package com.example.statsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.transition.AutoTransition
import androidx.transition.Scene
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.example.statsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            val endScene = Scene.getSceneForLayout(binding.root1, R.layout.second_scene, this)
            TransitionManager.go(endScene, AutoTransition().apply {
                duration = 2000
            })
        }
    }
}