package com.example.gesuaschallenge.register.sendForward

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gesuaschallenge.R
import com.example.gesuaschallenge.databinding.FragmentSendFowardBinding
import com.example.gesuaschallenge.register.sendForward.dialog.ConfirmDialog
import com.example.gesuaschallenge.register.sendForward.dialog.ListBottomDialog
import com.example.gesuaschallenge.register.sendForward.dialog.ReasonsBottomDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendForwardFragment : Fragment() {

    private lateinit var binding: FragmentSendFowardBinding
    private val viewModel: SendForwardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSendFowardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.btCancel.setOnClickListener {
            findNavController().clearBackStack(R.id.dataRegisterFragment)
        }

        binding.vForward.setOnClickListener {
            showForwardDialog()
        }

        binding.vReason.setOnClickListener {
            showReasonsDialog()
        }

        binding.btContinue.setOnClickListener {
            showConfirmDialog()
        }
    }

    private fun showConfirmDialog() {
        val dialog = ConfirmDialog()
        dialog.show(childFragmentManager, "ConfirmDialog")

    }

    private fun showReasonsDialog() {
        val dialog = ReasonsBottomDialog()
        dialog.show(childFragmentManager, "Reasons")
        dialog.reasonAdded = {
            viewModel.setReasons()
        }
    }

    private fun showForwardDialog() {
        val dialog = ListBottomDialog()
        dialog.show(childFragmentManager, "ForwardOptions")
    }

    private fun initObservers() {
        viewModel.forwardButtonEnabled.observe(viewLifecycleOwner) {
            binding.btContinue.isEnabled = it
        }

        viewModel.name.observe(viewLifecycleOwner) {
            binding.tvName.text = getString(R.string.name, it)
        }

        viewModel.age.observe(viewLifecycleOwner) {
            binding.tvAge.text = getString(R.string.age, it)
        }

        viewModel.cpf.observe(viewLifecycleOwner) {
            binding.tvCPF.text = getString(R.string.cpf, it)
        }

        viewModel.birthDate.observe(viewLifecycleOwner) {
            binding.tvBirthDate.text = getString(R.string.birthDate, it)
        }
    }

}