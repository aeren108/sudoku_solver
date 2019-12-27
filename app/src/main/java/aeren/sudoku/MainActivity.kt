package aeren.sudoku

import aeren.sudoku.sudoku.SudokuBoard
import aeren.sudoku.sudoku.SudokuCell
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridView

class MainActivity : AppCompatActivity() {
    private lateinit var grid: GridView

    private lateinit var solveButton: Button
    private lateinit var saveButton: Button
    private lateinit var numberInput: EditText

    private val values: MutableList<Int> = ArrayList()
    private var focusedView: View? = null
    private var focusedCell: SudokuCell? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        grid = findViewById(R.id.grid)
        solveButton = findViewById(R.id.solve)
        saveButton = findViewById(R.id.save)
        numberInput = findViewById(R.id.number)

        //Example sudoku to solve
        values.addAll(listOf(0,0,0,0,0,0,0,0,0,
                             0,0,0,0,0,0,0,0,0,
                             0,0,0,0,0,0,0,0,0,
                             0,0,0,0,0,0,0,0,0,
                             0,0,0,0,0,0,0,0,0,
                             0,0,0,0,0,0,0,0,6,
                             0,0,0,0,0,0,0,8,0,
                             0,0,0,0,0,0,0,0,0,
                             0,0,0,0,0,0,0,0,0))

        val board = SudokuBoard(values)
        val adapter = GridAdapter(this.applicationContext, board.cells)

        grid.adapter = adapter
        grid.setOnItemClickListener { adapterView, view, position, id ->
            val cell = adapter.getItem(position)
            focusedCell = cell as SudokuCell

            //Set old focused view's color to normal
            focusedView?.setBackgroundColor(Color.TRANSPARENT)
            focusedView?.setBackgroundResource(R.drawable.gridview_bg)

            //Highlighted clicked view
            focusedView = view as CellView
            focusedView?.setBackgroundColor(Color.argb(75, 255, 143, 190))

            //if focused cell has a value set it to edit text
            if (focusedCell?.value != 0)
                numberInput.setText(focusedCell?.value.toString())
            else
                numberInput.setText("")
        }

        saveButton.setOnClickListener {
            val number: String = numberInput.text.toString()

            if (number.length == 1) {
                focusedCell?.value = number.toInt()
                adapter.notifyDataSetChanged()
            }
        }

        solveButton.setOnClickListener {
            board.solve()
            adapter.notifyDataSetChanged()
        }
    }

}
