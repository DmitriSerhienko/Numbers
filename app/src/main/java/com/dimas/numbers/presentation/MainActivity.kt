package com.dimas.numbers.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dimas.numbers.R
import com.dimas.numbers.data.db.FactRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}