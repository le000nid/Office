package com.example.officemanagerapp.ui.services.orderInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentOrderInfoBinding
import com.example.officemanagerapp.models.Photo
import com.github.dhaval2404.imagepicker.ImagePicker

class PlannedInfoFragment : Fragment() {

    private val viewModel: OrderInfoViewModel by viewModels()
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var binding: FragmentOrderInfoBinding
    private val args by navArgs<PlannedInfoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_info, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        setUpAdapter()
        initListeners()
        observePostOrder()

        return binding.root
    }

    private fun observePostOrder() {

    }

    private fun initListeners() {
        binding.btnAddPhoto.setOnClickListener {
            ImagePicker.with(this)
                .cropSquare()
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent {
                    getContent.launch(it)
                }
        }

        binding.editTextOrder.addTextChangedListener {
            viewModel.comment = it.toString()
        }

        binding.fabDone.setOnClickListener {

            if (args.order.companyId == null ) {
                // marketPost
            } else {
                // plannedPost
            }
        }
    }

    private fun setUpAdapter() {
        photoAdapter = PhotoAdapter(PhotoAdapter.PhotoRemoveClick {
            val oldList = viewModel.photos.value?.toMutableList()
            oldList?.remove(it)
            viewModel.photos.value = oldList?.toList()
        })

        binding.photosRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = photoAdapter
            setHasFixedSize(true)
        }

        viewModel.photos.observe(viewLifecycleOwner) {
            photoAdapter.photos = viewModel.photos.value!!.toList()
        }
    }


    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { intent ->
        if (intent.data?.data != null) {
            // TODO(Add the restriction to number of photos that user can upload)
            val oldList = viewModel.photos.value?.toMutableList()
            oldList?.add(Photo(uri = intent.data?.data))
            viewModel.photos.value = oldList?.toList()
        }
    }
}
