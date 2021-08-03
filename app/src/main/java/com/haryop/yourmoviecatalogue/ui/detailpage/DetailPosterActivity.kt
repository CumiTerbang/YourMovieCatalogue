package com.haryop.yourmoviecatalogue.ui.detailpage

import android.view.LayoutInflater
import com.haryop.yourmoviecatalogue.databinding.ActivityDetailPosterBinding
import com.haryop.yourmoviecatalogue.utils.BaseActivityBinding
import com.haryop.yourmoviecatalogue.utils.setImageGlide

class DetailPosterActivity: BaseActivityBinding<ActivityDetailPosterBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityDetailPosterBinding
        get() = ActivityDetailPosterBinding::inflate

    override fun setupView(binding: ActivityDetailPosterBinding) {
        var poster_url = intent.getStringExtra("poster_url").toString()
        setImageGlide(poster_url, binding.root, binding.posterImageView)
        binding.close.setOnClickListener { finish() }
    }
}