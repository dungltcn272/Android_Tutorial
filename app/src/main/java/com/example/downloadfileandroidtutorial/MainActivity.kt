package com.example.downloadfileandroidtutorial

import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var edtFileUrl: EditText
    private lateinit var btnDownloadFile : Button

    companion object {
        const val REQUEST_PERMISSION_CODE = 10
        const val URL_FILE ="https://zingmp3.vn/bai-hat/Seasons-Rival-Cadmium-Harley-Bird/ZZAEU7FB.html"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtFileUrl=findViewById(R.id.edt_file_url)
        edtFileUrl.setText(URL_FILE)

        btnDownloadFile=findViewById(R.id.btn_download_file)
        btnDownloadFile.setOnClickListener {
            checkPermission()
        }

    }

    private fun checkPermission() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED){
                val permission = arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                requestPermissions(permission, REQUEST_PERMISSION_CODE)
            }
            else{
                startDownloadFile()
            }
        }else{
            startDownloadFile()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode ==  REQUEST_PERMISSION_CODE){
            if(grantResults.size >0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                 startDownloadFile()
            }
            else{
                Toast.makeText(this, "PERMISTION DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startDownloadFile() {
        val urlFile =edtFileUrl.text.toString().trim()

        val request = DownloadManager.Request(Uri.parse(urlFile))

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
        request.setTitle("Download")
        request.setDescription("Download file...")

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, System.currentTimeMillis().toString())

        val downloadManager : DownloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        if(downloadManager!=null){
            downloadManager.enqueue(request)
        }
    }
}