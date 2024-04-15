package com.example.autodrivingcar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPartOne = findViewById<Button>(R.id.btnPartOne)
        val btnPartTwo = findViewById<Button>(R.id.btnPartTwo)

        btnPartOne.setOnClickListener {
            val intent = Intent(this, Part1Activity::class.java)
            startActivity(intent)
            Log.i("Part_one","Navigating to Part1Activity")
        }

        btnPartTwo.setOnClickListener {
            val intent = Intent(this, Part2Activity::class.java)
            startActivity(intent)
            Log.i("Part_two","Navigating to Part2Activity")
        }
    }
}