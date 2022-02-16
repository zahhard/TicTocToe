package com.example.tictoctoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.tictoctoe.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    enum class Turn()
    {
        turnX,
        turnO
    }

    private var firtTurn = Turn.turnX
    private var currentTurn = Turn.turnO

   lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    fun boardTapped(view: View){
        if (view !is Button)
            return
        else{
            if (view.text != "")
                return
            if (currentTurn == Turn.turnO){
                view.text = "O"
                currentTurn = Turn.turnX
            }
            else if (currentTurn == Turn.turnX){
                view.text = "X"
                currentTurn = Turn.turnO
            }

            setTurnLabel()
        }
    }

    private fun setTurnLabel() {
        var turn = ""

        if (currentTurn == Turn.turnX)
            turn = " Turn Player X"
        else if (currentTurn == Turn.turnO)
            turn = "Turn Player O"

        binding.textView3Winner.text = turn
    }

    fun reset(){
        binding.button2.text = ""
        binding.button3.text = ""
        binding.button4.text = ""
        binding.button5.text = ""
        binding.button6.text = ""
        binding.button7.text = ""
        binding.button8.text = ""
        binding.button9.text = ""
        binding.button10.text = ""

        binding.textView3Winner.text = "Click to start"
    }

    fun stopTouch()
    {
        binding.button10.isEnabled = false
        binding.button2.isEnabled = false
        binding.button3.isEnabled = false
        binding.button4.isEnabled = false
        binding.button5.isEnabled = false
        binding.button6.isEnabled = false
        binding.button7.isEnabled = false
        binding.button8.isEnabled = false
        binding.button9.isEnabled = false
    }

    var Player1 = ArrayList<Int>()
    var Player2 = ArrayList<Int>()
    var ActivePlayer = 1
    var setPlayer = 1


    fun restartGame(view:View)
    {
        binding.button10.setBackgroundResource(android.R.drawable.btn_default)
        binding.button2.setBackgroundResource(android.R.drawable.btn_default)
        binding.button3.setBackgroundResource(android.R.drawable.btn_default)
        binding.button4.setBackgroundResource(android.R.drawable.btn_default)
        binding.button5.setBackgroundResource(android.R.drawable.btn_default)
        binding.button6.setBackgroundResource(android.R.drawable.btn_default)
        binding.button7.setBackgroundResource(android.R.drawable.btn_default)
        binding.button8.setBackgroundResource(android.R.drawable.btn_default)
        binding.button9.setBackgroundResource(android.R.drawable.btn_default)

        binding.button10.isEnabled = false
        binding.button2.isEnabled = false
        binding.button3.isEnabled = false
        binding.button4.isEnabled = false
        binding.button5.isEnabled = false
        binding.button6.isEnabled = false
        binding.button7.isEnabled = false
        binding.button8.isEnabled = false
        binding.button9.isEnabled = false

        Player1.clear()
        Player2.clear()
        ActivePlayer = 1

        binding.button10.isEnabled = true
        binding.button2.isEnabled = true
        binding.button3.isEnabled = true
        binding.button4.isEnabled = true
        binding.button5.isEnabled = true
        binding.button6.isEnabled = true
        binding.button7.isEnabled = true
        binding.button8.isEnabled = true
        binding.button9.isEnabled = true

        setPlayer = 1

    }

    fun buttonClick(view: View)
    {
        val buSelected:Button = view as Button
        var cellId = 0
        when(buSelected.id)
        {
            R.id.button2 -> cellId = 1
            R.id.button3 -> cellId = 2
            R.id.button4 -> cellId = 3

            R.id.button5 -> cellId = 4
            R.id.button6 -> cellId = 5
            R.id.button7 -> cellId = 6

            R.id.button8 -> cellId = 7
            R.id.button9 -> cellId = 8
            R.id.button10 -> cellId = 9
        }
        PlayGame(cellId,buSelected)

    }



    fun PlayGame(cellId:Int,buSelected:Button)
    {
        if (ActivePlayer == 1)
        {
            buSelected.text = "X"
            buSelected.setBackgroundColor(Color.GREEN)
            Player1.add(cellId)
            ActivePlayer = 2
            if (setPlayer == 1)
            {}
            else
            {
                try {
                    AutoPlay()
                }catch (ex:Exception)
                {
                    Toast.makeText(this,"Game Over", Toast.LENGTH_SHORT).show()
                }

            }
        }
        else
        {
            buSelected.text = "O"
            buSelected.setBackgroundColor(Color.CYAN)
            Player2.add(cellId)
            ActivePlayer = 1
        }
        buSelected.isEnabled = false
        CheckWinner()
    }

    fun CheckWinner()
    {
        var winner = -1

        //row1
        if (Player1.contains(1) && Player1.contains(2) && Player1.contains(3))
        {
            winner = 1
        }
        if (Player2.contains(1) && Player2.contains(2) && Player2.contains(3))
        {
            winner = 2
        }

        //row2
        if (Player1.contains(4) && Player1.contains(5) && Player1.contains(6))
        {
            winner = 1
        }
        if (Player2.contains(4) && Player2.contains(5) && Player2.contains(6))
        {
            winner = 2
        }

        //row3
        if (Player1.contains(7) && Player1.contains(8) && Player1.contains(9))
        {
            winner = 1
        }
        if (Player2.contains(7) && Player2.contains(8) && Player2.contains(9))
        {
            winner = 2
        }

        //col1
        if (Player1.contains(1) && Player1.contains(4) && Player1.contains(7))
        {
            winner = 1
        }
        if (Player2.contains(1) && Player2.contains(4) && Player2.contains(7))
        {
            winner = 2
        }

        //col2
        if (Player1.contains(2) && Player1.contains(5) && Player1.contains(8))
        {
            winner = 1
        }
        if (Player2.contains(2) && Player2.contains(5) && Player2.contains(8))
        {
            winner = 2
        }

        //col3
        if (Player1.contains(3) && Player1.contains(6) && Player1.contains(9))
        {
            winner = 1
        }
        if (Player2.contains(3) && Player2.contains(6) && Player2.contains(9))
        {
            winner = 2
        }

        //cross1
        if (Player1.contains(1) && Player1.contains(5) && Player1.contains(9))
        {
            winner = 1
        }
        if (Player2.contains(1) && Player2.contains(5) && Player2.contains(9))
        {
            winner = 2
        }

        //cross2
        if (Player1.contains(3) && Player1.contains(5) && Player1.contains(7))
        {
            winner = 1
        }
        if (Player2.contains(3) && Player2.contains(5) && Player2.contains(7))
        {
            winner = 2
        }

        if (winner != -1)
        {
            if (winner == 1)
            {
                if(setPlayer == 1) {
                    binding.textView3Winner.text = "Player 1 Wins!"
                    stopTouch()
                }
                else
                {
                    Toast.makeText(this, "You Won!!", Toast.LENGTH_SHORT).show()
                    stopTouch()
                }
            }
            else
            {
                if (setPlayer == 1) {
                    Toast.makeText(this, "Player 2 Wins!!", Toast.LENGTH_SHORT).show()
                    stopTouch()
                }
                else
                {
                    Toast.makeText(this, "CPU Wins!!", Toast.LENGTH_SHORT).show()
                    stopTouch()
                }
            }
        }
    }


    fun AutoPlay()
    {
        val emptyCells = ArrayList<Int>()
        for (cellId in 1..9) {
            if (Player1.contains(cellId) || Player2.contains(cellId))
            {}
            else
            {
                emptyCells.add(cellId)
            }
        }

        val r = Random()
        val randomIndex = r.nextInt(emptyCells.size-0)+0
        val cellId = emptyCells[randomIndex]

        val buSelect:Button?
        when(cellId)
        {
            1 -> buSelect = binding.button2
            2 -> buSelect = binding.button3
            3 -> buSelect = binding.button4
            4 -> buSelect = binding.button5
            5 -> buSelect = binding.button6
            6 -> buSelect = binding.button7
            7 -> buSelect = binding.button8
            8 -> buSelect = binding.button9
            9 -> buSelect = binding.button10
            else -> buSelect = binding.button2
        }

        PlayGame(cellId,buSelect)
    }

}