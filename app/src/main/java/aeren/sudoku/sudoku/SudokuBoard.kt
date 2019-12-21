package aeren.sudoku.sudoku

import android.util.Log
import kotlin.random.Random

class SudokuBoard(_values: List<Int>) {
    val values: List<Int> = _values
    val cells: MutableList<SudokuCell> = ArrayList()
    val blocks: MutableList<SudokuBlock> = ArrayList()

    init {
        for (i in 0..8) {
            blocks.add(SudokuBlock(this, i))
        }

        for (i in 0..(values.size-1)) {

            cells.add(SudokuCell(this, values[i], i))
        }

        for (cell in cells) {
            cell.computePossibleValues()
        }
    }

    fun setCells() {
        TODO("find which block of sudoku cells are in and fill the list")
    }


    fun solve(){
        val buffer: MutableList<SudokuCell> = ArrayList()
        buffer.addAll(cells)

        for (x in 0..8){
            for (y in 0..8) {
                val index = x + (y * 9)
                val cell = buffer[index]
                if (!cell.isEmpty) continue

                cell.sudokuBlock.extractValuesFromBoard()
                cell.sudokuBlock.findLackingNumbers()
                cell.computePossibleValues()

                //Handle number insertion. Randomized for now
                if (!cell.possibleValues.isEmpty())
                    cell.value = cell.possibleValues[Random.nextInt(0, cell.possibleValues.size)]

            }
        }

        cells.clear()
        cells.addAll(buffer)
    }
}
