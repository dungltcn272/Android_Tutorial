package com.example.rxandroidandretrofit

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rxandroidandretrofit.adapter.DataAdapter
import com.example.rxandroidandretrofit.model.ObjectData

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var mDataAdapter: DataAdapter
    private lateinit var rcvData: RecyclerView
    private lateinit var mProgressDialog: ProgressDialog
    private lateinit var mMainPresenter: MainPresenter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainPresenter= MainPresenter(this)
        val btnCallApi = findViewById<Button>(R.id.btn_call_api)
        rcvData = findViewById(R.id.rcv_data)

        val linearLayout = LinearLayoutManager(this)
        rcvData.layoutManager = linearLayout

        mProgressDialog= ProgressDialog(this)

        //đường kẻ phân cách các item của recycleview
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rcvData.addItemDecoration(dividerItemDecoration)
        btnCallApi.setOnClickListener {
            mProgressDialog.show()
            mMainPresenter.login()
        }
    }

    override fun loginSuccess(listData: List<ObjectData>) {
        mProgressDialog.dismiss()
        Toast.makeText(this@MainActivity, "Call API success", Toast.LENGTH_LONG).show()
        mDataAdapter = DataAdapter(listData)
        rcvData.adapter = mDataAdapter
    }

    override fun loginError() {
        Toast.makeText(this@MainActivity, "Call API Error", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mMainPresenter.getDisposable()!=null){
            mMainPresenter.getDisposable().dispose()
        }
    }
}