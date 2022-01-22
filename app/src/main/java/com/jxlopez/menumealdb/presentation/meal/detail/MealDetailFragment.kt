package com.jxlopez.menumealdb.presentation.meal.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jxlopez.menumealdb.api.Status
import com.jxlopez.menumealdb.databinding.FragmentMealDetailBinding
import com.jxlopez.menumealdb.entities.MealEntity
import com.jxlopez.menumealdb.presentation.BaseFragment
import com.jxlopez.menumealdb.utils.Util
import com.jxlopez.menumealdb.utils.extensions.loadImageUrl
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailFragment : BaseFragment() {
    private lateinit var binding: FragmentMealDetailBinding
    private val viewModel: MealDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealDetailBinding.inflate(inflater)
        enabledToolbar(binding.toolbar)
        observes()
        getArgs()
        return binding.root
    }

    private fun observes() {
        viewModel.meals.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    it.data?.let { meal ->
                        loadData(meal)
                    }
                }
                Status.ERROR -> {
                    hideProgressBar()
                    findNavController().popBackStack()

                }
                Status.LOADING -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun getArgs() {
        arguments?.let {
            val args = MealDetailFragmentArgs.fromBundle(it)
            viewModel.idMeal = args.idMeal
            viewModel.getMealDetail()
        } ?: also {
            findNavController().popBackStack()
        }
    }

    private fun loadData(meal: MealEntity) {
        binding.collapsingToolbar.title = meal.strMeal
        binding.tvRecipeIngredient.text = meal.strIngredients
        binding.tvRecipeInstruction.text = meal.strInstructions
        binding.backdrop.loadImageUrl(meal.strMealThumb)
        showVideo(meal.strYoutube)
        if(meal.strTags?.isNotEmpty() == true) {
            binding.tvTagsRecipe.text = meal.strTags
        } else {
            binding.contentTags.visibility = View.GONE
        }
    }

    private fun showVideo(strYoutube: String?) {
        val videoId = Util.getVideoIdForUrl(strYoutube)
        if(videoId != null) {
            lifecycle.addObserver(binding.youtubePlayerView)
            binding.youtubePlayerView.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.cueVideo(videoId, 0f)
                }
            })
        } else {
            binding.tvTitleVideo.visibility = View.GONE
            binding.youtubePlayerView.visibility = View.GONE
        }
    }

    private fun hideProgressBar() {
        binding.progress.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progress.visibility = View.VISIBLE
    }
}