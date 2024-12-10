package com.github.jbassett.y2024.d09

import java.io.InputStream

class Part1 {
    fun solve(input: InputStream): Long {
        val line = input.bufferedReader().readLine()
        val expanded = mutableListOf<Int>()
        line.forEachIndexed { index, c ->
            if (index % 2 == 0) {
                val id = (index / 2)
                for (i in 0 until c.toString().toInt()) {
                    expanded.add(id)
                }
            } else {
                for (i in 0 until c.toString().toInt()) {
                    expanded.add(-1)
                }
            }
        }

        val compressed = expanded.toMutableList()
        for (i in expanded.size - 1 downTo 0) {
            if (compressed[i] == -1) {
                compressed.removeAt(i)
                continue
            }
            val char = compressed[i]
            val index = compressed.indexOf(-1)
            if (index == -1) {
                break
            }
            compressed[index] = char
            compressed.removeAt(i)
        }

        return compressed.mapIndexed { index, value ->
            index * value.toLong()
        }.sum()
    }
}
