package com.haryop.yourmoviecatalogue.ui.detailpage

import android.view.LayoutInflater
import android.view.ViewGroup
import com.haryop.yourmoviecatalogue.databinding.FragmentDetailPageBinding
import com.haryop.yourmoviecatalogue.ui.ToolbarListener
import com.haryop.yourmoviecatalogue.utils.BaseFragmentBinding
import com.haryop.yourmoviecatalogue.utils.ConstantsObj

class DetailPageFragment : BaseFragmentBinding<FragmentDetailPageBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailPageBinding
        get() = FragmentDetailPageBinding::inflate

    lateinit var viewBinding: FragmentDetailPageBinding
    override fun setupView(binding: FragmentDetailPageBinding) {
        viewBinding = binding
        setUpToolbar()
    }

    fun setUpToolbar(){

    }
}