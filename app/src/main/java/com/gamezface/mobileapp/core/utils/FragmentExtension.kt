package com.gamezface.mobileapp.core.utils

import androidx.fragment.app.Fragment
import com.gamezface.mobileapp.R
import com.gamezface.mobileapp.modules.home.models.Schedules

fun Fragment.mapSchedule(schedules: Schedules?): String? {
    schedules?.let {
        val sunday = getString(R.string.sunday).plus(": ").plus(it.sunday.open)
            .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.sunday.close))
        val monday = getString(R.string.monday).plus(": ").plus(it.monday.open)
            .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.monday.close))
        val tuesday = getString(R.string.tuesday).plus(": ").plus(it.tuesday.open)
            .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.tuesday.close))
        val wednesday = getString(R.string.wednesday).plus(": ").plus(it.wednesday.open)
            .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.wednesday.close))
        val thursday = getString(R.string.thursday).plus(": ").plus(it.thursday.open)
            .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.thursday.close))
        val friday = getString(R.string.friday).plus(": ").plus(it.friday.open)
            .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.friday.close))
        val saturday = getString(R.string.saturday).plus(": ").plus(it.saturday.open)
            .plus(" ").plus(getString(R.string.to).plus(" ").plus(it.saturday.close))
        return "$sunday\n$monday\n$tuesday\n$wednesday\n$thursday\n$friday\n$saturday\n"
    } ?: return ""
}