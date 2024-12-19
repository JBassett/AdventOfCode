package com.github.jbassett.y2024.d18

import java.io.InputStream

class Part2 {
    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }

    fun solve(input: InputStream): String {
        val cor = input.bufferedReader().readLines().map {
            val split = it.split(",")
            Pair(split[0].toInt(), split[1].toInt())
        }
        val maxBoard = if (cor.size > 25) 71 else 7
        val board = Array(maxBoard) { Array(maxBoard) { '.' } }
        val starting = if (cor.size > 25) 1023 else 11

        var cachePath: List<Pair<Int, Int>>? = null
        for (z in cor.indices) {
            val (x, y) = cor[z]
            board[y][x] = '#'
            if (z > starting && (cachePath == null || cor[z] in cachePath)) {
                val shortestPath = mutableMapOf<Pair<Int, Int>, Int>()
                fun findPath(
                    board: Array<Array<Char>>,
                    x: Int,
                    y: Int,
                    path: List<Pair<Int, Int>>
                ): List<Pair<Int, Int>>? {
                    return Direction.entries
                        .mapNotNull { direction ->
                            when (direction) {
                                Direction.UP -> {
                                    if (y - 1 >= 0) {
                                        Pair(x, y - 1)
                                    } else {
                                        null
                                    }
                                }

                                Direction.RIGHT -> {
                                    if (x + 1 < board[y].size) {
                                        Pair(x + 1, y)
                                    } else {
                                        null
                                    }
                                }

                                Direction.DOWN -> {
                                    if (y + 1 < board.size) {
                                        Pair(x, y + 1)
                                    } else {
                                        null
                                    }
                                }

                                Direction.LEFT -> {
                                    if (x - 1 >= 0) {
                                        Pair(x - 1, y)
                                    } else {
                                        null
                                    }
                                }
                            }
                        }.mapNotNull { (newx, newy) ->
                            return@mapNotNull if (y == board.size - 1 && x == board[0].size - 1) {
                                path
                            } else if (path.contains(Pair(newx, newy))) {
                                null
                            } else if (board[newy][newx] == '.') {
                                val shortest = shortestPath.getOrDefault(Pair(newx, newy), Int.MAX_VALUE)
                                if (shortest <= path.size + 1) {
                                    null
                                } else {
                                    shortestPath[Pair(newx, newy)] = path.size + 1
                                    findPath(board, newx, newy, path + Pair(x, y))
                                }
                            } else {
                                null
                            }
                        }.filter { it.isNotEmpty() }
                        .minByOrNull { it.size }
                }

                cachePath = findPath(board, 0, 0, emptyList()) ?: return "$x,$y"
                printBoard(board, cachePath)
            }
        }
        throw IllegalStateException("No path blocked")
    }

    fun printBoard(board: Array<Array<Char>>, path: List<Pair<Int, Int>>) {
        println("BOARD")
        for (y in board.indices) {
            for (x in board[y].indices) {
                if (path.contains(Pair(x, y))) {
                    print('O')
                } else {
                    print(board[y][x])
                }
            }
            println()
        }
    }
}
