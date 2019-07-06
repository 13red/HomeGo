package com.test.homego.common

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class InfoFragmentDialog(val messageId : Int) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        return activity?.run {
            val builder = AlertDialog.Builder(this)
                .setMessage(messageId)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}