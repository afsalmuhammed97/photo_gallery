package com.practies.photogallery.Ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.practies.photogallery.R
import com.practies.photogallery.Utill.OnItemClickListener
import com.practies.photogallery.adapter.ImagePagingAdapter
import com.practies.photogallery.databinding.FragmentGalleryBinding
import com.practies.photogallery.viewModels.ImagesViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch





@AndroidEntryPoint
class GalleryFragment : Fragment(),OnItemClickListener {
    private  var _binding:FragmentGalleryBinding?=null
    private val binding  get() = _binding!!

    private lateinit var imageAdapter: ImagePagingAdapter
    private  val viewModel by activityViewModels<ImagesViewModel>()

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
//           viewModel=ViewModelProvider(this).get(ImagesViewModel::class.java)


        setUpView()

        loadData()

    }

          private fun loadData(){

              lifecycleScope.launch {
                  viewModel.listData.collect{      pagingData->

                      imageAdapter.submitData(pagingData)



                  }
              }
          }

    private fun setUpView(){
        imageAdapter= ImagePagingAdapter(this)
        binding.imageRv.apply {
            layoutManager =StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)                     // LinearLayoutManager(context)
            adapter=imageAdapter
        }

    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(R.id.action_galleryFragment_to_imagDetailsFragment)
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