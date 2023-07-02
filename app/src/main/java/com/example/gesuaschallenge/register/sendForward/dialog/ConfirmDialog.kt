package com.example.gesuaschallenge.register.sendForward.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.gesuaschallenge.R
import com.example.gesuaschallenge.databinding.DialogConfirmBinding

class ConfirmDialog: DialogFragment() {

    lateinit var binding: DialogConfirmBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogConfirmBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 50)
        dialog?.window?.setBackgroundDrawable(inset)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }

    private fun initClicks() {
        binding.btCancel.setOnClickListener {
            dismiss()
        }

        binding.btConfirm.setOnClickListener {
            dialog?.setCanceledOnTouchOutside(false)
            showConfirmationMessage()
        }
    }

    private fun showConfirmationMessage() {
        binding.tvMessage.text = getString(R.string.successful_forward)
        binding.btCancel.visibility = View.GONE
        binding.btConfirm.text = getString(R.string.back_to_register)
        binding.btConfirm.setOnClickListener {
            findNavController().clearBackStack(R.id.dataRegisterFragment)
        }
    }
}