package com.example.apadter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.machinetest.R
import com.example.machinetest.databinding.RvExpandableItemBinding
import com.example.network.model.ArticleModel

class ArticleAdapter(private val dataSet: List<ArticleModel>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(view: RvExpandableItemBinding) : RecyclerView.ViewHolder(view.root) {

        private val binding: RvExpandableItemBinding = view;

        fun bindView(model: ArticleModel) {
            binding.let {
                binding.item = model
                binding.parent.setOnClickListener {
                    binding.expandable.toggle(true)
                }
                Glide.with(binding.root.context)
                    .asBitmap()
                    .load(model.getImage())
                    .placeholder(R.color.gray)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(binding.layoutTop.image)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvExpandableItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION)
            viewHolder.bindView(dataSet.get(position));
    }

    override fun getItemCount() = dataSet.size

}