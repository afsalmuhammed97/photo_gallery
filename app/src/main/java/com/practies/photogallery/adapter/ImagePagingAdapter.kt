package com.practies.photogallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.practies.photogallery.R
import com.practies.photogallery.Utill.OnItemClickListener
import com.practies.photogallery.databinding.ImageItemBinding
import com.practies.photogallery.model.ImageData

class ImagePagingAdapter(private val listener:OnItemClickListener):PagingDataAdapter<ImageData,ImagePagingAdapter.ImageHolder>(diffCallback) {

   inner class ImageHolder (val binding: ImageItemBinding):RecyclerView.ViewHolder(binding.root),View.OnClickListener{

            init {
                binding.image.setOnClickListener(this)
            }


        override fun onClick(v: View?) {

           val position=absoluteAdapterPosition

            if (position !=RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }

    }

    companion object{

        val diffCallback=object :DiffUtil.ItemCallback<ImageData>(){

            override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
               return  oldItem.id==newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
               return  oldItem==newItem
            }

        }
    }


    override fun onBindViewHolder(holder: ImageHolder, position: Int) {


        val currentItem=getItem(position)


        Glide.with(holder.itemView.context).load( currentItem?.download_url)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_background))
            .centerCrop()
            .into(holder.binding.image)
//                   val image=currentItem?.download_url
//                    holder.binding.image.load(image){
//                        crossfade(true)
//                        crossfade(500)
//                    }



    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
           return  ImageHolder(
               ImageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),
           )
    }




}