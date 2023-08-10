package com.example.clickitemrecycleview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clickitemrecycleview.my_interface.IClickItemUserListener

class MainActivity : AppCompatActivity() {

    private lateinit var rcvData : RecyclerView
    private lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcvData=findViewById(R.id.rcv_data)

        val linearLayoutManager=LinearLayoutManager(this)
        rcvData.layoutManager=linearLayoutManager
        val itemDecoration =DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rcvData.addItemDecoration(itemDecoration)
        userAdapter= UserAdapter(getListUser(),object :IClickItemUserListener{
            override fun onClickItemUser(user: User) {
                onClickGoToDetail(user)
            }
        })
        rcvData.adapter=userAdapter

    }

    private fun getListUser(): List<User> {
        val list =ArrayList<User>()
        list.add(User(R.mipmap.ic_launcher, "dung1", "nam dinh"))
        list.add(User(R.mipmap.ic_launcher, "dung2", "nam dinh"))
        list.add(User(R.mipmap.ic_launcher, "dung3", "nam dinh"))
        list.add(User(R.mipmap.ic_launcher, "dung4", "nam dinh"))
        list.add(User(R.mipmap.ic_launcher, "dung1", "nam dinh"))
        list.add(User(R.mipmap.ic_launcher, "dung", "nam dinh"))
        list.add(User(R.mipmap.ic_launcher, "dung7", "nam dinh"))
        list.add(User(R.mipmap.ic_launcher, "dung8", "nam dinh"))
        list.add(User(R.mipmap.ic_launcher, "dung9", "nam dinh"))
        list.add(User(R.mipmap.ic_launcher, "dung10", "nam dinh"))
        list.add(User(R.mipmap.ic_launcher, "dung1", "nam dinh"))
        list.add(User(R.mipmap.ic_launcher, "dung1", "nam dinh"))

        return list
    }
    private fun onClickGoToDetail(user: User) {
        val intent= Intent(this, DetailActivity::class.java)
        val bundle=Bundle()
        bundle.putSerializable("object user", user)
        intent.putExtras(bundle)
        this.startActivity(intent)
    }
}