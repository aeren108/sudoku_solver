package aeren.sudoku.sudoku

class SudokuBoard(_values: List<Int>) {
    private val values: List<Int> = _values
    val cells: MutableList<SudokuCell> = ArrayList()

    init {
        for (i in values.indices) {
            cells.add(SudokuCell(this, values[i], i))
        }
    }

    /**
     * Returns the nine 3x3 boxes of the sudoku
     * @param boxId the position of box which cell is in
     * @return the list which contains numbers in the box
     */
    fun getBox(boxId: Int): List<Int> {
        val boxCells = ArrayList<Int>()

        val x = boxId % 3
        val y = boxId / 3

        val startX = x * 3
        val startY = y * 3

        for (i in startY..(startY + 2)) {
            for (j in startX..(startX + 2)) {
                val index: Int = j + (i * 9)
                boxCells.add(cells[index].value)
            }
        }

        return boxCells
    }

    fun getRow(row: Int): List<Int> {
        val rowCells = ArrayList<Int>()

        for (i in 0..8) {
            val index = i + (row * 9)

            rowCells.add(cells[index].value)
        }

        return rowCells
    }

    fun getColumn(column: Int): List<Int> {
        val colCells = ArrayList<Int>()

        for (i in 0..8) {
            val index = column + (i * 9)

            colCells.add(cells[index].value)
        }

        return colCells
    }

    //Returns first empty cell in sudoku board
    private fun findEmpty(): SudokuCell? {
        for (cell in cells) {
            if (cell.value == 0)
                return cell
        }

        return null
    }

    //Details for backtracking algorithm: https://en.wikipedia.org/wiki/Backtracking
    fun solve(): Boolean {
        val cell = findEmpty() ?: return true

        for (i in 1..9) {
            if (cell.isValid(i)) {
                cell.value = i

                //Backtracking
                if (solve())
                    return true

                cell.value = 0
            }
        }

        return false
    }
}
