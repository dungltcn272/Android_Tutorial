package com.example.fragmentandroidtutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class DetailFragment : Fragment() {

    companion object{
        val TAG: String =DetailFragment::class.java.name
    }
    private lateinit var tvName: TextView
    private lateinit var btnBack: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val mView=inflater.inflate(R.layout.fragment_detail, container, false)
        tvName=mView.findViewById(R.id.tv_name)
        btnBack=mView.findViewById(R.id.btn_back)

        val bundleReceive =arguments
        if(bundleReceive!=null){
            val user =bundleReceive.get("object_user") as User
            tvName.text = user.name
        }

        btnBack.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        return mView
    }
}