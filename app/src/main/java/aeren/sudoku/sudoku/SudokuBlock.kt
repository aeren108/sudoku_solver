package aeren.sudoku.sudoku

import android.util.Log

class SudokuBlock(_board: SudokuBoard, _position: Int) {
    val position: Int = _position
    val board: SudokuBoard = _board

    val nums: MutableList<Int> = ArrayList()
    val lackingNumbers: MutableList<Int> = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    var startX: Int = 0
    var startY: Int = 0

    init {
        val x: Int = position % 3
        val y: Int = position / 3

        startX = 3 * x
        startY = 3 * y

        extractValuesFromBoard()
        findLackingNumbers()
    }

    fun extractValuesFromBoard() {
        for (i in startY..(startY + 2)) {
            for (j in startX..(startX + 2)) {
                val index: Int = j + (i * 9)
                nums.add(board.values[index])
            }
        }
    }

    fun findLackingNumbers() {

        for (num in nums) {
            if (lackingNumbers.contains(num))
                lackingNumbers.remove(num)
        }
        Log.i("NUMS", lackingNumbers.toString())
    }

    override fun toString(): String {
        return "Numbers inside: $nums, Lacking numbers: $lackingNumbers"
    }
}