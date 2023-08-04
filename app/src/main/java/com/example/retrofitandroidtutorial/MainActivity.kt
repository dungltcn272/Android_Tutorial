package com.example.retrofitandroidtutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.retrofitandroidtutorial.api.ApiService
import com.example.retrofitandroidtutorial.model.Currency
import com.example.retrofitandroidtutorial.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var tvTerms: TextView
    private lateinit var tvSource: TextView
    private lateinit var tvUsdVnd: TextView
    private lateinit var tvPostResult: TextView
    private lateinit var btnCallApi: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTerms = findViewById(R.id.tv_terms)
        tvSource = findViewById(R.id.tv_source)
        tvUsdVnd = findViewById(R.id.tv_usd_vnd)
        btnCallApi = findViewById(R.id.btn_call_api)
        tvPostResult=findViewById(R.id.tv_post_result)

        btnCallApi.setOnClickListener {
//            clickCallApi()
            sendPost()
        }
    }
    //http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    private fun clickCallApi() {
        val options =HashMap<String, String>()
        options["access_key"] = "843d4d34ae72b3882e3db642c51e28e6"
        options["currencies"] = "VND"
        options["source"] = "USD"
        options["format"] = "1"
        ApiService.apiService.convertUsdToVnd2(options)
            .enqueue(object :Callback<Currency>{
                override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
                    Toast.makeText(this@MainActivity, "Call Api Success", Toast.LENGTH_SHORT).show()

                    val currency =response.body()
                    if(currency!=null && currency.success){
                        tvTerms.text=currency.terms
                        tvSource.text=currency.source
                        tvUsdVnd.text=currency.quotes.usdVnd.toString()
                    }
                }

                override fun onFailure(call: Call<Currency>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Call Api Error", Toast.LENGTH_SHORT).show()
                }

            })
    }
    private fun sendPost(){
        val post =Post(10, 101, "dung", "tin dung")
        ApiService.apiService.sendPost(post).enqueue(object :Callback<Post>{
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Toast.makeText(this@MainActivity, "Call Api success", Toast.LENGTH_SHORT).show()
                val postResult =response.body()
                if(postResult!=null){
                    tvPostResult.text=postResult.toString()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Call Api error", Toast.LENGTH_SHORT).show()
            }

        })
    }
}