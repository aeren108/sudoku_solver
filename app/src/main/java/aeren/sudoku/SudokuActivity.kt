package aeren.sudoku

import aeren.sudoku.sudoku.SudokuBoard
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridView
import kotlinx.android.synthetic.main.cell_view.view.*

class SudokuActivity : AppCompatActivity() {
    private lateinit var grid: GridView
    private lateinit var solveButton: Button

    private val values: MutableList<Int> = ArrayList()
    private var focusedView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sudoku)

        grid = findViewById(R.id.grid)
        solveButton = findViewById(R.id.solve)

        values.addAll(listOf(5,3,0,0,7,0,0,0,0,
                             6,0,0,1,9,5,0,0,0,
                             0,9,8,0,0,0,0,6,0,
                             8,0,0,0,6,0,0,0,3,
                             4,0,0,8,0,3,0,0,1,
                             7,0,0,0,2,0,0,0,6,
                             0,6,0,0,0,0,2,8,0,
                             0,0,0,4,1,9,0,0,5,
                             0,0,0,0,8,0,0,7,9))

        val board = SudokuBoard(values)
        val adapter = GridAdapter(this.applicationContext, board.cells)

        grid.adapter = adapter
        grid.setOnItemClickListener { adapterView, view, position, id ->
            val cell = adapter.getItem(position)
            Log.i("CELL", "$cell, id: $position")

            focusedView?.value?.setTextColor(Color.parseColor("#181818"))

            focusedView = view
            focusedView?.value?.setTextColor(Color.parseColor("#d9003d"))
        }

        solveButton.setOnClickListener {
            board.solve()
            adapter.notifyDataSetChanged()
        }
    }

}
