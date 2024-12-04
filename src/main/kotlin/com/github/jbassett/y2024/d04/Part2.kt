package com.github.jbassett.y2024.d04

import java.io.InputStream

class Part2 {
    fun solve(input: InputStream): Int {
        val board = input.bufferedReader().readLines().map { it }

        return board.mapIndexed { y, line ->
            line.mapIndexed { x, c ->
                if (c == 'A') {
                    try {
                        if (((board[y - 1][x - 1] == 'M' && board[y + 1][x + 1] == 'S') ||
                                    (board[y - 1][x - 1] == 'S' && board[y + 1][x + 1] == 'M')) &&
                            ((board[y - 1][x + 1] == 'M' && board[y + 1][x - 1] == 'S') ||
                                    (board[y - 1][x + 1] == 'S' && board[y + 1][x - 1] == 'M'))
                        ) {
                            1
                        } else {
                            0
                        }
                    } catch (e: Exception) {
                        0
                    }
                } else {
                    0
                }
            }.sum()
        }.sum()
    }
}
