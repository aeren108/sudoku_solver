package aeren.sudoku

import aeren.sudoku.sudoku.SudokuBoard
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.GridView
import kotlin.random.Random

class SudokuActivity : AppCompatActivity() {
    lateinit var grid: GridView
    val values: MutableList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sudoku)

        grid = findViewById(R.id.grid)

        setValues()
        val board = SudokuBoard(values)
        val adapter = GridAdapter(applicationContext, board.cells)

        grid.adapter = adapter
        grid.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, position, id ->
            Log.i("CELL", adapter.getItem(position).toString())
        })

    }

    fun setValues() {
        for (i in 0..81) {
            values.add(Random.nextInt(0,9))
        }
    }
}
