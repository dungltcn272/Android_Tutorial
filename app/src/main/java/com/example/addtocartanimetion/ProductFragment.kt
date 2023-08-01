package com.example.addtocartanimetion

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductFragment : Fragment() {
    private lateinit var rcvProduct: RecyclerView
    private lateinit var mView: View
    private lateinit var productAdapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mView=inflater.inflate(R.layout.fragment_product, container, false)

        rcvProduct=mView.findViewById(R.id.rcv_product)
        val layoutManager = LinearLayoutManager(activity as MainActivity)
        rcvProduct.layoutManager=layoutManager

        productAdapter= ProductAdapter()
        productAdapter.setData(getListProduct(),object : ProductAdapter.IClickAddToCartListener{
            override fun onClickAddToCart(imageAddToCart: ImageView, product: Product) {
                AnimationUtil().translateAnimation((activity as MainActivity).getViewAnimation(),imageAddToCart, (activity as MainActivity).getViewEndAnimation(),object :Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {
                    }

                    override fun onAnimationRepeat(animation: Animation?) {
                    }

                    @SuppressLint("NotifyDataSetChanged")
                    override fun onAnimationEnd(animation: Animation?) {
                        product.isAddToCart=true
                        imageAddToCart.setBackgroundResource(R.drawable.bg_grey_corner_6)
                        productAdapter.notifyDataSetChanged()

                        (activity as MainActivity).setCountProductInCart((activity as MainActivity).getCountProduct()+1)
                    }
                })
            }
        })

        rcvProduct.adapter=productAdapter
        return mView
    }

    private fun getListProduct(): List<Product> {
        val list =ArrayList<Product>()
        list.add(Product(R.drawable.img1, "Product Name 1", "This is Description"))
        list.add(Product(R.drawable.img2, "Product Name 2", "This is Description"))
        list.add(Product(R.drawable.img1, "Product Name 3", "This is Description"))
        list.add(Product(R.drawable.img2, "Product Name 4", "This is Description"))
        list.add(Product(R.drawable.img1, "Product Name 5", "This is Description"))
        list.add(Product(R.drawable.img2, "Product Name 6", "This is Description"))
        list.add(Product(R.drawable.img1, "Product Name 7", "This is Description"))
        list.add(Product(R.drawable.img2, "Product Name 8", "This is Description"))
        list.add(Product(R.drawable.img1, "Product Name 9", "This is Description"))
        list.add(Product(R.drawable.img2, "Product Name 10", "This is Description"))
        list.add(Product(R.drawable.img1, "Product Name 11", "This is Description"))
        list.add(Product(R.drawable.img2, "Product Name 12", "This is Description"))
        list.add(Product(R.drawable.img2, "Product Name 12", "This is Description"))
        list.add(Product(R.drawable.img2, "Product Name 12", "This is Description"))
        list.add(Product(R.drawable.img2, "Product Name 12", "This is Description"))
        list.add(Product(R.drawable.img2, "Product Name 12", "This is Description"))
        list.add(Product(R.drawable.img2, "Product Name 12", "This is Description"))
        list.add(Product(R.drawable.img2, "Product Name 12", "This is Description"))

        return list
    }
}