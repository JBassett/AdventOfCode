package com.github.jbassett.y2024.d01

import java.io.InputStream
import kotlin.collections.map
import kotlin.io.bufferedReader
import kotlin.math.abs

class Part1 {

    fun solve(input: InputStream): Int {
        val parsed = input.bufferedReader().readLines().map {
            val split = it.split(" ")
            Pair(split.first().toInt(), split.last().toInt())
        }

        val sortedOne = parsed.map(Pair<Int, Int>::first).sorted()
        val sortedTwo = parsed.map(Pair<Int, Int>::second).sorted()

        return sortedOne.zip(sortedTwo).sumOf { (one, two) ->
            abs(one - two)
        }
    }
}
