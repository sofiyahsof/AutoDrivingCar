package com.example.autodrivingcar

class Car(private var width: Int, private var height: Int) {
    private var x: Int = 0
    private var y: Int = 0
    private var direction: Char = 'N'

    fun setPosition(x: Int, y: Int, direction: Char) {
        if (x in 0..<width && y in 0..<height){
            this.x = x
            this.y = y
            this.direction = direction
        } else {
            throw IllegalArgumentException("Invalid x and y coordinates")
        }
    }

    fun executeCommands(commands: String) {
        for (command in commands) {
            if (command=='L' || command=='R' || command=='F'){
                when (command) {
                    'L' -> turnLeft()
                    'R' -> turnRight()
                    'F' -> moveForward()
                }
            } else {
                throw IllegalArgumentException("Invalid command. Please only input L, R, or F.")
            }
        }
    }

    fun turnLeft() {
        when (direction) {
            'N' -> direction = 'W'
            'W' -> direction = 'S'
            'S' -> direction = 'E'
            'E' -> direction = 'N'
        }
    }

    fun turnRight() {
        when (direction) {
            'N' -> direction = 'E'
            'E' -> direction = 'S'
            'S' -> direction = 'W'
            'W' -> direction = 'N'
        }
    }

    fun moveForward() {
        when (direction) {
            'N' -> if (y < height - 1) y++
            'E' -> if (x < width - 1) x++
            'S' -> if (y > 0) y--
            'W' -> if (x > 0) x--
        }
    }

    fun getPosition(): String {
        return "$x $y $direction"
    }
}