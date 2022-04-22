package com.example.statsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stats = findViewById<StatsView>(R.id.stats)
        stats.data = listOf(
            500f,
            500f,
            500f,
            500f
        )
    }
}