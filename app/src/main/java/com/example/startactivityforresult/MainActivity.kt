package com.example.startactivityforresult


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
//    companion object{
//        const val MY_REQUEST_CODE=100
//    }
    private lateinit var edtData:EditText

    private var mActivityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result?.resultCode == RESULT_OK) {
            val intent = result.data
            val strResult = intent?.getStringExtra("data_result")
            edtData.setText(strResult)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Main Screen"
        edtData=findViewById(R.id.edt_data)
        val btnGoToDetail=findViewById<Button>(R.id.btn_go_to_detail)
        btnGoToDetail.setOnClickListener {
            val strData=edtData.text.toString().trim()
            onClickGoToDetail(strData)
        }
    }

    private fun onClickGoToDetail(strData: String) {

        val intent= Intent(this, DetailActivity::class.java)
        intent.putExtra("data_intent", strData)
//        startActivityForResult(intent, MY_REQUEST_CODE)
        mActivityResultLauncher.launch(intent)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (MY_REQUEST_CODE==requestCode && RESULT_OK==resultCode){
//            val strDataResult=data?.getStringExtra("data_result")
//            edtData.setText(strDataResult)
//        }
//    }
}