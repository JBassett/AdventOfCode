package com.github.jbassett.y2024.d03

import java.io.InputStream

class Part1 {
    val regex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
    fun solve(input: InputStream): Int {
        return input.bufferedReader().readLines().map {
            regex.findAll(it).map { match ->
                val (a, b) = match.destructured
                a.toInt() * b.toInt()
            }.sum()
        }.sum()
    }
}
