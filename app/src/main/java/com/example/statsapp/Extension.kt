package com.example.statsapp

import android.content.Context

    fun Int.px(context: Context): Int = (this * context.resources.displayMetrics.density).toInt()

    fun Int.dp(context: Context): Int = (this / context.resources.displayMetrics.density).toInt()