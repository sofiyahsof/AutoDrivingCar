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

class Part2Activity: AppCompatActivity() {

    lateinit var setWidth: EditText
    lateinit var setHeight: EditText

    lateinit var setXPosition1: EditText
    lateinit var setYPosition1: EditText
    lateinit var direction1: Spinner
    lateinit var commands1: EditText

    lateinit var setXPosition2: EditText
    lateinit var setYPosition2: EditText
    lateinit var direction2: Spinner
    lateinit var commands2: EditText

    lateinit var collisionResult: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part2)

        initViews()

        val buttonCalculate2 = findViewById<Button>(R.id.buttonCalculate2)
        val btnBackToMain2 = findViewById<ImageView>(R.id.btnBackToMain2)

        buttonCalculate2.setOnClickListener {
            try {
                moveCars()
            } catch (e: IllegalArgumentException) {
                collisionResult.text = "Error: ${e.message}"
                collisionResult.setTextColor(Color.RED)
                Log.e("IllegalArgumentException", "Error: ${e.message}")
            }
        }

        btnBackToMain2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Log.i("Back_button","Returning to main activity")
        }
    }

    private fun initViews() {
        // Initialize all views here
        setWidth = findViewById(R.id.setWidth2)
        setHeight = findViewById(R.id.setHeight2)

        collisionResult = findViewById(R.id.collisionResult)

        // Car 1 fields
        setXPosition1 = findViewById(R.id.xPositionCarA)
        setYPosition1 = findViewById(R.id.yPositionCarA)
        direction1 = findViewById(R.id.directionCarA)
        commands1 = findViewById(R.id.commandsCarA)

        // Car 2 fields
        setXPosition2 = findViewById(R.id.xPositionCarB)
        setYPosition2 = findViewById(R.id.yPositionCarB)
        direction2 = findViewById(R.id.directionCarB)
        commands2 = findViewById(R.id.commandsCarB)
    }

    // Check that fields are not empty
    fun checkForEmptyFields() {
        val width = setWidth.text.toString()
        val height = setHeight.text.toString()

        val xPosition1 = setXPosition1.text.toString()
        val yPosition1 = setYPosition1.text.toString()
        val commands1 = commands1.text.toString()

        val xPosition2 = setXPosition2.text.toString()
        val yPosition2 = setYPosition2.text.toString()
        val commands2 = commands2.text.toString()

        if (width.isEmpty() || height.isEmpty() || xPosition1.isEmpty()
            || yPosition1.isEmpty() || commands1.isEmpty() || xPosition2.isEmpty()
            || yPosition2.isEmpty() || commands2.isEmpty()) {
            throw IllegalArgumentException("Please fill in all fields")
        }
    }

    // Check that both cars are not at the same starting point
    fun checkStartingPoints() {
        val xPosition1 = setXPosition1.text.toString()
        val yPosition1 = setYPosition1.text.toString()

        val xPosition2 = setXPosition2.text.toString()
        val yPosition2 = setYPosition2.text.toString()

        if (xPosition1 == xPosition2 && yPosition1 == yPosition2) {
            throw IllegalArgumentException("Both cars cannot be at the same starting position. Please input new coordinates.")
        }
    }

    @SuppressLint("SetTextI18n")
    fun moveCars() {
        try {
            checkForEmptyFields()
            checkStartingPoints()
            val width = setWidth.text.toString().toInt()
            val height = setHeight.text.toString().toInt()

            val xPosition1 = setXPosition1.text.toString().toInt()
            val yPosition1 = setYPosition1.text.toString().toInt()
            val direction1 = direction1.selectedItem.toString().first()
            val commands1 = commands1.text.toString()

            val xPosition2 = setXPosition2.text.toString().toInt()
            val yPosition2 = setYPosition2.text.toString().toInt()
            val direction2 = direction2.selectedItem.toString().first()
            val commands2 = commands2.text.toString()

            val car1 = Car(width, height)
            car1.setPosition(xPosition1, yPosition1, direction1)

            val car2 = Car(width, height)
            car2.setPosition(xPosition2, yPosition2, direction2)

            // Take the length of the longer command for the for-loop,
            // in case the command for one car is longer than the other
            val maxLength = maxOf(commands1.length, commands2.length)

            // Check for collision after each command
            for (i in 0 until maxLength) {
                // Get the command for each car, or empty string if index out of bounds
                val command1 = if (i < commands1.length) commands1[i].toString() else ""
                val command2 = if (i < commands2.length) commands2[i].toString() else ""

                car1.executeCommands(command1)
                car2.executeCommands(command2)

                if (checkCollision(car1, car2)) {
                    val car1Position = car1.getPosition()

                    // Split position string into x, y, and direction
                    val (car1X, car1Y, _) = car1Position.split(" ")

                    collisionResult.text = "Collision at $car1X $car1Y \nAt step no.: ${i + 1}"
                    collisionResult.setTextColor(Color.BLACK)
                    Log.i("collision", "Collision at $car1X $car1Y \nAt step no.: ${i + 1}")
                    break
                } else {
                    collisionResult.text = "No collision"
                    collisionResult.setTextColor(Color.BLACK)
                    Log.i("No_collision", "No collision")
                }
            }
        } catch (e: IllegalArgumentException) {
            collisionResult.text = "Error: ${e.message}"
            collisionResult.setTextColor(Color.RED)
            Log.e("IllegalArgumentException", "Error: ${e.message}")
        }
    }

    fun checkCollision(car1: Car, car2: Car): Boolean {
        val car1Position = car1.getPosition()
        val car2Position = car2.getPosition()

        // Split position string into x, y, and direction
        val (car1X, car1Y, _) = car1Position.split(" ")
        val (car2X, car2Y, _) = car2Position.split(" ")

        // Check that x and y positions are the same
        return car1X == car2X && car1Y == car2Y
    }
}