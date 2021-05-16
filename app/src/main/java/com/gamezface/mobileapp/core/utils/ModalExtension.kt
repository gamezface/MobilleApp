package com.gamezface.mobileapp.core.utils

import android.content.DialogInterface
import androidx.fragment.app.Fragment
import com.gamezface.mobileapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.showModalError(
    title: String? = null,
    description: String? = null,
    throwable: Throwable?,
    positiveButtonText: String? = null,
    action: DialogInterface.OnClickListener?
) {
    val dialog = MaterialAlertDialogBuilder(this.requireContext())
        .setTitle(title ?: this.getString(R.string.something_went_wrong))
        .setMessage(description ?: this.getString(R.string.service_unavailable))
        .setPositiveButton(
            positiveButtonText ?: this.getString(R.string.try_again), action
        )
        .setNeutralButton(this.getString(R.string.ok), null)
    dialog.show()
}
