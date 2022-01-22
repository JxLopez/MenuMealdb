package com.jxlopez.menumealdb.presentation.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jxlopez.menumealdb.api.Status
import com.jxlopez.menumealdb.databinding.FragmentCategoryListBinding
import com.jxlopez.menumealdb.utils.extensions.loadImageUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment : Fragment() {
    private lateinit var binding: FragmentCategoryListBinding
    private val viewModel: CategoryListViewModel by viewModels()
    private var categoriesAdapter: CategoryAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryListBinding.inflate(inflater)

        binding.rvCategories.layoutManager = LinearLayoutManager(requireActivity())

        categoriesAdapter?.setOnItemClickListener { category ->
            Log.e("categoriesAdapter::","Clic item category")
            findNavController().navigate(
                CategoryListFragmentDirections.actionCategoryListFragmentToMealListFragment(category)
            )
        }

        observes()
        viewModel.randomMeal()
        viewModel.getCategory()
        return binding.root
    }

    fun observes() {
        viewModel.categories.observe(viewLifecycleOwner) {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let { res ->
                        Log.e("Categories:::","$res")
                        categoriesAdapter = CategoryAdapter(res)
                        binding.rvCategories.adapter = categoriesAdapter
                        categoriesAdapter?.setOnItemClickListener { category ->
                            Log.e("categoriesAdapter::","Clic item category")
                            findNavController().navigate(
                                CategoryListFragmentDirections.actionCategoryListFragmentToMealListFragment(category)
                            )
                        }
                    }
                }
                Status.ERROR -> {
                    if(it.error?.errorCode == 401) {
                        //Toast.makeText(requireContext(), getString(R.string.token_expired_single), Toast.LENGTH_SHORT).show()
                        //albumsViewModel.resetToken()
                        //startActivity(Intent(requireContext(), LoginActivity::class.java))
                        //requireActivity().finish()
                    }
                }
            }
        }

        viewModel.mealRandom.observe(viewLifecycleOwner) {
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let { meal ->
                        binding.viewMealRandom.tvCategory.text = meal.strMeal
                        binding.viewMealRandom.ivPhotoCategory.loadImageUrl(meal.strMealThumb)
                    }
                }
                Status.ERROR -> {
                    if(it.error?.errorCode == 401) {
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