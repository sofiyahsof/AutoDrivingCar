package com.example.autodrivingcar

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class CarTest {

    @Test
    fun `test setPosition is valid`() {
        val car = Car(5, 5)
        car.setPosition(2, 3, 'N')
        assertEquals("2 3 N", car.getPosition())
    }

    @Test
    fun `test setPosition when invalid`() {
        val car = Car(5, 5)
        val exception = assertThrows(IllegalArgumentException::class.java) {
            car.setPosition(6, 6, 'N')
        }
        assertEquals("Invalid x and y coordinates", exception.message)
    }

    @Test
    fun testExecuteCommands() {
        val car = Car(10, 10)
        car.setPosition(0, 0, 'N')
        car.executeCommands("FFLR")
        assertEquals("0 2 N", car.getPosition())
    }

    @Test
    fun testInvalidCommand() {
        val car = Car(10, 10)
        car.setPosition(0, 0, 'N')
        val exception = assertThrows(IllegalArgumentException::class.java) {
            car.executeCommands("FFGX")
        }
        assertEquals("Invalid command. Please only input L, R, or F.", exception.message)
    }

    @Test
    fun testTurnLeft() {
        val car = Car(5, 5)
        car.setPosition(2, 3, 'N')
        car.turnLeft()
        assertEquals("2 3 W", car.getPosition())
    }

    @Test
    fun testTurnRight() {
        val car = Car(5, 5)
        car.setPosition(2, 3, 'N')
        car.turnRight()
        assertEquals("2 3 E", car.getPosition())
    }

    @Test
    fun testMoveForward() {
        val car = Car(5, 5)
        car.setPosition(2, 3, 'N')
        car.moveForward()
        assertEquals("2 4 N", car.getPosition())
    }
}
