package com.example.swiperefreshlayouttutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var rcvData : RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var itemAdapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcvData=findViewById(R.id.rvc_data)
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout)

        val linearLayoutManager =LinearLayoutManager(this)
        rcvData.layoutManager=linearLayoutManager

        itemAdapter =ItemAdapter()
        itemAdapter.setData(getListItems())
        rcvData.adapter=itemAdapter

        val itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rcvData.addItemDecoration(itemDecoration)

        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setColorSchemeColors(resources.getColor(R.color.teal_200))
    }

    private fun getListItems(): List<ItemObject> {
        val list =ArrayList<ItemObject>()
        for(i in 0.. 20){
            list.add(ItemObject("Item $i"))
        }
        return list
    }

    override fun onRefresh() {
        itemAdapter.setData(getListItems())
        val handle = Handler()
        handle.postDelayed({ swipeRefreshLayout.isRefreshing=false }, 3000)
    }
}