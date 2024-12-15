package com.github.jbassett.y2024.d15

import java.io.InputStream

class Part1 {
    enum class BoardObject {
        WALL,
        BOX,
        ROBOT,
        EMPTY
    }

    enum class Move {
        UP,
        RIGHT,
        DOWN,
        LEFT,
    }

    data class State(
        val board: List<List<BoardObject>>,
        val robotPosition: Pair<Int, Int>,
        val moves: List<Move>
    )

    fun solve(input: InputStream): Long {
        val initialBoard = input.bufferedReader().readLines()
            .fold(State(emptyList(), Pair(-1, -1), emptyList())) { state, line ->
                line.fold(state.copy(board = state.board + listOf(listOf()))) { state, c ->
                    val lastRow = state.board.last().toMutableList()
                    var robotPosition = state.robotPosition
                    val moves = state.moves.toMutableList()
                    when (c) {
                        '#' -> lastRow.add(BoardObject.WALL)
                        'O' -> lastRow.add(BoardObject.BOX)
                        '@' -> {
                            robotPosition = Pair(state.board.size - 1, lastRow.size)
                            lastRow.add(BoardObject.ROBOT)
                        }

                        '.' -> lastRow.add(BoardObject.EMPTY)

                        '^' -> moves.add(Move.UP)
                        '>' -> moves.add(Move.RIGHT)
                        'v' -> moves.add(Move.DOWN)
                        '<' -> moves.add(Move.LEFT)

                        else -> Unit
                    }


                    state.copy(
                        board = (state.board.dropLast(1) + listOf(lastRow)).dropLastWhile { it.isEmpty() },
                        robotPosition = robotPosition,
                        moves = moves
                    )
                }
            }
        printBoard(initialBoard)
        var state = initialBoard
        while (state.moves.isNotEmpty()) {
            val board = state.board.map { it.toMutableList() }.toMutableList()

            val pos = if (makeMove(board, state.robotPosition, state.moves.first())) {
                val (x, y) = state.robotPosition
                when (state.moves.first()) {
                    Move.UP -> Pair(x, y - 1)
                    Move.RIGHT -> Pair(x + 1, y)
                    Move.DOWN -> Pair(x, y + 1)
                    Move.LEFT -> Pair(x - 1, y)
                }
            } else state.robotPosition

            state = state.copy(board = board, robotPosition = pos, moves = state.moves.drop(1))
        }

        printBoard(initialBoard)

        return state.board.mapIndexed { y, column ->
            column.mapIndexed { x, item ->
                if (item == BoardObject.BOX) {
                    (100L * y) + x
                } else
                    0L
            }.sum()
        }.sum()
    }

    fun makeMove(
        board: MutableList<MutableList<BoardObject>>,
        start: Pair<Int, Int>,
        direction: Move
    ): Boolean {
        val (startX, startY) = start
        val (endX, endY) = when (direction) {
            Move.UP -> Pair(start.first, start.second - 1)
            Move.RIGHT -> Pair(start.first + 1, start.second)
            Move.DOWN -> Pair(start.first, start.second + 1)
            Move.LEFT -> Pair(start.first - 1, start.second)
        }
        return when (board[endY][endX]) {
            BoardObject.WALL -> false
            BoardObject.BOX -> makeMove(board, Pair(endX, endY), direction)
            BoardObject.ROBOT -> throw IllegalStateException("Robot is in 2 places at once")
            BoardObject.EMPTY -> true
        }.also { wasSuccess ->
            if (wasSuccess) {
                board[endY][endX] = board[startY][startX]
                board[startY][startX] = BoardObject.EMPTY
            }
        }
    }

    fun printBoard(state: State) {
        state.board.forEach { line ->
            line.forEach { pos ->
                when (pos) {
                    BoardObject.WALL -> print("#")
                    BoardObject.BOX -> print("O")
                    BoardObject.ROBOT -> print("@")
                    BoardObject.EMPTY -> print(".")
                }
            }
            println()
        }
    }
}
