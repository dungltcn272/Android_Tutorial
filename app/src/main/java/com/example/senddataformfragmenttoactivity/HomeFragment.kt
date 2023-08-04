package com.example.senddataformfragmenttoactivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class HomeFragment : Fragment() {

    private lateinit var edtEmail: EditText
    private lateinit var edtName: EditText
    private lateinit var btnUpdate: Button
    private lateinit var mView: View
    private lateinit var mMainActivity: MainActivity

    companion object{
        lateinit var iSendDataListener: ISendDataListener
        fun getInstance(user: User, listener: ISendDataListener) : HomeFragment{
            iSendDataListener=listener
            val homeFragment=HomeFragment()
            val bundle=Bundle()
            bundle.putSerializable("object_user", user)
            homeFragment.arguments=bundle
            return homeFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mView=inflater.inflate(R.layout.fragment_home, container, false)
        mMainActivity =activity as MainActivity
        initUI()

        return  mView
    }

    private fun initUI() {
        edtEmail=mView.findViewById(R.id.edt_email)
        edtName=mView.findViewById(R.id.edt_name)
        btnUpdate=mView.findViewById(R.id.btn_update)

        val user=arguments?.get("object_user") as User
        edtEmail.setText(user.email)
        edtName.setText(user.name)

        btnUpdate.setOnClickListener {
            sendDataToActivity()
        }
    }

    private fun sendDataToActivity() {
        val strEmailUpdate=edtEmail.text.toString().trim()
        val strNameUpdate=edtName.text.toString().trim()

        val user=User(strEmailUpdate, strNameUpdate)

        iSendDataListener.sendData(user)
    }


}