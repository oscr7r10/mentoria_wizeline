package com.cursokotlin.retrofitkotlinexample.presentation.view.searchdogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursokotlin.retrofitkotlinexample.databinding.FragmentSearchDogsBinding
import com.cursokotlin.retrofitkotlinexample.domain.model.DogsModel
import com.cursokotlin.retrofitkotlinexample.presentation.adapter.DogsAdapter
import com.cursokotlin.retrofitkotlinexample.presentation.viewmodel.SearchDogsViewModel
import com.cursokotlin.retrofitkotlinexample.tools.ResultAPI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDogsFragment : Fragment() {

    lateinit var imagesPuppies: List<String>
    lateinit var dogsAdapter: DogsAdapter
    private val viewModel: SearchDogsViewModel by viewModels()
    private lateinit var binding: FragmentSearchDogsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchDogsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBreed.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                p0?.let { viewModel.searchDogByName(it.lowercase()) }
                return true
            }

            override fun onQueryTextChange(p0: String?) = true
        })

        viewModel.searchDogService.observe(viewLifecycleOwner) {
            when (val result = it) {
                is ResultAPI.OnFailure -> showErrorDialog()
                is ResultAPI.OnLoading -> {}
                is ResultAPI.OnSuccess -> initCharacter(result.data)
            }
            hideKeyboard()
        }
    }

    private fun initCharacter(puppies: DogsModel) {
        imagesPuppies = puppies.images
        dogsAdapter = DogsAdapter(imagesPuppies)
        binding.rvDogs.setHasFixedSize(true)
        binding.rvDogs.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDogs.adapter = dogsAdapter
    }

    private fun showErrorDialog() {
        Toast.makeText(
            requireContext(),
            "Ha ocurrido un error, inténtelo de nuevo.",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }
}