package com.gamezface.mobileapp.core.utils

import androidx.fragment.app.Fragment
import com.gamezface.mobileapp.R
import com.gamezface.mobileapp.modules.home.models.Schedules

fun Fragment.mapSchedule(schedules: Schedules?): String? {
    schedules?.let { schedules ->
        val sunday = schedules.sunday?.let {
            getString(R.string.sunday).plus(": ").plus(it.open)
                .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.close)).plus("\n")
        } ?: ""
        val monday = schedules.monday?.let {
            getString(R.string.monday).plus(": ").plus(it.open)
                .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.close)).plus("\n")
        } ?: ""
        val tuesday = schedules.tuesday?.let {
            getString(R.string.tuesday).plus(": ").plus(it.open)
                .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.close)).plus("\n")
        } ?: ""
        val wednesday = schedules.wednesday?.let {
            getString(R.string.wednesday).plus(": ").plus(it.open)
                .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.close)).plus("\n")
        } ?: ""
        val thursday = schedules.thursday?.let {
            getString(R.string.thursday).plus(": ").plus(it.open)
                .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.close)).plus("\n")
        } ?: ""
        val friday = schedules.friday?.let {
            getString(R.string.friday).plus(": ").plus(it.open)
                .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.close)).plus("\n")
        } ?: ""
        val saturday = schedules.saturday?.let {
            getString(R.string.saturday).plus(": ").plus(it.open)
                .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.close))
        } ?: ""
        return "$sunday$monday$tuesday$wednesday$thursday$friday$saturday"
    } ?: return ""
}