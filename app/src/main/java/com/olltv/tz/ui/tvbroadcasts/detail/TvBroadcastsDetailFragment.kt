package com.olltv.tz.ui.tvbroadcasts.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.olltv.tz.databinding.FragmentTvBroadcasstsListBinding
import com.olltv.tz.databinding.FragmentTvBroadcastDetailBinding
import dagger.android.support.DaggerFragment

class TvBroadcastsDetailFragment : DaggerFragment() {

    private val args: TvBroadcastsDetailFragmentArgs by navArgs()

    private var _binding: FragmentTvBroadcastDetailBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentTvBroadcastDetailBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            it.item = args.tvBroadast
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.tvBroadast.channelName
        binding.ivBroadcastImage.transitionName = args.tvBroadast.id.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}