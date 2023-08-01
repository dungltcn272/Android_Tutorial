package com.example.addtocartanimetion

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private lateinit var mListProduct : List<Product>
    private lateinit var iClickAddToCartListener : IClickAddToCartListener

    interface IClickAddToCartListener {
        fun onClickAddToCart(imageAddToCart : ImageView, product: Product)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Product>, listener: IClickAddToCartListener){
        this.mListProduct=list
        this.iClickAddToCartListener=listener
        notifyDataSetChanged()
    }


    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct =itemView.findViewById<ImageView>(R.id.img_product)
        val tvProductName=itemView.findViewById<TextView>(R.id.tv_product_name)
        val tvDescription=itemView.findViewById<TextView>(R.id.tv_description)
        val imgAddToCart=itemView.findViewById<ImageView>(R.id.img_add_to_cart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(mListProduct!=null){
            return mListProduct.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product =mListProduct[position]
        if(product ==null){
            return
        }
        holder.imgProduct.setImageResource(product.resourceId)
        holder.tvProductName.text=product.name
        holder.tvDescription.text=product.description
        if(product.isAddToCart){
            holder.imgAddToCart.setBackgroundResource(R.drawable.bg_grey_corner_6)
        }else{
            holder.imgAddToCart.setBackgroundResource(R.drawable.bg_red_corner_6)
        }

        holder.imgAddToCart.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if(!product.isAddToCart){
                    iClickAddToCartListener.onClickAddToCart(holder.imgAddToCart, product)
                }
            }
        })
    }
}