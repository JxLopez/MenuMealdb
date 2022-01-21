package com.jxlopez.menumealdb.presentation.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jxlopez.menumealdb.api.Status
import com.jxlopez.menumealdb.databinding.FragmentCategoryListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment : Fragment() {
    private lateinit var binding: FragmentCategoryListBinding
    private val viewModel: CategoryListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryListBinding.inflate(inflater)
        observes()
        viewModel.getCategory()
        return binding.root
    }

    fun observes() {
        viewModel.res.observe(viewLifecycleOwner) {
            when(it.status){
                Status.SUCCESS -> {
                    it.data.let { res ->
                        Log.e("Categories:::","$res")
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