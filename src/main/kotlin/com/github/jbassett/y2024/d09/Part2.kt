package com.github.jbassett.y2024.d09

import java.io.InputStream

class Part2 {
    fun solve(input: InputStream): Long {
        val line = input.bufferedReader().readLine()
        var expanded = mutableListOf<Int>()
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
        while (true) {
            val id = expanded.lastOrNull { it != -1 } ?: break
            val first = compressed.indexOf(id)
            val last = compressed.lastIndexOf(id)
            val length = last - first

            var checkStart = -1
            for ((index, i) in compressed.withIndex()) {
                if (index >= first) {
                    expanded = expanded.subList(0, first)
                    break
                }

                if (i == -1 && checkStart == -1) {
                    checkStart = index
                } else if (i != -1) {
                    checkStart = -1
                }
                if (checkStart != -1 && index - checkStart == length) {
                    // replace the -1 with the ids
                    for (j in first..last) {
                        compressed[j] = -1
                    }
                    for (j in checkStart..index) {
                        compressed[j] = id
                    }
                    expanded = expanded.subList(0, first)
                    break
                }
            }

        }

        return compressed.mapIndexed { index, value ->
            if (value == -1) {
                return@mapIndexed 0L
            } else {
                index * value.toLong()
            }
        }.sum()
    }
}
