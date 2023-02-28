package com.dimas.numbers.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dimas.numbers.R
import com.dimas.numbers.data.network.NetworkResult
import com.dimas.numbers.databinding.StartFragmentBinding
import com.dimas.numbers.presentation.adapter.FactAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment() {

    private var _binding: StartFragmentBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("StartFragmentBinding == null")
    private val viewModel: FactsViewModel by viewModels()
    private val factAdapter by lazy { FactAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = StartFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() = with(binding) {
        super.onStart()
        initAdapter()
        observeProgress()
        viewModel.readFacts.observe(requireActivity()) {
            factAdapter.setData(it.asReversed())
        }
        randomFactButton.setOnClickListener {
            viewModel.getRandomFact()
        }
        binding.getFactButton.setOnClickListener {
            val editText = inputNumber.text.toString()
            if (editText.isNotEmpty()) {
                viewModel.getInputFact(editText.toLong())
            } else {
                Toast.makeText(requireContext(), R.string.input_number, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun observeProgress() {
        viewModel.myResponse.observe(this){
            when(it){
                is NetworkResult.Success -> {
                    binding.pgBarLayout.visibility = View.GONE
                }
                is NetworkResult.Error -> {
                    binding.pgBarLayout.visibility = View.GONE
                }
                is NetworkResult.Loading -> {
                    binding.pgBarLayout.visibility = View.VISIBLE
                }
            }
        }
    }


    private fun initAdapter() {
        binding.rcView.apply {
            adapter = factAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}