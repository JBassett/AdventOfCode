package com.github.jbassett.y2024.d14

import java.io.InputStream
import java.lang.Thread.sleep
import kotlin.math.abs

class Part2 {
    data class Robot(
        var x: Int,
        var y: Int,

        val vx: Int,
        val vy: Int,
    )

    fun solve(input: InputStream, sample: Boolean = false): Long {

        val boardWidth = if (sample) 11 else 101
        val boardHeight = if (sample) 7 else 103

        val robots = input.bufferedReader().readLines().map { line ->
            val split = line.split(" ")
            val position = split[0].substring(2)
            val velocity = split[1].substring(2)

            Robot(
                x = position.split(",")[0].toInt(),
                y = position.split(",")[1].toInt(),
                vx = velocity.split(",")[0].toInt(),
                vy = velocity.split(",")[1].toInt()
            )
        }

        var maxAverageNeighborCount = 0.0
        for (t in 0..8158) {
            robots.forEach { r ->
                val x = (r.x + r.vx) % boardWidth
                r.x = if (x < 0) boardWidth + x else x
                val y = (r.y + r.vy) % boardHeight
                r.y = if (y < 0) boardHeight + y else y
            }

            val averageNeighborCount = robots.sumOf { r -> numNeighbors(r, robots) } / robots.size.toDouble()
            if (maxAverageNeighborCount <= averageNeighborCount) {
                maxAverageNeighborCount = averageNeighborCount
//                printBoard(t, robots)
                sleep(250)
            }
        }

        return 8159
    }

    fun numNeighbors(robot: Robot, robots: List<Robot>): Int {
        return robots.count { r ->
            abs(r.x - robot.x) < 2 && abs(r.y - robot.y) < 2
        }
    }

    fun printBoard(i: Int, robots: List<Robot>) {
        println("\nIteration: $i")
        for (x in 0..100) {
            for (y in 0..102) {
                when (val c = robots.count { robot -> robot.x == x && robot.y == y }) {
                    0 -> print("  ")
                    in 1..9 -> print(" $c")
                    else -> print(c)
                }
            }
            println()
        }
    }
}

