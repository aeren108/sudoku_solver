package aeren.sudoku

import aeren.sudoku.sudoku.SudokuBoard
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import kotlinx.android.synthetic.main.cell_view.view.*
import kotlin.random.Random

class SudokuActivity : AppCompatActivity() {
    lateinit var grid: GridView
    val values: MutableList<Int> = ArrayList()
    var focusedView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sudoku)

        grid = findViewById(R.id.grid)

        setValues()
        val board = SudokuBoard(values)
        val adapter = GridAdapter(applicationContext, board.cells)

        grid.adapter = adapter
        grid.setOnItemClickListener( OnItemClickListener { adapterView, view, position, id ->
            Log.i("CELL", adapter.getItem(position).toString() + ", id: $position")

            focusedView?.value?.setTextColor(Color.parseColor("#181818"))

            focusedView = view
            focusedView?.value?.setTextColor(Color.parseColor("#d9003d"))
        })

    }

    fun setValues() {
        //randomizing for now
        for (i in 0..81) {
            if (Random.nextInt(0, 8) < 2)
                values.add(Random.nextInt(0,9))
            else
                values.add(0)
        }
    }
}
