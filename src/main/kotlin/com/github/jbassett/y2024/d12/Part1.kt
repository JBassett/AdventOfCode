package com.github.jbassett.y2024.d12

import java.io.InputStream

class Part1 {
    enum class Direction {
        UP, RIGHT, DOWN, LEFT
    }

    fun solve(input: InputStream): Long {
        val board = input.bufferedReader().readLines().mapIndexed { y, line ->
            line.mapIndexed { x, c ->
                c
            }
        }
        val pointsEvaluated = mutableSetOf<Pair<Int, Int>>()
        val areaToPerimeter = mutableListOf<Pair<Int, Int>>()
        board.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (Pair(x, y) !in pointsEvaluated) {
                    val points = HashSet<Pair<Int, Int>>()
                    val perimeter = buildRegion(points, board, c, x, y)
                    areaToPerimeter.add(points.size to perimeter)
                    pointsEvaluated.addAll(points)
                }
            }
        }


        return areaToPerimeter.sumOf { (area, perimeter) ->
            area.toLong() * perimeter
        }
    }

    fun buildRegion(
        seenPoints: MutableSet<Pair<Int, Int>>,
        board: List<List<Char>>,
        char: Char,
        x: Int,
        y: Int,
    ): Int {
        seenPoints.add(Pair(x, y))
        return Direction.entries.sumOf { direction ->
            val location = when (direction) {
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
            return@sumOf if (location == null) {
                1
            } else if (location in seenPoints) {
                0
            } else {
                val (x, y) = location
                if (board[y][x] == char) {
                    buildRegion(seenPoints, board, char, x, y)
                } else {
                    1
                }
            }
        }
    }
}
