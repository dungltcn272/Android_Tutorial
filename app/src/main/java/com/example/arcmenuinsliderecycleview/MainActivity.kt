package com.example.arcmenuinsliderecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sa90.materialarcmenu.ArcMenu

class MainActivity : AppCompatActivity() {

    private lateinit var rcvUser : RecyclerView
    private lateinit var arcMenu : ArcMenu
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcvUser =findViewById(R.id.rcv_user)
        arcMenu=findViewById(R.id.arc_menu)

        userAdapter= UserAdapter()

        val linearLayoutManager =LinearLayoutManager(this)
        rcvUser.layoutManager=linearLayoutManager

        userAdapter.setData(getListUser())
        rcvUser.adapter=userAdapter

        rcvUser.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy>0){
                    arcMenu.visibility=View.GONE
                }
                else arcMenu.visibility=View.VISIBLE
            }
        })
    }

    private fun getListUser(): List<String> {
        val list =ArrayList<String>()
        for(i in 1 .. 20){
            list.add("User name $i")
        }
        return list
    }
}