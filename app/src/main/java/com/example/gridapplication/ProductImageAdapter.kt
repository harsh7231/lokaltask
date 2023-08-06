package com.example.gridapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductImageAdapter(private val imageUrls: List<String>,
                          private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ProductImageAdapter.ImageViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(imageUrl: String)
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage = itemView.findViewById<ImageView>(R.id.productImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_image, parent, false)
        return ImageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageUrls[position]

        // Load product image using Picasso
        Picasso.get()
            .load(imageUrl)
            .fit()
            .into(holder.productImage)
        holder.productImage.setOnClickListener {
            onItemClickListener.onItemClick(imageUrl)
        }
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }
}
