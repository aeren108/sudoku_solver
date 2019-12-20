package aeren.sudoku

import aeren.sudoku.sudoku.SudokuCell
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.cell_view.view.*

class GridAdapter(_context: Context, _cells: List<SudokuCell>) : BaseAdapter() {
    var context: Context = _context
    var cells: List<SudokuCell> = _cells

    override fun getItem(position: Int): Any {
        return cells[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return cells.size
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val cell = cells[position]

        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var cellView = inflator.inflate(R.layout.cell_view, null)
        cellView.value.setText(cell.value.toString())

        return cellView
    }
}