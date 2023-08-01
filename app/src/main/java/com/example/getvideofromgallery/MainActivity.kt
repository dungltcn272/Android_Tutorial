package com.example.getvideofromgallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.Manifest
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission

class MainActivity : AppCompatActivity() {
    private lateinit var rcvVideo : RecyclerView
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var btnGetVideo : Button
    private lateinit var mListVideo : ArrayList<Video>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcvVideo=findViewById(R.id.rcv_video)
        videoAdapter= VideoAdapter(this)
        btnGetVideo=findViewById(R.id.btn_get_video)

        val gridLayoutManager = GridLayoutManager(this, 2)
        rcvVideo.layoutManager=gridLayoutManager

        btnGetVideo.setOnClickListener{
            checkPermission()
        }
    }

    private fun checkPermission() {
        val permissionListener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                getAllVideoFromGallery()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(this@MainActivity, "Permission Denied\n$deniedPermissions", Toast.LENGTH_SHORT).show()
            }
        }

        TedPermission.create()
            .setPermissionListener(permissionListener)
            .setDeniedMessage("If you reject permission, you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .check()
    }

    private fun getAllVideoFromGallery() {
        mListVideo= ArrayList()

        var absolutePathOfImage : String
        var thumpnail : String

        val uri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI

        val projection= arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media._ID,
            MediaStore.Video.Thumbnails.DATA
        )

        val orderBy =MediaStore.Images.Media.DATE_TAKEN
        val cursor: Cursor?=applicationContext.contentResolver.query(uri, projection,null, null,
            "$orderBy DESC"
        )
        val columIndexData: Int? = cursor?.getColumnIndex(MediaStore.MediaColumns.DATA)
        val thump :Int?=cursor?.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA)

        while (cursor?.moveToNext() == true){
            absolutePathOfImage=cursor.getString(columIndexData!!)
            thumpnail=cursor.getString(thump!!)

            val video =Video(absolutePathOfImage, thumpnail)

            mListVideo.add(video)

        }

        videoAdapter.setData(mListVideo)
        rcvVideo.adapter=videoAdapter

    }
}