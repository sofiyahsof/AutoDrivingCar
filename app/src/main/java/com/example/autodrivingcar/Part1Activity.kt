package com.example.autodrivingcar

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Part1Activity: AppCompatActivity() {

    lateinit var setWidth: EditText
    lateinit var setHeight: EditText
    lateinit var setXPosition: EditText
    lateinit var setYPosition: EditText
    lateinit var direction: Spinner
    lateinit var commands: EditText
    lateinit var positionResult: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part1)

        initViews()

        val buttonCalculate1 = findViewById<Button>(R.id.buttonCalculate1)
        val btnBackToMain1 = findViewById<ImageView>(R.id.btnBackToMain1)

        buttonCalculate1.setOnClickListener {
            try {
                moveCar()
            } catch (e: IllegalArgumentException) {
                positionResult.text = "Error: ${e.message}"
                positionResult.setTextColor(Color.RED)
                Log.e("IllegalArgumentException", "Error: ${e.message}")
            }
        }

        btnBackToMain1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Log.i("Back_button","Returning to main activity")
        }
    }

    private fun initViews() {
        // Initialize all views here
        setWidth = findViewById(R.id.setWidth1)
        setHeight = findViewById(R.id.setHeight1)
        setXPosition = findViewById(R.id.xPosition)
        setYPosition = findViewById(R.id.yPosition)
        direction = findViewById(R.id.direction)
        commands = findViewById(R.id.commands)
        positionResult = findViewById(R.id.positionResult)
    }

    // Check that fields are not empty
    fun checkForEmptyFields() {
        val width = setWidth.text.toString()
        val height = setHeight.text.toString()
        val xPosition = setXPosition.text.toString()
        val yPosition = setYPosition.text.toString()
        val commands = commands.text.toString()

        if (width.isEmpty() || height.isEmpty() || xPosition.isEmpty() || yPosition.isEmpty() || commands.isEmpty()) {
            throw IllegalArgumentException("Please fill in all fields")
        }
    }

    @SuppressLint("SetTextI18n")
    fun moveCar() {
        try {
            checkForEmptyFields()
            val width = setWidth.text.toString().toInt()
            val height = setHeight.text.toString().toInt()
            val xPosition = setXPosition.text.toString().toInt()
            val yPosition = setYPosition.text.toString().toInt()
            val direction = direction.selectedItem.toString().first()
            val commands = commands.text.toString()

            val car = Car(width, height)
            car.setPosition(xPosition, yPosition, direction)
            car.executeCommands(commands)

            val finalPosition = car.getPosition()
            positionResult.text = finalPosition
            positionResult.setTextColor(Color.BLACK)
            Log.i("finalPosition","Car's final position is at: $finalPosition")

        } catch (e: IllegalArgumentException) {
            positionResult.text = "Error: ${e.message}"
            positionResult.setTextColor(Color.RED)
            Log.e("IllegalArgumentException", "Error: ${e.message}")
        }
    }
}
