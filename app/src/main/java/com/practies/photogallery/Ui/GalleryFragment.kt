package com.practies.photogallery.Ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.practies.photogallery.R
import com.practies.photogallery.adapter.ImagePagingAdapter
import com.practies.photogallery.databinding.FragmentGalleryBinding
import com.practies.photogallery.viewModels.ImagesViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class GalleryFragment : Fragment() {
    private  var _binding:FragmentGalleryBinding?=null
    private val binding  get() = _binding!!

    private lateinit var imageAdapter: ImagePagingAdapter
    private lateinit var viewModel: ImagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding= FragmentGalleryBinding.inflate(inflater,container,false)


//        viewModel=ViewModelProvider(this).get(ImagesViewModel::class.java)
//        setUpView()
//        loadData()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           viewModel=ViewModelProvider(this).get(ImagesViewModel::class.java)

                  loadData()

    }

          private fun loadData(){

              lifecycleScope.launch {
                  viewModel.listData.collect{      pagingData->
                      imageAdapter.submitData(pagingData)

                      setUpView()
                  }
              }
          }

    private fun setUpView(){
        imageAdapter= ImagePagingAdapter()
        binding.imageRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter=imageAdapter
        }

    }

}

//StaggeredGridLayoutManager(
//2,StaggeredGridLayoutManager.VERTICAL
//)
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//        // TODO: Use the ViewModel
//    }