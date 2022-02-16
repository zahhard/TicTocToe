package com.example.tictoctoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tictoctoe.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    enum class Turn() {
        NOUGHT,
        CROSS
    }

    private var firtTurn = Turn.NOUGHT
    private var currentTurn = Turn.CROSS
    private var boardList = mutableListOf<Button>()
    private var crossesScore = 0
    private var noughtsScore = 0

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard() {
        boardList.add(binding.button1)
        boardList.add(binding.button2)
        boardList.add(binding.button3)
        boardList.add(binding.button4)
        boardList.add(binding.button5)
        boardList.add(binding.button6)
        boardList.add(binding.button7)
        boardList.add(binding.button8)
        boardList.add(binding.button9)
    }

    fun boardTapped(view: View) {
        if (view !is Button)
            return
        addToBoard(view)

        if (checkForVictory("X")) {
            noughtsScore ++
            result("Player X Win!")

        }
        if (checkForVictory("O")) {
            crossesScore ++
            result("Player O Win!")

        }
        setTurnLabel()
        if (fullBoard()) {
            result("Draw")
        }
    }

    private fun checkForVictory(s: String): Boolean {

        //Horizontal victory
        if (match(binding.button1, s) && match(binding.button2, s) && match(binding.button3, s))
            return true
        if (match(binding.button4, s) && match(binding.button5, s) && match(binding.button6, s))
            return true
        if (match(binding.button7, s) && match(binding.button8, s) && match(binding.button9, s))
            return true

        //Vertical victory
        if (match(binding.button1, s) && match(binding.button4, s) && match(binding.button7, s))
            return true
        if (match(binding.button2, s) && match(binding.button5, s) && match(binding.button8, s))
            return true
        if (match(binding.button3, s) && match(binding.button6, s) && match(binding.button9, s))
            return true

        //Diagonal victory
        if (match(binding.button1, s) && match(binding.button5, s) && match(binding.button9, s))
            return true
        if (match(binding.button3, s) && match(binding.button5, s) && match(binding.button7, s))
            return true

        return false
    }


    private fun addToBoard(button: Button) {
        if (button.text != "")
            return
        if (currentTurn == Turn.NOUGHT) {
            button.text = "O"
            currentTurn = Turn.CROSS
        } else if (currentTurn == Turn.CROSS) {
            button.text = "X"
            currentTurn = Turn.NOUGHT
        }

        setTurnLabel()

    }

    private fun match(button: Button, symbol: String): Boolean =  button.text == symbol
    private fun result(title: String) {
        val massage = "\nPlayer X: $noughtsScore\n\nPlayer O: $crossesScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(massage)
            .setPositiveButton("Reset") { _, _ -> resetBoard() }
            .setCancelable(false)
            .show()

    }

    private fun setTurnLabel() {
        var turnText = ""

        if (currentTurn == Turn.CROSS)
            turnText = " Turn Player X"
        else if (currentTurn == Turn.NOUGHT)
            turnText = "Turn Player O"

        binding.textView3Winner.text = turnText
    }

    private fun fullBoard(): Boolean {
        for (button in boardList) {
            if (button.text == "") {
                return false
            }
        }
        return true
    }

    private fun resetBoard() {
        for (button in boardList) {

            button.text = ""
        }
        if (firtTurn == Turn.NOUGHT)
            firtTurn = Turn.CROSS
        else if (firtTurn == Turn.CROSS)
            firtTurn = Turn.NOUGHT

        currentTurn = firtTurn
    }


}


