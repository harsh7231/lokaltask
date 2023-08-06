package com.example.gridapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductDetailsActivity : AppCompatActivity(),ProductImageAdapter.OnItemClickListener {
    lateinit var product: Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        product=Products.products!![intent.getIntExtra("position",0)]
        product?.let {
            val productImage = findViewById<ImageView>(R.id.productDetailImage)
            val productName = findViewById<TextView>(R.id.productDetailName)
            val productDetailDescription=findViewById<TextView>(R.id.productDetailDescription)
            val productPrice=findViewById<TextView>(R.id.productDetailPrice)
            val productRating = findViewById<RatingBar>(R.id.productDetailRating)
            val discountPercentage = findViewById<TextView>(R.id.discountPercentage)
            val productStock = findViewById<TextView>(R.id.stock)
            val productBrand = findViewById<TextView>(R.id.brand)
            val productCategory = findViewById<TextView>(R.id.category)

            productName.text = it.title
            productPrice.text="₹"+it.price
            productDetailDescription.text=it.description
            productRating.rating = it.rating.toFloat()
            discountPercentage.text = "Discount Percentage: "+it.discountPercentage.toString()
            productStock.text = "Stock: "+it.stock.toString()
            productBrand.text = "Brand: "+it.brand
            productCategory.text = "Category: "+it.category

            // Load main product image using Picasso
            Picasso.get()
                .load(it.images[0])
                .fit()
                .into(productImage)

            val imageRecyclerView = findViewById<RecyclerView>(R.id.imageRecyclerView)
            val imageAdapter = ProductImageAdapter(it.images,this)
            imageRecyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            imageRecyclerView.adapter = imageAdapter
        }
    }

    override fun onItemClick(imageUrl: String) {
        val productImage = findViewById<ImageView>(R.id.productDetailImage)

        // Load the clicked image into the main product image using Picasso
        Picasso.get()
            .load(imageUrl)
            .fit()
            .into(productImage)
    }
}