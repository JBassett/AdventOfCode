package com.github.jbassett.y2024.d20

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

    data class State(
        val board: List<List<BoardObject>>,
        val pos: Pair<Int, Int>,
        val path: List<Pair<Int, Int>>,
        val cheats: Int = 1,
    ) {
        fun place(loc:Pair<Int, Int>, boardObject: BoardObject): State {
            val (x, y) = loc
            val newBoard = board.map { it.toMutableList() }.toMutableList()
            newBoard[y][x] = boardObject
            return copy(board = newBoard)
        }

        fun score() = path.size - 1

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
            return str.toString()
        }
    }

    fun solve(isSample: Boolean, input: InputStream): Int {
        val initialBoard = input.bufferedReader().readLines()
            .fold(State(emptyList(), Pair(-1, -1), emptyList())) { state, line ->
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
        val pointToDistStart = mutableMapOf<Pair<Int, Int>, Int>()
        var baseline = initialBoard.copy(path = listOf(initialBoard.pos))
        while (baseline.board[baseline.pos.second][baseline.pos.first] != BoardObject.END) {
            val (startX, startY) = baseline.pos
            pointToDistStart[baseline.pos] = baseline.score()
            val newBoard = baseline.board.map { it.toMutableList() }.toMutableList()
            newBoard[baseline.pos.second][baseline.pos.first] = BoardObject.PATH
            Direction.entries
                .first {
                    when (it) {
                        Direction.UP -> {
                            if (startY - 1 >= 0
                                && baseline.board[startY - 1][startX] in listOf(BoardObject.EMPTY, BoardObject.END)
                            ) {
                                baseline = baseline.copy(
                                    board = newBoard,
                                    pos = Pair(startX, startY - 1),
                                    path = baseline.path + listOf(Pair(startX, startY - 1)),
                                )
                                true
                            } else {
                                false
                            }
                        }

                        Direction.RIGHT -> {
                            if (startX + 1 < baseline.board[startY].size
                                && baseline.board[startY][startX + 1] in listOf(BoardObject.EMPTY, BoardObject.END)
                            ) {
                                baseline = baseline.copy(
                                    board = newBoard,
                                    pos = Pair(startX + 1, startY),
                                    path = baseline.path + listOf(Pair(startX +1, startY)),
                                )
                                true
                            } else {
                                false
                            }
                        }

                        Direction.DOWN -> {
                            if (startY + 1 < baseline.board.size
                                && baseline.board[startY + 1][startX] in listOf(BoardObject.EMPTY, BoardObject.END)
                            ) {
                                baseline = baseline.copy(
                                    board = newBoard,
                                    pos = Pair(startX, startY + 1),
                                    path = baseline.path + listOf(Pair(startX, startY + 1)),
                                )
                                true
                            } else {
                                false
                            }
                        }

                        Direction.LEFT -> {
                            if (startX - 1 >= 0
                                && baseline.board[startY][startX - 1] in listOf(BoardObject.EMPTY, BoardObject.END)
                            ) {
                                baseline = baseline.copy(
                                    board = newBoard,
                                    pos = Pair(startX - 1, startY),
                                    path = baseline.path + listOf(Pair(startX -1, startY)),
                                )
                                true
                            } else {
                                false
                            }
                        }
                    }
                }
        }
        val baselineScore = baseline.score()
        pointToDistStart[baseline.pos] = baselineScore
        val pointToScore = pointToDistStart.map { (pos, score) -> pos to baselineScore - score }.toMap()

        return baseline.path.flatMap { pos ->
            val pointsFromStart = pointToDistStart[pos] ?: return@flatMap emptyList<Int>()
            Direction.entries.mapNotNull { dir ->
                val wall = pos.move(dir)
                if (baseline.board[wall.second][wall.first] != BoardObject.WALL) return@mapNotNull null
                val cheatPos = wall.move(dir)

                val pointsFromStartCheat = pointToDistStart[cheatPos] ?: return@mapNotNull null

                if (pointsFromStartCheat < pointsFromStart) return@mapNotNull null

                pointsFromStartCheat - pointsFromStart
            }
        }.count {
            if (isSample) true
            else it > 100
        }
    }

    fun Pair<Int, Int>.move(dir: Direction): Pair<Int, Int> = when (dir) {
        Direction.UP -> Pair(this.first, this.second - 1)
        Direction.RIGHT -> Pair(this.first + 1, this.second)
        Direction.DOWN -> Pair(this.first, this.second + 1)
        Direction.LEFT -> Pair(this.first - 1, this.second)
    }
}
