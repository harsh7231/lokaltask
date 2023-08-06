package com.example.gridapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await

class MainActivity : AppCompatActivity(), ProductAdapter.OnItemClickListener {

lateinit var apiResponse: ApiResponse
lateinit var productAdapter:ProductAdapter
lateinit var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recyclerView)
        loadDataFromUrl()

    }

    private fun loadDataFromUrl() {
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.api.getApiResponse().await()
                }
                apiResponse=ApiResponse(response.products,response.total,response.skip,response.limit)
                Products.products=apiResponse.products
                productAdapter = ProductAdapter(apiResponse.products, this@MainActivity)
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                recyclerView.adapter = productAdapter
            } catch (e: Exception) {
                Log.e("Api exception",e.toString())
                // Handle error case
            }
        }
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }
}