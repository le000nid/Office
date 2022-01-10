package com.example.officemanagerapp.ui.services.order_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.officemanagerapp.R
import com.example.officemanagerapp.databinding.FragmentOrderInfoBinding
import com.example.officemanagerapp.models.OrderType
import com.example.officemanagerapp.models.Photo
import com.example.officemanagerapp.network.Resource
import com.example.officemanagerapp.ui.FieldsValidator
import com.example.officemanagerapp.ui.RESULT_SUCCESS
import com.example.officemanagerapp.util.*
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderInfoFragment : Fragment() {

    private val viewModel: OrderInfoViewModel by viewModels()
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var binding: FragmentOrderInfoBinding
    private val args by navArgs<OrderInfoFragmentArgs>()

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
        viewModel.orderResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Success -> {
                    binding.progressBar.hide()
                    setFragmentResult(ORDER_POST_REQUEST, bundleOf("post_result" to RESULT_SUCCESS))
                    val action = OrderInfoFragmentDirections.actionOrderInfoFragmentToOrdersListFragment()
                    findNavController().navigate(action)
                }
                is Resource.Loading -> {
                    binding.allViews.visibility = View.GONE
                    binding.progressBar.show()
                }
                is Resource.Failure -> {
                    binding.allViews.visibility = View.VISIBLE
                    binding.progressBar.hide()
                }
            }
        }
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

        binding.txInputName.addTextChangedListener {
            viewModel.fullName.value = it.toString()
        }

        binding.txInputPhone.addTextChangedListener {
            viewModel.phone.value = it.toString()
        }

        binding.txInputAddress.addTextChangedListener {
            viewModel.address.value = it.toString()
        }

        binding.txInputComment.addTextChangedListener {
            viewModel.comment.value = it.toString()
        }

        binding.txInputFloor.addTextChangedListener {
            viewModel.floor.value = it.toString()
        }

        binding.txInputRoom.addTextChangedListener {
            viewModel.room.value = it.toString()
        }

        binding.fabDone.setOnClickListener {

            if (isFormValid()) {
                if (args.order.companyId == null) {
                    viewModel.postOrder(OrderType.PLANNED)
                } else {
                    viewModel.postOrder(OrderType.MARKET)
                }
            } else {
                return@setOnClickListener
            }

        }
    }

    private fun isFormValid(): Boolean {
        var validationFlag = true

        FieldsValidator.clearError(binding.layoutName)
        FieldsValidator.clearError(binding.layoutPhone)
        FieldsValidator.clearError(binding.layoutComment)
        FieldsValidator.clearError(binding.layoutAddress)
        FieldsValidator.clearError(binding.layoutFloor)
        FieldsValidator.clearError(binding.layoutRoom)

        if (!FieldsValidator.nameValidator(binding.layoutName, viewModel.fullName.value)) { validationFlag = false }
        if (!FieldsValidator.phoneValidator(binding.layoutPhone, viewModel.phone.value)) { validationFlag = false }
        if (!FieldsValidator.commentValidator(binding.layoutComment, viewModel.comment.value)) { validationFlag = false }
        if (!FieldsValidator.addressValidator(binding.layoutAddress, viewModel.address.value)) { validationFlag = false }
        if (!FieldsValidator.floorValidator(binding.layoutFloor, viewModel.floor.value)) { validationFlag = false }
        if (!FieldsValidator.roomValidator(binding.layoutRoom, viewModel.room.value)) { validationFlag = false }

        return validationFlag
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
