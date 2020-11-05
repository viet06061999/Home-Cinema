package com.sun.homecinema.ui.playtrailer

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.sun.homecinema.R
import com.sun.homecinema.base.BindingFragment
import com.sun.homecinema.base.BottomNavigationListener
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.databinding.FragmentPlayTrailerBinding
import com.sun.homecinema.ui.adapter.WatchMoreAdapter
import com.sun.homecinema.ui.detail.DetailViewModel
import com.sun.homecinema.utils.hide
import com.sun.homecinema.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayTrailerFragment : BindingFragment<FragmentPlayTrailerBinding>() {

    override fun getLayoutResId() = R.layout.fragment_play_trailer

    override val viewModel by viewModel<DetailViewModel>()

    private var navigationListener: BottomNavigationListener? = null

    private val args: PlayTrailerFragmentArgs by navArgs()

    private val watchMoreAdapter = WatchMoreAdapter(::onItemMovieClick)

    private var youtube: YouTubePlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadDetail(args.movieId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BottomNavigationListener) navigationListener = context
    }

    override fun setupView() {
        binding.lifecycleOwner = this
        binding.recyclerViewWatchMore.adapter = watchMoreAdapter
        binding.detailViewModel = viewModel
        initListener()
        observer()
    }

    private fun initListener() {
        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observer() {
        viewModel.video.observe(viewLifecycleOwner, Observer {
            loadVideo(it.key)
        })
    }

    private fun onItemMovieClick(item: Movie) {
        val action = PlayTrailerFragmentDirections.actionPlayTrailerFragmentToSelf(item.movieId)
        findNavController().navigate(action)
    }

    private fun loadVideo(youtubeId: String) {
        val youtubeView = binding.youtubeView
        youtubeView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(youtubeId, 0f)
                youtube = youTubePlayer
            }
        })
        youtubeView.addFullScreenListener(object : YouTubePlayerFullScreenListener {
            override fun onYouTubePlayerEnterFullScreen() {
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                binding.toolbarDetail.hide()
                navigationListener?.hideNav()
            }

            override fun onYouTubePlayerExitFullScreen() {
                requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                binding.toolbarDetail.show()
                navigationListener?.showNav()
            }
        })
    }

    override fun onPause() {
        super.onPause()
        youtube?.pause()
    }

    override fun onDestroy() {
        binding.youtubeView.release()
        super.onDestroy()
    }
}
