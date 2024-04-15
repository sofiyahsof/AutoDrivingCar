package com.example.autodrivingcar

import android.text.Editable
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Part2ActivityTest {
    private lateinit var part2Activity: Part2Activity

    @Mock
    private lateinit var width: EditText
    @Mock
    private lateinit var height: EditText

    @Mock
    private lateinit var xPosition1: EditText
    @Mock
    private lateinit var yPosition1: EditText
    @Mock
    private lateinit var direction1: Spinner
    @Mock
    private lateinit var commands1: EditText

    @Mock
    private lateinit var xPosition2: EditText
    @Mock
    private lateinit var yPosition2: EditText
    @Mock
    private lateinit var direction2: Spinner
    @Mock
    private lateinit var commands2: EditText

    @Mock
    private lateinit var collisionResult: TextView

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        part2Activity = Part2Activity()

        part2Activity.setWidth = width
        part2Activity.setHeight = height

        part2Activity.setXPosition1 = xPosition1
        part2Activity.setYPosition1 = yPosition1
        part2Activity.direction1 = direction1
        part2Activity.commands1 = commands1

        part2Activity.setXPosition2 = xPosition2
        part2Activity.setYPosition2 = yPosition2
        part2Activity.direction2 = direction2
        part2Activity.commands2 = commands2

        part2Activity.collisionResult = collisionResult
    }

    @Test
    fun `test checkForEmptyFields with non-empty fields`() {
        // Mock the EditText fields and set their text values
        Mockito.`when`(width.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))
        Mockito.`when`(height.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))

        // Mock fields for car 1
        Mockito.`when`(xPosition1.text).thenReturn(Editable.Factory.getInstance().newEditable("5"))
        Mockito.`when`(yPosition1.text).thenReturn(Editable.Factory.getInstance().newEditable("5"))
        Mockito.`when`(commands1.text).thenReturn(Editable.Factory.getInstance().newEditable("F"))

        // Mock fields for car 2
        Mockito.`when`(xPosition2.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        Mockito.`when`(yPosition2.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        Mockito.`when`(commands2.text).thenReturn(Editable.Factory.getInstance().newEditable("FFR"))

        try {
            part2Activity.checkForEmptyFields()
        } catch (e: IllegalArgumentException) {
            // If any exception occurs, fail the test
            assertEquals("An IllegalArgumentException should not be thrown", true, false)
        }
    }

    @Test
    fun `test checkForEmptyFields with empty fields`() {
        // Mock the EditText fields and set their text values
        Mockito.`when`(width.text).thenReturn(Editable.Factory.getInstance().newEditable(""))
        Mockito.`when`(height.text).thenReturn(Editable.Factory.getInstance().newEditable(""))

        // Mock fields for car 1
        Mockito.`when`(xPosition1.text).thenReturn(Editable.Factory.getInstance().newEditable(""))
        Mockito.`when`(yPosition1.text).thenReturn(Editable.Factory.getInstance().newEditable(""))
        Mockito.`when`(commands1.text).thenReturn(Editable.Factory.getInstance().newEditable(""))

        // Mock fields for car 2
        Mockito.`when`(xPosition2.text).thenReturn(Editable.Factory.getInstance().newEditable(""))
        Mockito.`when`(yPosition2.text).thenReturn(Editable.Factory.getInstance().newEditable(""))
        Mockito.`when`(commands2.text).thenReturn(Editable.Factory.getInstance().newEditable(""))

        assertThrows(IllegalArgumentException::class.java) {
            part2Activity.checkForEmptyFields()
        }
    }

    @Test
    fun testCheckStartingPoints() {
        // Mock the EditText fields and set their text values
        Mockito.`when`(width.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))
        Mockito.`when`(height.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))

        // Mock fields for car 1
        Mockito.`when`(xPosition1.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        Mockito.`when`(yPosition1.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        Mockito.`when`(commands1.text).thenReturn(Editable.Factory.getInstance().newEditable("F"))

        // Mock fields for car 2
        Mockito.`when`(xPosition2.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        Mockito.`when`(yPosition2.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        Mockito.`when`(commands2.text).thenReturn(Editable.Factory.getInstance().newEditable("FFR"))

        assertThrows(IllegalArgumentException::class.java) {
            part2Activity.checkStartingPoints()
        }
    }

    @Test
    fun `test moveCars with no collision`() {
        Mockito.`when`(width.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))
        Mockito.`when`(height.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))

        Mockito.`when`(xPosition1.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        Mockito.`when`(yPosition1.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        Mockito.`when`(direction1.selectedItem).thenReturn("N")
        Mockito.`when`(commands1.text).thenReturn(Editable.Factory.getInstance().newEditable("F"))

        Mockito.`when`(xPosition2.text).thenReturn(Editable.Factory.getInstance().newEditable("5"))
        Mockito.`when`(yPosition2.text).thenReturn(Editable.Factory.getInstance().newEditable("5"))
        Mockito.`when`(direction2.selectedItem).thenReturn("N")
        Mockito.`when`(commands2.text).thenReturn(Editable.Factory.getInstance().newEditable("L"))

        part2Activity.moveCars()

        Mockito.verify(collisionResult).text = "No collision"
    }

    @Test
    fun `test moveCars with collision`() {
        Mockito.`when`(width.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))
        Mockito.`when`(height.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))

        Mockito.`when`(xPosition1.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        Mockito.`when`(yPosition1.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        Mockito.`when`(direction1.selectedItem).thenReturn("N")
        Mockito.`when`(commands1.text).thenReturn(Editable.Factory.getInstance().newEditable("L"))

        Mockito.`when`(xPosition2.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        Mockito.`when`(yPosition2.text).thenReturn(Editable.Factory.getInstance().newEditable("2"))
        Mockito.`when`(direction2.selectedItem).thenReturn("S")
        Mockito.`when`(commands2.text).thenReturn(Editable.Factory.getInstance().newEditable("FF"))

        part2Activity.moveCars()

        Mockito.verify(collisionResult).text = "Collision at 0 0 \nAt step no.: 2"
    }

    @Test
    fun `test moveCar throws IllegalArgumentException`() {
        Mockito.`when`(width.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))
        Mockito.`when`(height.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))

        Mockito.`when`(xPosition1.text).thenReturn(Editable.Factory.getInstance().newEditable("11"))
        Mockito.`when`(yPosition1.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        Mockito.`when`(direction1.selectedItem).thenReturn("N")
        Mockito.`when`(commands1.text).thenReturn(Editable.Factory.getInstance().newEditable("FRF"))

        Mockito.`when`(xPosition2.text).thenReturn(Editable.Factory.getInstance().newEditable("5"))
        Mockito.`when`(yPosition2.text).thenReturn(Editable.Factory.getInstance().newEditable("5"))
        Mockito.`when`(direction2.selectedItem).thenReturn("N")
        Mockito.`when`(commands2.text).thenReturn(Editable.Factory.getInstance().newEditable("FFL"))

        part2Activity.moveCars()

        Mockito.verify(collisionResult).text = "Error: Invalid x and y coordinates"
    }

    @Test
    fun `test checkCollision`() {
        val width = 10
        val height = 10

        val car1 = Car(width, height)
        val car2 = Car(width, height)

        // Set positions for car1 and car2 to be the same, hence they should collide
        car1.setPosition(5, 5, 'N')
        car2.setPosition(5, 5, 'N')

        // Assert that checkCollision() returns true for colliding cars
        assertTrue(part2Activity.checkCollision(car1, car2))

        // Set positions for car1 and car2 to be different, hence they should not collide
        car1.setPosition(0, 0, 'N')
        car2.setPosition(5, 5, 'N')

        // Assert that checkCollision() returns false for non-colliding cars
        assertFalse(part2Activity.checkCollision(car1, car2))
    }
}