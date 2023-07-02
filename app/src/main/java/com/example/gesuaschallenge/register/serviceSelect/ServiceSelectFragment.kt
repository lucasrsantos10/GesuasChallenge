package com.example.gesuaschallenge.register.serviceSelect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gesuaschallenge.databinding.FragmentServiceSelectBinding

class ServiceSelectFragment : Fragment() {

    private lateinit var binding: FragmentServiceSelectBinding
    private val viewModel: ServiceSelectViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServiceSelectBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {

        binding.rgServices.setOnCheckedChangeListener { _, _ ->
            viewModel.serviceChoosed()
        }

        binding.btContinue.setOnClickListener {
            findNavController().navigate(ServiceSelectFragmentDirections.actionServiceSelectFragmentToSendForwardFragment())
        }
    }

    private fun initObservers() {
        viewModel.enableContinueButton.observe(viewLifecycleOwner) {
            binding.btContinue.isEnabled = it
        }

    }

}