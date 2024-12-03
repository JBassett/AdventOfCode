package com.github.jbassett.y2024.d02

import java.io.InputStream

class Part1 {
    fun solve(input: InputStream): Int {
        return input.bufferedReader().readLines().map { line ->
            val split = line.split(" ").map { it.toInt() }
            val diffs = (1..< split.size).map{ i ->
                val first = split[i-1]
                val second = split[i]
                first - second
            }
            if (diffs.all { it in 1..3 } || diffs.all { it in -3..-1 }) {
                1
            } else {
                0
            }
        }.sum()
    }
}
