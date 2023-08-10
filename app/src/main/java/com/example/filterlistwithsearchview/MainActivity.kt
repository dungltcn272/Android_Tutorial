package com.example.filterlistwithsearchview

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rcvUser : RecyclerView
    private lateinit var userAdapter : UserAdapter
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcvUser=findViewById(R.id.rcv_user)
        val linearLayoutManager =LinearLayoutManager(this)
        rcvUser.layoutManager=linearLayoutManager

        userAdapter= UserAdapter(getListUsers(), getListUsers())
        rcvUser.adapter=userAdapter

        val itemDecoration=DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rcvUser.addItemDecoration(itemDecoration)
    }

    private fun getListUsers(): List<User> {

        val list =ArrayList<User>()
        list.add(User(R.drawable.img_01, "dung", "nam dinh"))
        list.add(User(R.drawable.img_01, "dung", "nam dinh"))
        list.add(User(R.drawable.img_01, "tin coder", "nam dinh"))
        list.add(User(R.drawable.img_01, "dung", "nam dinh"))
        list.add(User(R.drawable.img_01, "dung", "nam dinh"))
        list.add(User(R.drawable.img_01, "dun", "nam dinh"))
        list.add(User(R.drawable.img_01, "dun", "nam dinh"))
        list.add(User(R.drawable.img_01, "dun", "nam dinh"))
        list.add(User(R.drawable.img_01, "dun", "nam dinh"))
        list.add(User(R.drawable.img_01, "dun", "nam dinh"))
        list.add(User(R.drawable.img_01, "dun", "nam dinh"))
        list.add(User(R.drawable.img_01, "dun", "nam dinh"))
        list.add(User(R.drawable.img_01, "dun", "nam dinh"))
        list.add(User(R.drawable.img_01, "dun", "nam dinh"))
        list.add(User(R.drawable.img_01, "dun", "nam dinh"))
        list.add(User(R.drawable.img_01, "dun", "nam dinh"))
        list.add(User(R.drawable.img_01, "dun", "nam dinh"))
        list.add(User(R.drawable.img_01, "dun", "nam dinh"))

        return list
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchManager =getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView= menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth=Integer.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                userAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                userAdapter.filter.filter(newText)
                return false
            }

        })
        return true
    }

    override fun onBackPressed() {
        if(searchView.isIconified){
            searchView.isIconified=true
            return
        }
        super.onBackPressed()
    }
}