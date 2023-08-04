package com.example.fragmentandroidtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentTransaction =supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_frame, HomeFragment())
        fragmentTransaction.commit()
    }

    fun goToDetailFragment(user: User){
        val fragmentTransaction =supportFragmentManager.beginTransaction()

        val detailFragment =DetailFragment()

        val bundle=Bundle()
        bundle.putSerializable("object_user", user)

        detailFragment.arguments=bundle

        fragmentTransaction.replace(R.id.content_frame, detailFragment)
        fragmentTransaction.addToBackStack(DetailFragment.TAG)
        fragmentTransaction.commit()
    }
}