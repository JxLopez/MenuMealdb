package com.jxlopez.menumealdb.presentation.meal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.jxlopez.menumealdb.api.Status
import com.jxlopez.menumealdb.databinding.FragmentMealListBinding
import com.jxlopez.menumealdb.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealListFragment : Fragment() {
    private lateinit var binding: FragmentMealListBinding
    private val viewModel: MealListViewModel by viewModels()
    private var mealsAdapter: MealAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMealListBinding.inflate(inflater)
        binding.rvMeals.layoutManager = GridLayoutManager(requireActivity(), Constants.COUNT_SPAN)
        observes()
        getArgs()
        return binding.root
    }

    private fun getArgs() {
        arguments?.let {
            val args = MealListFragmentArgs.fromBundle(it)
            val category = args.category
            viewModel.category = category
            viewModel.getMealsByCategory()
            //binding.collapsingToolbar.title = artist.name
        } ?: also {
            //findNavController().popBackStack()
        }
    }

    fun observes() {
        viewModel.meals.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { res ->
                        Log.e("Categories:::", "$res")
                        mealsAdapter = MealAdapter(res)
                        binding.rvMeals.adapter = mealsAdapter
                    }
                }
                Status.ERROR -> {
                    if (it.error?.errorCode == 401) {
                        //Toast.makeText(requireContext(), getString(R.string.token_expired_single), Toast.LENGTH_SHORT).show()
                        //albumsViewModel.resetToken()
                        //startActivity(Intent(requireContext(), LoginActivity::class.java))
                        //requireActivity().finish()
                    }
                }
            }
        }
    }
}