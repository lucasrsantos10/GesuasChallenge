package com.example.gesuaschallenge.register.sendForward.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.forEach
import com.example.gesuaschallenge.R
import com.example.gesuaschallenge.databinding.BottomDialogListBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ListBottomDialog: BottomSheetDialogFragment() {

    lateinit var binding: BottomDialogListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomDialogListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        val forwardOptions = arrayOf("CRAS", "CREAS", "Defensoria Publica", "Ministério Público", "Poder Judiciario")
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.listview_center_text, R.id.textItem, forwardOptions)
        binding.lvOptions.adapter = arrayAdapter
        binding.lvOptions.setOnItemClickListener { adapterView, item, _, _ ->
            adapterView.forEach {
                if (it is TextView) {
                    it.setTextColor(resources.getColor(R.color.gray, null))
                    it.setBackgroundColor(resources.getColor(R.color.white, null))
                }
            }
            if (item is TextView) {
                item.setTextColor(resources.getColor(R.color.black, null))
                item.setBackgroundColor(resources.getColor(R.color.selectionWhite, null))
            }
        }

        binding.btSave.setOnClickListener {
            dismiss()
        }
    }
}