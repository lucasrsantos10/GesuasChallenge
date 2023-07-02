package com.example.gesuaschallenge.register.dataRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gesuaschallenge.R
import com.example.gesuaschallenge.databinding.FragmentDataRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataRegisterFragment : Fragment() {

    private lateinit var binding: FragmentDataRegisterBinding
    private val viewModel: DataRegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDataRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.btRegister.setOnClickListener {
            viewModel.registerClicked()
        }

        binding.etName.doAfterTextChanged {
            viewModel.setName(it.toString())
        }
    }

    private fun initObservers() {
        viewModel.goNextView.observe(viewLifecycleOwner) {
            findNavController().navigate(DataRegisterFragmentDirections.actionDataRegisterFragmentToServiceSelectFragment())
        }

        viewModel.birthDateTextWatcher.observe(viewLifecycleOwner) {
            binding.etBirthDate.addTextChangedListener(it)
        }

        viewModel.cpfTextWatcher.observe(viewLifecycleOwner) {
            binding.etCPF.addTextChangedListener(it)
        }

        viewModel.cellphoneTextWatcher.observe(viewLifecycleOwner) {
            binding.etCellphone.addTextChangedListener(it)
        }

        viewModel.cellphone.observeForever {
            binding.etCellphone.setText(it)
            binding.etCellphone.setSelection(it.length)
        }

        viewModel.cpf.observeForever {
            binding.etCPF.setText(it)
            binding.etCPF.setSelection(it.length)
        }

        viewModel.birthDate.observe(viewLifecycleOwner) {
            binding.etBirthDate.setText(it)
            binding.etBirthDate.setSelection(it.length)
        }

        viewModel.showErrorOnCPF.observe(viewLifecycleOwner) {
            binding.tilCPF.error = if (!it) null else getString(R.string.cpf_invalido)
        }

        viewModel.showErrorOnCellphone.observe(viewLifecycleOwner) {
            binding.tilCellphone.error = if (!it) null else getString(R.string.cpf_invalido)
        }

        viewModel.showErrorOnName.observe(viewLifecycleOwner) {
            binding.tilName.error = if (!it) null else getString(R.string.cpf_invalido)

        }

        viewModel.registerButtonEnabled.observeForever {
            binding.btRegister.isEnabled = it
        }
    }

}