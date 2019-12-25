package aeren.sudoku.sudoku

class SudokuCell(_board: SudokuBoard, _value: Int, _position: Int) {
    val board: SudokuBoard = _board

    var value: Int = _value
    var boxId: Int = 0

    var position: Int = _position
    val x = (position % 9)
    val y = (position / 9)

    init {
        if (x < 3) {
            if (y < 3) {
                boxId = 0
            } else if (y < 6) {
                boxId = 3
            } else if (y < 9) {
                boxId = 6
            }
        } else if (x < 6) {
            if (y < 3) {
                boxId = 1
            } else if (y < 6) {
                boxId = 4
            } else if (y < 9) {
                boxId = 7
            }
        } else if (x < 9) {
            if (y < 3) {
                boxId = 2
            } else if (y < 6) {
                boxId = 5
            } else if (y < 9) {
                boxId = 8
            }
        }
    }

    fun isValid(value: Int): Boolean {
        if (possibleValues().contains(value))
            return true

        return false
    }

    fun possibleValues(): List<Int> {
        val possibleValues = mutableListOf(1,2,3,4,5,6,7,8,9)

        val forbiddenValues = ArrayList<Int>()
        forbiddenValues.addAll(board.getBox(boxId))
        forbiddenValues.addAll(board.getRow(y))
        forbiddenValues.addAll(board.getColumn(x))

        for (num in forbiddenValues) {
            if (possibleValues.contains(num))
                possibleValues.remove(num)
        }

        return possibleValues
    }

    override fun toString(): String {
        return "value: $value, possibleValues: ${possibleValues()}"
    }
}