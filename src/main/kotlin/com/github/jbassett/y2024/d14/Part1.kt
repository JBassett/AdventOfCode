package com.github.jbassett.y2024.d14

import java.io.InputStream

class Part1 {
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
        }.map { r ->
            for (t in 0..99) {
                val x = (r.x + r.vx) % boardWidth
                r.x = if (x < 0) boardWidth + x else x
                val y = (r.y + r.vy) % boardHeight
                r.y = if (y < 0) boardHeight + y else y
            }
            r
        }
        val xm = boardWidth / 2
        val ym = boardHeight / 2
        val q1 = robots.count { robot -> robot.x < xm && robot.y < ym }
        val q2 = robots.count { robot -> robot.x > xm && robot.y < ym }
        val q3 = robots.count { robot -> robot.x > xm && robot.y > ym }
        val q4 = robots.count { robot -> robot.x < xm && robot.y > ym }

        return q1.toLong() * q2 * q3 * q4
    }
}
