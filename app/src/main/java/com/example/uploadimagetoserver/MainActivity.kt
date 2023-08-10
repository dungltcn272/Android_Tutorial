package com.example.uploadimagetoserver

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    companion object{
        const val MY_REQUEST_CODE=10
        const val TAG="dung"
    }
    private lateinit var edtUsername: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnSelectImage: Button
    private lateinit var btnUploadImage: Button
    private lateinit var imgFromGallery: ImageView
    private lateinit var imgFromApi: ImageView
    private lateinit var tvUsername: TextView
    private lateinit var tvPassword: TextView

    private val mActivityResultLauncher =registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
        object :ActivityResultCallback<ActivityResult> {
            override fun onActivityResult(result: ActivityResult?) {
                Log.e(TAG,"onActivityResult")
                if(result?.resultCode==Activity.RESULT_OK){
                    val data = result.data ?: return
                    val uri =data.data
                    try {
                        val bitmap=MediaStore.Images.Media.getBitmap(contentResolver, uri)
                        imgFromGallery.setImageBitmap(bitmap)
                    }catch (e: Exception){
                        e.printStackTrace()
                    }
                }
            }

        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()

        btnSelectImage.setOnClickListener {
            onClickRequestPermission()
        }
    }

    private fun initUi(){
        edtUsername=findViewById(R.id.edt_username)
        edtPassword=findViewById(R.id.edt_password)
        imgFromGallery=findViewById(R.id.img_from_gallery)
        imgFromApi=findViewById(R.id.img_from_api)
        btnUploadImage=findViewById(R.id.btn_upload_image)
        btnSelectImage=findViewById(R.id.btn_select_image)
        tvUsername=findViewById(R.id.tv_username)
        tvPassword=findViewById(R.id.tv_password)
    }

    private fun onClickRequestPermission() {
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.M){
            openGallery()
           return
        }
        if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==PackageManager.PERMISSION_GRANTED){
            openGallery()
        }
        else{
            // nếu thiết bị từ android 10 trở lên, phải dùng quyền READ_MEDIA_IMAGES
            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.TIRAMISU){
                val permission = arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES)
                requestPermissions(permission, MY_REQUEST_CODE)
            }
            else{
                val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permission, MY_REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode== MY_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"))
    }

}