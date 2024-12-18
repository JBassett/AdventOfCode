package com.github.jbassett.y2024.d16

import com.github.jbassett.y2024.d12.Part1.Direction
import java.io.InputStream

class Part1 {
    enum class BoardObject {
        WALL,
        EMPTY,
        END,
        PATH,
    }

    enum class Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT,
    }

    enum class Move {
        STRAIGHT,
        RIGHT,
        LEFT,
    }

    data class State(
        val board: List<List<BoardObject>>,
        val pos: Pair<Int, Int>,
        val direction: Direction,
        val moves: List<Move>,
    ) {
        fun score(): Long = moves.map { move ->
            when (move) {
                Move.STRAIGHT -> 1
                Move.RIGHT,
                Move.LEFT -> 1000
            }
        }.sum().toLong()

        override fun toString(): String {
            val str = StringBuilder()
            board.forEach { line ->
                line.forEach { pos ->
                    when (pos) {
                        BoardObject.WALL -> str.append("#")
                        BoardObject.EMPTY -> str.append(".")
                        BoardObject.END -> str.append("E")
                        BoardObject.PATH -> str.append('O')
                    }
                }
                str.append("\n")
            }
            str.append("Moves: ")
            moves.forEach { move ->
                when (move) {
                    Move.STRAIGHT -> str.append("^")
                    Move.RIGHT -> str.append(">")
                    Move.LEFT -> str.append("<")
                }
            }
            return str.toString()
        }
    }

    fun solve(input: InputStream): Long {
        val initialBoard = input.bufferedReader().readLines()
            .fold(State(emptyList(), Pair(-1, -1), Direction.RIGHT, emptyList())) { state, line ->
                line.fold(state.copy(board = state.board + listOf(listOf()))) { state, c ->
                    val lastRow = state.board.last().toMutableList()
                    var pos = state.pos
                    when (c) {
                        '#' -> lastRow.add(BoardObject.WALL)
                        'E' -> lastRow.add(BoardObject.END)
                        'S' -> {
                            pos = Pair(lastRow.size, state.board.size - 1)
                            lastRow.add(BoardObject.EMPTY)
                        }

                        '.' -> lastRow.add(BoardObject.EMPTY)

                        else -> Unit
                    }


                    state.copy(
                        board = (state.board.dropLast(1) + listOf(lastRow)).dropLastWhile { it.isEmpty() },
                        pos = pos,
                    )
                }
            }

        return makeMove(initialBoard)?.score() ?: -1
    }

    private var lowScore = Long.MAX_VALUE
    private val states = mutableMapOf<Pair<Int, Int>, State>()
    private fun makeMove(
        state: State,
    ): State? {
        if (state.score() > lowScore) {
            return null
        }
        val cache = states[state.pos]
        if (state.score() < (cache?.score() ?: Long.MAX_VALUE)) {
            states[state.pos] = state
        } else {
            return null
        }
        when (state.board[state.pos.second][state.pos.first]) {
            BoardObject.PATH,
            BoardObject.WALL -> return null

            BoardObject.END -> {
                println("Found end score:${state.score()}")
                lowScore = state.score()
                return state
            }
            else -> Unit
        }
        val (startX, startY) = state.pos
        return Direction.entries
            .mapNotNull {
                val newBoard = state.board.map { it.toMutableList() }.toMutableList()
                newBoard[state.pos.second][state.pos.first] = BoardObject.PATH
                when (it) {
                    Direction.UP -> {
                        if (startY - 1 >= 0) {
                            val moves = when (state.direction) {
                                Direction.UP -> listOf(Move.STRAIGHT)
                                Direction.RIGHT -> listOf(Move.LEFT, Move.STRAIGHT)
                                Direction.DOWN -> listOf(Move.LEFT, Move.LEFT, Move.STRAIGHT)
                                Direction.LEFT -> listOf(Move.RIGHT, Move.STRAIGHT)
                            }
                            newBoard[startY + 1][startX] = BoardObject.PATH
                            newBoard[startY][startX - 1] = BoardObject.PATH
                            newBoard[startY][startX + 1] = BoardObject.PATH
                            state.copy(
                                board = newBoard,
                                pos = Pair(startX, startY - 1),
                                direction = Direction.UP,
                                moves = state.moves + moves
                            )
                        } else {
                            null
                        }
                    }

                    Direction.RIGHT -> {
                        if (startX + 1 < state.board[startY].size) {
                            val moves = when (state.direction) {
                                Direction.UP -> listOf(Move.RIGHT, Move.STRAIGHT)
                                Direction.RIGHT -> listOf(Move.STRAIGHT)
                                Direction.DOWN -> listOf(Move.LEFT, Move.STRAIGHT)
                                Direction.LEFT -> listOf(Move.LEFT, Move.LEFT, Move.STRAIGHT)
                            }
                            newBoard[startY - 1][startX] = BoardObject.PATH
                            newBoard[startY + 1][startX] = BoardObject.PATH
                            newBoard[startY][startX - 1] = BoardObject.PATH
                            state.copy(
                                board = newBoard,
                                pos = Pair(startX + 1, startY),
                                direction = Direction.RIGHT,
                                moves = state.moves + moves
                            )
                        } else {
                            null
                        }
                    }

                    Direction.DOWN -> {
                        if (startY + 1 < state.board.size) {
                            val moves = when (state.direction) {
                                Direction.UP -> listOf(Move.LEFT, Move.LEFT, Move.STRAIGHT)
                                Direction.RIGHT -> listOf(Move.RIGHT, Move.STRAIGHT)
                                Direction.DOWN -> listOf(Move.STRAIGHT)
                                Direction.LEFT -> listOf(Move.LEFT, Move.STRAIGHT)
                            }
                            newBoard[startY - 1][startX] = BoardObject.PATH
                            newBoard[startY][startX - 1] = BoardObject.PATH
                            newBoard[startY][startX + 1] = BoardObject.PATH
                            state.copy(
                                board = newBoard,
                                pos = Pair(startX, startY + 1),
                                direction = Direction.DOWN,
                                moves = state.moves + moves
                            )
                        } else {
                            null
                        }
                    }

                    Direction.LEFT -> {
                        if (startX - 1 >= 0) {
                            val moves = when (state.direction) {
                                Direction.UP -> listOf(Move.LEFT, Move.STRAIGHT)
                                Direction.RIGHT -> listOf(Move.LEFT, Move.LEFT, Move.STRAIGHT)
                                Direction.DOWN -> listOf(Move.RIGHT, Move.STRAIGHT)
                                Direction.LEFT -> listOf(Move.STRAIGHT)
                            }
                            newBoard[startY - 1][startX] = BoardObject.PATH
                            newBoard[startY + 1][startX] = BoardObject.PATH
                            newBoard[startY][startX + 1] = BoardObject.PATH
                            state.copy(
                                board = newBoard,
                                pos = Pair(startX - 1, startY),
                                direction = Direction.LEFT,
                                moves = state.moves + moves
                            )
                        } else {
                            null
                        }
                    }
                }
            }
            .mapNotNull { makeMove(it) }
            .minWithOrNull(compareBy { it.score() })
    }
}
