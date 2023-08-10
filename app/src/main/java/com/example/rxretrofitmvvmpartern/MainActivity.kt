package com.example.rxretrofitmvvmpartern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rxretrofitmvvmpartern.adapter.MyAdapter
import com.example.rxretrofitmvvmpartern.databinding.ActivityMainBinding
import com.example.rxretrofitmvvmpartern.model.ObjectData

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val mainViewModel =MainViewModel()
        mainBinding.mainViewModel=mainViewModel

        val linearLayoutManager=LinearLayoutManager(this)
        mainBinding.rcvData.layoutManager=linearLayoutManager
    }

    companion object{
       @BindingAdapter("list_data" ,"is_success")
       @JvmStatic
       fun loadListData(view: RecyclerView, list: ObservableArrayList<ObjectData>, isSuccess: ObservableBoolean){
            if(isSuccess.get()){
                Toast.makeText(view.context, "Call api success", Toast.LENGTH_SHORT).show()
            }else
            {
                Toast.makeText(view.context, "Call api error", Toast.LENGTH_SHORT).show()
            }

            val myAdapter =MyAdapter(list)
            view.adapter=myAdapter

        }
    }

}