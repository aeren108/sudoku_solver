package aeren.sudoku

import aeren.sudoku.sudoku.SudokuCell
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.cell_view.view.*

class GridAdapter(_context: Context, _cells: List<SudokuCell>) : BaseAdapter() {
    private var context: Context = _context
    private var cells: List<SudokuCell> = _cells

    override fun getItem(position: Int): Any {
        return cells[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return cells.size
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val cell = cells[position]

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val cellView = inflater.inflate(R.layout.cell_view, null)

        val text: String = if (cell.value == 0) " " else cell.value.toString()
        cellView.value.text = text

        if (cell.isEmptyAtFirst)
            cellView.value.setTextColor(Color.parseColor("#3461eb"))

        return cellView
    }
}