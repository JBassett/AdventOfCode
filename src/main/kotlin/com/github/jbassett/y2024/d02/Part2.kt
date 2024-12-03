package com.github.jbassett.y2024.d02

import java.io.InputStream

class Part2 {
    fun solve(input: InputStream): Int {
        return input.bufferedReader().readLines().map line@{ line ->
            val split = line.split(" ").map { it.toInt() }
            if (isValid(split)) {
                1
            } else {
                for (i in split.indices) {
                    val newElements = split.toMutableList()
                    newElements.removeAt(i)
                    if (isValid(newElements)) {
                        return@line 1
                    }
                }
                return@line 0
            }
        }.sum()
    }

    private fun isValid(elements: List<Int>): Boolean {
        val diffs = (1..<elements.size).map { i ->
            val first = elements[i - 1]
            val second = elements[i]
            first - second
        }
        return diffs.all { it in 1..3 } || diffs.all { it in -3..-1 }
    }
}
