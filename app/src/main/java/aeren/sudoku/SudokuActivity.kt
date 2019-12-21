package aeren.sudoku

import aeren.sudoku.sudoku.SudokuBoard
import aeren.sudoku.sudoku.SudokuCell
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.GridView
import kotlinx.android.synthetic.main.cell_view.view.*
import kotlin.random.Random

class SudokuActivity : AppCompatActivity() {
    lateinit var grid: GridView
    lateinit var solveButton: Button

    val values: MutableList<Int> = ArrayList()
    var focusedView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sudoku)

        grid = findViewById(R.id.grid)
        solveButton = findViewById(R.id.solve)

        setValues()
        val board = SudokuBoard(values)
        val adapter = GridAdapter(applicationContext, board.cells)

        grid.adapter = adapter
        grid.setOnItemClickListener( OnItemClickListener { adapterView, view, position, id ->
            val cell = adapter.getItem(position)
            Log.i("CELL", cell.toString() + ", id: $position")

            focusedView?.value?.setTextColor(Color.parseColor("#181818"))

            focusedView = view
            focusedView?.value?.setTextColor(Color.parseColor("#d9003d"))
        })

        solveButton.setOnClickListener(View.OnClickListener {
            board.solve()
            adapter.notifyDataSetChanged()
        })

    }

    fun setValues() {
        values.addAll(listOf(5,3,0,0,7,0,0,0,0,
                             6,0,0,1,9,5,0,0,0,
                             0,9,8,0,0,0,0,6,0,
                             8,0,0,0,6,0,0,0,3,
                             4,0,0,8,0,3,0,0,1,
                             7,0,0,0,2,0,0,0,6,
                             0,6,0,0,0,0,2,8,0,
                             0,0,0,4,1,9,0,0,5,
                             0,0,0,0,8,0,0,7,9))
    }
}
