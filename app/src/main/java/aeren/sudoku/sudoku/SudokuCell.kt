package aeren.sudoku.sudoku

class SudokuCell(_board: SudokuBoard, _value: Int, _position: Int) {
    var value: Int = _value
    val possibleValues: MutableList<Int> = ArrayList()
    val board: SudokuBoard = _board
    val isEmpty: Boolean = (value == 0)
    var position: Int = _position
    val x = (position % 9)
    val y = (position / 9)
    var block: Int = 0
    var sudokuBlock: SudokuBlock

    init {
        if (x < 3) {
            if (y < 3) {
                block = 0
            } else if (y < 6) {
                block = 3
            } else if (y < 9) {
                block = 6
            }
        } else if (x < 6) {
            if (y < 3) {
                block = 1
            } else if (y < 6) {
                block = 4
            } else if (y < 9) {
                block = 7
            }
        } else if (x < 9) {
            if (y < 3) {
                block = 2
            } else if (y < 6) {
                block = 5
            } else if (y < 9) {
                block = 8
            }
        }

        sudokuBlock = board.blocks[block]
        possibleValues.addAll(sudokuBlock.lackingNumbers)
    }


    fun computePossibleValues() {
        if (!isEmpty)
            return

        //Check row
        for (i in 0..8) {
            val index = i + (y * 9)
            val cell: SudokuCell = board.cells[index]

            if (possibleValues.contains(cell.value)) {
                possibleValues.remove(cell.value)
            }
        }

        //Check column
        for (i in 0..8) {
            val index = x + (i * 9)
            val cell: SudokuCell = board.cells[index]

            if (possibleValues.contains(cell.value)) {
                possibleValues.remove(cell.value)
            }
        }
    }

    override fun toString(): String {
        return "value: $value, possibleValues: ${possibleValues}, isEmpty:$isEmpty, position=($x, $y), Block[$sudokuBlock]"
    }
}