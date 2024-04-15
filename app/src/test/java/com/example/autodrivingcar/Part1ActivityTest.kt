package com.example.autodrivingcar

import android.text.Editable
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class Part1ActivityTest {

    private lateinit var part1Activity: Part1Activity

    @Mock
    private lateinit var width: EditText
    @Mock
    private lateinit var height: EditText
    @Mock
    private lateinit var xPosition: EditText
    @Mock
    private lateinit var yPosition: EditText
    @Mock
    private lateinit var direction: Spinner
    @Mock
    private lateinit var commands: EditText
    @Mock
    private lateinit var positionResult: TextView

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        part1Activity = Part1Activity()

        part1Activity.setWidth = width
        part1Activity.setHeight = height
        part1Activity.setXPosition = xPosition
        part1Activity.setYPosition = yPosition
        part1Activity.direction = direction
        part1Activity.commands = commands
        part1Activity.positionResult = positionResult
    }

    @Test
    fun `test checkForEmptyFields with non-empty fields`() {
        // Mock the EditText fields and set their text values
        `when`(width.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))
        `when`(height.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))
        `when`(xPosition.text).thenReturn(Editable.Factory.getInstance().newEditable("5"))
        `when`(yPosition.text).thenReturn(Editable.Factory.getInstance().newEditable("5"))
        `when`(commands.text).thenReturn(Editable.Factory.getInstance().newEditable("F"))

        try {
            part1Activity.checkForEmptyFields()
        } catch (e: IllegalArgumentException) {
            // If any exception occurs, fail the test
            assertEquals("An IllegalArgumentException should not be thrown", true, false)
        }
    }

    @Test
    fun `test checkForEmptyFields with empty fields`() {
        `when`(width.text).thenReturn(Editable.Factory.getInstance().newEditable(""))
        `when`(height.text).thenReturn(Editable.Factory.getInstance().newEditable(""))
        `when`(xPosition.text).thenReturn(Editable.Factory.getInstance().newEditable(""))
        `when`(yPosition.text).thenReturn(Editable.Factory.getInstance().newEditable(""))
        `when`(commands.text).thenReturn(Editable.Factory.getInstance().newEditable(""))

        assertThrows(IllegalArgumentException::class.java) {
            part1Activity.checkForEmptyFields()
        }
    }

    @Test
    fun testMoveCar() {
        `when`(width.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))
        `when`(height.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))
        `when`(xPosition.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        `when`(yPosition.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        `when`(direction.selectedItem).thenReturn("N")
        `when`(commands.text).thenReturn(Editable.Factory.getInstance().newEditable("FRF"))

        part1Activity.moveCar()

        verify(positionResult).text = "1 1 E"
    }

    @Test
    fun `test moveCar throws IllegalArgumentException`() {
        `when`(width.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))
        `when`(height.text).thenReturn(Editable.Factory.getInstance().newEditable("10"))
        `when`(xPosition.text).thenReturn(Editable.Factory.getInstance().newEditable("11"))
        `when`(yPosition.text).thenReturn(Editable.Factory.getInstance().newEditable("0"))
        `when`(direction.selectedItem).thenReturn("N")
        `when`(commands.text).thenReturn(Editable.Factory.getInstance().newEditable("FRF"))

        part1Activity.moveCar()

        verify(positionResult).text = "Error: Invalid x and y coordinates"
    }
}
