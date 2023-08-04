package com.example.fragmentandroidtutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFragment : Fragment() {

    private lateinit var rcvUser : RecyclerView
    private lateinit var mView: View
    private lateinit var mMainActivity : MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mView=inflater.inflate(R.layout.fragment_home, container, false)
        mMainActivity=activity as MainActivity
        rcvUser =mView.findViewById(R.id.rcv_user)
        val linearLayoutManager=LinearLayoutManager(mMainActivity)
        rcvUser.layoutManager=linearLayoutManager

        val userAdapter =UserAdapter(getListUser(),object : UserAdapter.IClickItemListener{
            override fun onClickItemUser(user: User) {
                mMainActivity.goToDetailFragment(user)
            }

        })
        rcvUser.adapter=userAdapter

        val itemDecoration :RecyclerView.ItemDecoration =DividerItemDecoration(mMainActivity, DividerItemDecoration.VERTICAL)
        rcvUser.addItemDecoration(itemDecoration)

        return mView
    }

    private fun getListUser(): List<User> {
        val list =ArrayList<User>()
        for(i in 1 .. 20){
            list.add(User("This is user $i"))
        }
        return list
    }
}