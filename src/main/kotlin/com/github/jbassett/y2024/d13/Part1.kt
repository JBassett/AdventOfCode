package com.github.jbassett.y2024.d13

import java.io.InputStream

class Part1 {
    private val A_COST = 3
    private val B_COST = 1

    fun solve(input: InputStream): Long {
        return input.bufferedReader().readLines().mapIndexedNotNull { i, line ->
            return@mapIndexedNotNull if (i % 4 == 0 || i % 4 == 1) {
                val x = line.substring(line.indexOf("X+") + 2, line.indexOf(",")).toInt()
                val y = line.substring(line.indexOf("Y+") + 2).toInt()
                Pair(x, y)
            } else if (i % 4 == 2) {
                val x = line.substring(line.indexOf("X=") + 2, line.indexOf(",")).toInt()
                val y = line.substring(line.indexOf("Y=") + 2).toInt()
                Pair(x, y)
            } else {
                null
            }
        }
            .chunked(3)
            .sumOf { game ->
                val (aX, aY) = game[0]
                val (bX, bY) = game[1]
                val (pX, pY) = game[2]

                var cheapest = Int.MAX_VALUE

                for (aGuess in 0..100) {
                    for (bGuess in 0..100) {
                        val cost = (aGuess * A_COST) + (bGuess * B_COST)
                        if (cost > cheapest) continue

                        if ((aX * aGuess + bX * bGuess) == pX && (aY * aGuess + bY * bGuess) == pY) {
                            cheapest = cost
                        }
                    }
                }

                if (cheapest != Int.MAX_VALUE) {
                    cheapest.toLong()
                } else {
                    0L
                }
            }

    }
}
