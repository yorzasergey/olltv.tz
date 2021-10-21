package com.olltv.tz.ui.tvbroadcasts.list

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.olltv.tz.databinding.FragmentTvBroadcasstsListBinding
import com.olltv.tz.domen.models.TvBroadcast
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject
import androidx.core.content.ContextCompat.getSystemService

import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat


class TvBroadcastsListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: TvBroadcastsListViewModel by viewModels {
        viewModelFactory
    }

    private val onClickListener = object : TvBroadcastsAdapter.OnClickListener {
        override fun onClickItem(item: TvBroadcast, transitionImageView: ImageView) {
            val extras = FragmentNavigatorExtras(
                transitionImageView to item.id.toString()
            )
            findNavController().navigate(
                TvBroadcastsListFragmentDirections.actionTvBroadcastListToTvBroadcastsDetailFragment(item),
                extras
            )
        }
    }

    private val adapter = TvBroadcastsAdapter(onClickListener)

    private var _binding: FragmentTvBroadcasstsListBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentTvBroadcasstsListBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = viewLifecycleOwner
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        binding.charactersList.post{ startPostponedEnterTransition() }

        binding.btnRetry.setOnClickListener {
            adapter.retry()
        }

        binding.charactersList.adapter = adapter

        lifecycleScope.launchWhenResumed {
            viewModel.result.collectLatest {
                adapter.submitData(it)
            }
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                tvEmptyResult.isVisible =
                    loadState.source.refresh is LoadState.NotLoading && adapter.itemCount == 0
                groupError.isVisible = loadState.source.refresh is LoadState.Error
                loader.isVisible =
                    loadState.source.refresh is LoadState.Loading || loadState.source.append is LoadState.Loading
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}