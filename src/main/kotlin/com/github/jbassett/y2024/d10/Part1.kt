package com.github.jbassett.y2024.d10

import java.io.InputStream

class Part1 {
    enum class Direction {
        UP, RIGHT, DOWN, LEFT
    }

    fun solve(input: InputStream): Long {
        val trailHeads = mutableListOf<Pair<Int, Int>>()
        val board = input.bufferedReader().readLines().mapIndexed { y, line ->
            line.mapIndexed { x, c ->
                if (c == '0') {
                    trailHeads.add(Pair(x, y))
                }
                c.toString().toInt()
            }
        }
        return trailHeads.sumOf { (x, y) ->
            getValidTrails(board, 0, x, y, emptyList()).size.toLong()
        }
    }

    private fun getValidTrails(
        board: List<List<Int>>,
        currentElevation: Int,
        x: Int,
        y: Int,
        history: List<Pair<Int, Int>>
    ): Set<Pair<Int, Int>> {
        if (currentElevation == 9) {
            return setOf(Pair(x, y))
        }
        return Direction.entries.mapNotNull { direction ->
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
        }.flatMap { (x, y) ->
            if (board[y][x] == currentElevation + 1) {
                getValidTrails(board, currentElevation + 1, x, y, history + Pair(x, y))
            } else {
                emptySet()
            }
        }.toSet()
    }

}
