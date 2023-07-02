package com.example.gesuaschallenge.domain.mask

import android.text.Editable
import android.text.TextWatcher
import javax.inject.Inject

class Mask @Inject constructor() {

    var isUpdating : Boolean = false
    var oldString : String = ""
    var lastMaskUsed = ""

    private fun replaceChars(cpfFull : String) : String{
        return cpfFull.replace("", "").replace("-", "")
            .replace("(", "").replace(")", "")
            .replace("/", "").replace(" ", "")
            .replace("*", "").replace(".","")
    }

    fun applyMask(mask: String, textChanged: (String) -> Unit): TextWatcher {
        return object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val str =
                    replaceChars(
                        s.toString()
                    )
                var newText = ""

                if (count == 0)//is deleting
                    isUpdating = true
                if (isUpdating)
                {
                    oldString = str
                    isUpdating = false
                    return
                }

                var i = 0
                for (m : Char in mask.toCharArray())
                {
                    if (m != '#' && str.length >= oldString.length) {
                        newText += m
                        continue
                    }
                    try {
                        newText += str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                isUpdating = true
                lastMaskUsed = mask
                textChanged(newText)
            }

            override fun afterTextChanged(text: Editable) {
            }
        }
    }


}