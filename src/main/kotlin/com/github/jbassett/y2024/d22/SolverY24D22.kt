package com.github.jbassett.y2024.d22

object SolverY24D22 {

    fun Long.nextRandom(): Long {
        var next = (this xor (this * 64)) % 16777216
        next = (next xor (next / 32)) % 16777216
        next = (next xor (next * 2048)) % 16777216
        return next
    }

    fun part1(input: List<String>): Long {
        return input
            .map { it.toLong() }
            .sumOf {
                var next = it
                for (i in 0..<2000) {
                    next = next.nextRandom()
                }
                next
            }
    }

}
