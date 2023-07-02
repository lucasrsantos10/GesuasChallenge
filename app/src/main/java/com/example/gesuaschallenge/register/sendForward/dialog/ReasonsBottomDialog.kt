package com.example.gesuaschallenge.register.sendForward.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.gesuaschallenge.databinding.BottomDialogReasonsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReasonsBottomDialog: BottomSheetDialogFragment() {

    lateinit var binding: BottomDialogReasonsBinding
    var reasonAdded: (() -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomDialogReasonsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.tvCancel.setOnClickListener {
            dismiss()
        }
        binding.tvSave.setOnClickListener {
            if (!binding.etReason.text.isNullOrBlank()) {
                reasonAdded?.invoke()
                dismiss()
            }else {
                Toast.makeText(context, "Escreva um motivo", Toast.LENGTH_LONG).show()
            }
        }
    }
}
