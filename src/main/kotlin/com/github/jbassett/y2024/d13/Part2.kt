package com.github.jbassett.y2024.d13

import java.io.InputStream

class Part2 {

    private val A_COST = 3
    private val B_COST = 1

    fun solve(input: InputStream): Long {
        return input.bufferedReader().readLines().mapIndexedNotNull { i, line ->
            return@mapIndexedNotNull if (i % 4 == 0 || i % 4 == 1) {
                val x = line.substring(line.indexOf("X+") + 2, line.indexOf(",")).toLong()
                val y = line.substring(line.indexOf("Y+") + 2).toLong()
                Pair(x, y)
            } else if (i % 4 == 2) {
                val x = line.substring(line.indexOf("X=") + 2, line.indexOf(",")).toLong() + 10000000000000
                val y = line.substring(line.indexOf("Y=") + 2).toLong() + 10000000000000
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

                val determ = aX * bY - aY * bX
                if (determ == 0L) return@sumOf 0

                val determinantA = pX * bY - pY * bX
                val determinantB = aX * pY - aY * pX

                val a = determinantA / determ
                val b = determinantB / determ

                return@sumOf if (a > 0 && b > 0 && (a * aX + b * bX) == pX && (a * aY + b * bY) == pY) {
                    a * A_COST + b * B_COST
                } else
                    0L
            }
    }
}
