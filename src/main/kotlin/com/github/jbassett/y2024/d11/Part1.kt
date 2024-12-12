package com.github.jbassett.y2024.d11

import java.io.InputStream

class Part1 {
    fun solve(input: InputStream): Long {
        val line = input.bufferedReader().readLine()

        var stones = line.trim().split(" ").map { it.toLong() }
        for (i in 0..<25) {
            stones = stones.flatMap { stone ->
                when {
                    stone == 0L -> {
                        listOf(1)
                    }

                    stone.toString().length % 2 == 0 -> {
                        val stoneString = stone.toString()
                        val mid = stoneString.length / 2
                        listOf(
                            stoneString.substring(0, mid).toLong(),
                            stoneString.substring(mid).toLong()
                        )
                    }

                    else -> {
                        listOf(stone * 2024)
                    }
                }
            }

        }

        return stones.size.toLong()
    }
}
