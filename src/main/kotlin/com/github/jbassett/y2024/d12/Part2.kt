package com.github.jbassett.y2024.d12

import java.io.InputStream

class Part2 {
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

                    areaToPerimeter.add(points.size to calculatePerimeter(board, c, points))
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
        return Part1.Direction.entries.sumOf { direction ->
            val location = when (direction) {
                Part1.Direction.UP -> {
                    if (y - 1 >= 0) {
                        Pair(x, y - 1)
                    } else {
                        null
                    }
                }

                Part1.Direction.RIGHT -> {
                    if (x + 1 < board[y].size) {
                        Pair(x + 1, y)
                    } else {
                        null
                    }
                }

                Part1.Direction.DOWN -> {
                    if (y + 1 < board.size) {
                        Pair(x, y + 1)
                    } else {
                        null
                    }
                }

                Part1.Direction.LEFT -> {
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

    fun calculatePerimeter(
        board: List<List<Char>>,
        char: Char,
        points: Set<Pair<Int, Int>>
    ): Int {
        val maxX = board[0].size - 1
        val maxY = board.size - 1

        var permVert = 0
        for (y in 0..maxY) {
            var wasLastTopCounted = false
            var wasLastBottomCounted = false
            for (x in 0..maxX) {
                if (Pair(x, y) in points) {
                    val shouldTopCount = y - 1 < 0 || board[y - 1][x] != char
                    if (shouldTopCount && !wasLastTopCounted) {
                        wasLastTopCounted = true
                        permVert++
                    } else if (!shouldTopCount && wasLastTopCounted) {
                        wasLastTopCounted = false
                    }

                    val shouldBottomCount = y + 1 >= board.size || board[y + 1][x] != char
                    if (shouldBottomCount && !wasLastBottomCounted) {
                        wasLastBottomCounted = true
                        permVert++
                    } else if (!shouldBottomCount && wasLastBottomCounted) {
                        wasLastBottomCounted = false
                    }
                } else {
                    wasLastTopCounted = false
                    wasLastBottomCounted = false
                }
            }
        }
        var permHor = 0
        for (x in 0..maxX) {
            var wasLastLeftCounted = false
            var wasLastRightCounted = false
            for (y in 0..maxY) {
                if (Pair(x, y) in points) {
                    val shouldLeftCount = x - 1 < 0 || board[y][x - 1] != char
                    if (shouldLeftCount && !wasLastLeftCounted) {
                        wasLastLeftCounted = true
                        permHor++
                    } else if (!shouldLeftCount && wasLastLeftCounted) {
                        wasLastLeftCounted = false
                    }

                    val shouldRightCount = x + 1 >= board.size || board[y][x + 1] != char
                    if (shouldRightCount && !wasLastRightCounted) {
                        wasLastRightCounted = true
                        permHor++
                    } else if (!shouldRightCount && wasLastRightCounted) {
                        wasLastRightCounted = false
                    }
                } else {
                    wasLastLeftCounted = false
                    wasLastRightCounted = false
                }
            }
        }
        return permVert + permHor
    }
}
