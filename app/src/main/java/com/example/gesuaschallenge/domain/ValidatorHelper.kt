package com.example.gesuaschallenge.domain

import javax.inject.Inject

class ValidatorHelper @Inject constructor() {

    fun validateCPF(cpf: String?): Boolean {
        if (cpf.isNullOrBlank()) {
            return false
        }

        val cpfClean = cpf.replace(".", "").replace("-", "")

        if (cpfClean == "00000000000" || cpfClean == "11111111111"
            || cpfClean == "22222222222" || cpfClean == "33333333333"
            || cpfClean == "44444444444" || cpfClean == "55555555555"
            || cpfClean == "66666666666" || cpfClean == "77777777777"
            || cpfClean == "88888888888" || cpfClean == "99999999999"
        ) {
            return false
        }

        //## check if size is eleven
        if (cpfClean.length != 11)
            return false

        //## check if is number
        try {
            cpfClean.toLong()
        } catch (e: Exception) {
            return false
        }

        //continue
        val dvCurrent10 = cpfClean.substring(9, 10).toInt()
        val dvCurrent11 = cpfClean.substring(10, 11).toInt()

        //the sum of the nine first digits determines the tenth digit
        val cpfNineFirst = IntArray(9)
        var i = 9
        while (i > 0) {
            cpfNineFirst[i - 1] = cpfClean.substring(i - 1, i).toInt()
            i--
        }
        //multiple the nine digits for your weights: 10,9..2
        val sumProductNine = IntArray(9)
        var weight = 10
        var position = 0
        while (weight >= 2) {
            sumProductNine[position] = weight * cpfNineFirst[position]
            weight--
            position++
        }
        //Verify the nineth digit
        var dvForTenthDigit = sumProductNine.sum() % 11
        dvForTenthDigit = 11 - dvForTenthDigit //rule for tenth digit
        if (dvForTenthDigit > 9)
            dvForTenthDigit = 0
        if (dvForTenthDigit != dvCurrent10)
            return false

        //### verify tenth digit
        val cpfTenFirst = cpfNineFirst.copyOf(10)
        cpfTenFirst[9] = dvCurrent10
        //multiple the nine digits for your weights: 10,9..2
        val sumProductTen = IntArray(10)
        var w = 11
        var p = 0
        while (w >= 2) {
            sumProductTen[p] = w * cpfTenFirst[p]
            w--
            p++
        }
        //Verify the nineth digit
        var dvForeleventhDigit = sumProductTen.sum() % 11
        dvForeleventhDigit = 11 - dvForeleventhDigit //rule for tenth digit
        if (dvForeleventhDigit > 9)
            dvForeleventhDigit = 0
        if (dvForeleventhDigit != dvCurrent11)
            return false

        return true
    }

    fun validateCellphone(cellphone: String?): Boolean {
        return (!cellphone.isNullOrBlank() && cellphone.length > 14)
    }
}