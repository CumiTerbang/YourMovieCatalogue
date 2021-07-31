package com.haryop.yourmoviecatalogue.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.haryop.yourmoviecatalogue.R
import com.haryop.yourmoviecatalogue.databinding.FragmentHomePageBinding
import com.haryop.yourmoviecatalogue.ui.ToolbarListener
import com.haryop.yourmoviecatalogue.utils.BaseFragmentBinding
import com.haryop.yourmoviecatalogue.utils.ConstantsObj


class HomePageFragment : BaseFragmentBinding<FragmentHomePageBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomePageBinding
        get() = FragmentHomePageBinding::inflate

    lateinit var viewBinding: FragmentHomePageBinding
    override fun setupView(binding: FragmentHomePageBinding) {
        viewBinding = binding
        setUpContent()
    }

    fun setUpContent() = with(viewBinding) {
        btnMore.setOnClickListener {
            Toast.makeText(requireContext(), "more button clicked", Toast.LENGTH_SHORT).show()
            onOpenDetail()
        }
    }

    fun onReSearch(_query: String) = with(viewBinding) {

    }

    fun onOpenDetail() {
        var imdb_id = ""

        (activity as ToolbarListener).onUpdateToolbar(ConstantsObj.DETAIL_PAGE)
        findNavController().navigate(
            R.id.action_home_to_detail, bundleOf("imdb_id" to imdb_id)
        )
    }

    override fun onResume() {
        (activity as ToolbarListener).onUpdateToolbar(ConstantsObj.HOME_PAGE)
        super.onResume()
    }

}