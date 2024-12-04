package com.github.jbassett.y2024.d04

import java.io.InputStream

class Part1 {
    fun solve(input: InputStream): Int {
        val board = input.bufferedReader().readLines().map { it }

        return board.mapIndexed { y, line ->
            line.mapIndexed { x, c ->
                if (c == 'X') {
                    val xPlus = try {
                        listOf(board[y][x + 1], board[y][x + 2], board[y][x + 3]).joinToString("")
                    } catch (e: IndexOutOfBoundsException) {
                        ""
                    }
                    val xMinus = try {
                        listOf(board[y][x - 1], board[y][x - 2], board[y][x - 3]).joinToString("")
                    } catch (e: IndexOutOfBoundsException) {
                        ""
                    }
                    val yPlus = try {
                        listOf(board[y + 1][x], board[y + 2][x], board[y + 3][x]).joinToString("")
                    } catch (e: IndexOutOfBoundsException) {
                        ""
                    }
                    val yMinus = try {
                        listOf(board[y - 1][x], board[y - 2][x], board[y - 3][x]).joinToString("")
                    } catch (e: IndexOutOfBoundsException) {
                        ""
                    }
                    val diagPlus = try {
                        listOf(board[y + 1][x + 1], board[y + 2][x + 2], board[y + 3][x + 3]).joinToString("")
                    } catch (e: IndexOutOfBoundsException) {
                        ""
                    }
                    val diagMinus = try {
                        listOf(board[y - 1][x - 1], board[y - 2][x - 2], board[y - 3][x - 3]).joinToString("")
                    } catch (e: IndexOutOfBoundsException) {
                        ""
                    }
                    val diagPlus2 = try {
                        listOf(board[y + 1][x - 1], board[y + 2][x - 2], board[y + 3][x - 3]).joinToString("")
                    } catch (e: IndexOutOfBoundsException) {
                        ""
                    }
                    val diagMinus2 = try {
                        listOf(board[y - 1][x + 1], board[y - 2][x + 2], board[y - 3][x + 3]).joinToString("")
                    } catch (e: IndexOutOfBoundsException) {
                        ""
                    }
                    listOf(xPlus, xMinus, yPlus, yMinus, diagPlus, diagMinus, diagPlus2, diagMinus2).map {
                        if (it == "MAS") {
                            1
                        } else
                            0
                    }.sum()
                } else {
                    0
                }
            }.sum()
        }.sum()
    }
}
