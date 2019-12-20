package aeren.sudoku.sudoku

class SudokuBoard(values: List<Int>) {
    val cells: MutableList<SudokuCell> = ArrayList()

    init {
        for (i in 1..(values.size-1)) {
            cells.add(SudokuCell(this, values[i]))
        }
    }

    fun setCells() {
        TODO("find which block of sudoku cells are in and fill the list")
    }

    fun findLackingValues(block: Int) {
        TODO("Find lacking numbers in blocks")
    }

    fun solve() {
        TODO("Solve the sudoku")
    }
}