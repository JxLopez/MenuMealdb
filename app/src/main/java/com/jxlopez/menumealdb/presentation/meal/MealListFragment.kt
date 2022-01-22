package com.jxlopez.menumealdb.presentation.meal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jxlopez.menumealdb.api.Status
import com.jxlopez.menumealdb.databinding.FragmentMealListBinding
import com.jxlopez.menumealdb.presentation.BaseFragment
import com.jxlopez.menumealdb.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealListFragment : BaseFragment() {
    private lateinit var binding: FragmentMealListBinding
    private val viewModel: MealListViewModel by viewModels()
    private var mealsAdapter: MealAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealListBinding.inflate(inflater)
        enabledToolbar(binding.toolbar)
        configureRecycler()
        observes()
        getArgs()
        return binding.root
    }

    private fun getArgs() {
        arguments?.let {
            val args = MealListFragmentArgs.fromBundle(it)
            val category = args.category
            viewModel.category = category
            binding.toolbar.title = category.strCategory
            viewModel.getMealsByCategory()
        } ?: also {
            findNavController().popBackStack()
        }
    }

    private fun configureRecycler() {
        binding.rvMeals.layoutManager = GridLayoutManager(requireActivity(), Constants.COUNT_SPAN)
        mealsAdapter = MealAdapter()
        binding.rvMeals.adapter = mealsAdapter
        binding.rvMeals
        mealsAdapter?.setOnItemClickListener { meal ->
            findNavController().navigate(
                MealListFragmentDirections.actionMealListFragmentToMealDetailFragment(meal.idMeal)
            )
        }
    }

    private fun observes() {
        viewModel.meals.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgressBar()
                    binding.tvError.visibility = View.GONE
                    it.data?.let { meals ->
                        mealsAdapter?.submitList(meals)
                    }
                }
                Status.ERROR -> {
                    hideProgressBar()
                    binding.tvError.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.progressLoading.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressLoading.visibility = View.VISIBLE
    }
}