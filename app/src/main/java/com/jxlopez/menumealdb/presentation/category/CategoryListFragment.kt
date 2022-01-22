package com.jxlopez.menumealdb.presentation.category

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jxlopez.menumealdb.R
import com.jxlopez.menumealdb.api.Status
import com.jxlopez.menumealdb.databinding.FragmentCategoryListBinding
import com.jxlopez.menumealdb.entities.MealEntity
import com.jxlopez.menumealdb.presentation.BaseFragment
import com.jxlopez.menumealdb.presentation.meal.MealAdapter
import com.jxlopez.menumealdb.utils.Constants
import com.jxlopez.menumealdb.utils.extensions.loadImageUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment : BaseFragment() {
    private lateinit var binding: FragmentCategoryListBinding
    private val viewModel: CategoryListViewModel by viewModels()
    private var categoriesAdapter: CategoryAdapter? = null
    private var mealsAdapter: MealAdapter? = null
    private var mealRandom: MealEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryListBinding.inflate(inflater)
        configureBar()
        configureRecyclers()
        setListeners()
        observes()
        setListeners()
        viewModel.randomMeal()
        viewModel.getCategory()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_search, menu)
        val search = menu.findItem(R.id.appSearchBar)
        val searchView = search.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText?.length?: 0 > 2) {
                    viewModel.searchMealByName(newText?.trim() ?: "")
                    binding.contentMain.visibility = View.GONE
                    binding.contentSearching.visibility = View.VISIBLE
                } else if(newText?.isEmpty() == true) {
                    binding.contentMain.visibility = View.VISIBLE
                    binding.contentSearching.visibility = View.GONE
                    mealsAdapter?.submitList(arrayListOf())
                }
                return true
            }
        })
        searchView.setOnClickListener {view ->  }
    }

    private fun configureBar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.welcome_categories_list_fragment)
        setHasOptionsMenu(true)
    }

    private fun configureRecyclers() {
        binding.rvCategories.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvMealsSearching.layoutManager = GridLayoutManager(requireActivity(), Constants.COUNT_SPAN)
        categoriesAdapter = CategoryAdapter()
        mealsAdapter = MealAdapter()
        binding.rvMealsSearching.adapter = mealsAdapter
        binding.rvCategories.adapter = categoriesAdapter
    }

    fun observes() {
        viewModel.categories.observe(viewLifecycleOwner) {
            when(it.status){
                Status.SUCCESS -> {
                    hideProgressBarCategory()
                    it.data?.let { categories ->
                        if (categories.isNotEmpty()) {
                            binding.tvError.visibility = View.GONE
                            categoriesAdapter?.submitList(categories)
                        } else {
                            binding.tvError.text =
                                getString(R.string.not_found_categories_list_fragment)
                            binding.tvError.visibility = View.VISIBLE
                        }
                    }
                }
                Status.ERROR -> {
                    hideProgressBarCategory()
                    binding.tvError.text = String.format(getString(R.string.not_found_categories_list_fragment), it.error?.errorMessage)
                    binding.tvError.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    showProgressBarCategory()
                }
            }
        }

        viewModel.mealRandom.observe(viewLifecycleOwner) {
            when(it.status){
                Status.SUCCESS -> {
                    hideProgressBarRandom()
                    it.data?.let { meal ->
                        mealRandom = meal
                        binding.tvTitleMealRandom.visibility = View.VISIBLE
                        binding.viewMealRandom.ivImageMeal.visibility = View.VISIBLE
                        binding.viewMealRandom.tvTitle.visibility = View.VISIBLE
                        binding.viewMealRandom.tvTitle.text = meal.strMeal
                        binding.viewMealRandom.ivImageMeal.loadImageUrl(meal.strMealThumb)
                    }
                }
                Status.ERROR -> {
                    hideProgressBarRandom()
                    binding.tvTitleMealRandom.visibility = View.GONE
                    binding.viewMealRandom.ivImageMeal.visibility = View.GONE
                    binding.viewMealRandom.tvTitle.visibility = View.GONE
                }
                Status.LOADING -> {
                    showProgressBarRandom()
                }
            }
        }

        viewModel.searchMeals.observe(viewLifecycleOwner) {
            when(it.status){
                Status.SUCCESS -> {
                    hideProgressBarSearching()
                    binding.tvErrorSearch.visibility = View.GONE
                    it.data?.let { meals ->
                        if(meals.isNotEmpty()) {
                            mealsAdapter?.submitList(meals)
                        } else {
                            binding.tvErrorSearch.visibility = View.VISIBLE
                            mealsAdapter?.submitList(arrayListOf())
                        }
                    }
                }
                Status.ERROR -> {
                    hideProgressBarSearching()
                    binding.tvErrorSearch.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    showProgressBarSearching()
                }
            }
        }
    }

    private fun setListeners() {
        binding.rvContentRandom.setOnClickListener {
            mealRandom?.let {
                findNavController().navigate(
                    CategoryListFragmentDirections.actionCategoryListFragmentToMealDetailFragment(it.idMeal)
                )
            }
        }
        mealsAdapter?.setOnItemClickListener { meal ->
            findNavController().navigate(
                CategoryListFragmentDirections.actionCategoryListFragmentToMealDetailFragment(
                    meal.idMeal
                )
            )
        }
        categoriesAdapter?.setOnItemClickListener { category ->
            findNavController().navigate(
                CategoryListFragmentDirections.actionCategoryListFragmentToMealListFragment(
                    category
                )
            )
        }
    }

    private fun hideProgressBarCategory() {
        binding.progressLoading.visibility = View.INVISIBLE
    }

    private fun showProgressBarCategory() {
        binding.progressLoading.visibility = View.VISIBLE
    }

    private fun hideProgressBarRandom() {
        binding.progressRandom.visibility = View.INVISIBLE
    }

    private fun showProgressBarRandom() {
        binding.progressRandom.visibility = View.VISIBLE
    }

    private fun hideProgressBarSearching() {
        binding.progressSearch.visibility = View.INVISIBLE
    }

    private fun showProgressBarSearching() {
        binding.progressSearch.visibility = View.VISIBLE
    }
}