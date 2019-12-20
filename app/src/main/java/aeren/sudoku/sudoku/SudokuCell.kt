package aeren.sudoku.sudoku

import aeren.sudoku.sudoku.SudokuBoard

class SudokuCell(_board: SudokuBoard, _value: Int) {
    var value: Int = _value
    val possibleValues: MutableList<Int> = ArrayList()
    val board: SudokuBoard = _board
    val isEmpty: Boolean = (value == 0)

    fun computePossibleValues() {
        //TODO: check all possibilities according to sudoku board
    }

    override fun toString(): String {
        return "value: $value, possibleValues: ${possibleValues.toString()}, isEmpty:$isEmpty"
    }
}