package com.github.jbassett.y2024.d01

import java.io.InputStream
import kotlin.collections.map
import kotlin.io.bufferedReader

class Part2 {

    fun solve(input: InputStream): Int {
        val parsed = input.bufferedReader().readLines().map {
            val split = it.split(" ")
            Pair(split.first().toInt(), split.last().toInt())
        }

        val twoCount = mutableMapOf<Int, Int>()
        parsed.map(Pair<Int, Int>::second).forEach {
            twoCount.put(it, twoCount.getOrPut(it) { 0 } + 1)
        }

        return parsed.map(Pair<Int, Int>::first).sumOf {
            it * twoCount.getOrPut(it) { 0 }
        }
    }
}
