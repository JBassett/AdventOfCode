package com.github.jbassett.y2024.d05

import java.io.InputStream

class Part2 {
    fun solve(input: InputStream): Int {
        val orderMap = mutableMapOf<Int, MutableList<Int>>()
        val updates = mutableListOf<List<Int>>()
        input.bufferedReader().readLines().forEach { line ->
            if (line.contains("|")) {
                val parts = line.split("|")
                val key = parts[0].trim().toInt()
                val f = orderMap.getOrPut(key) { mutableListOf() }
                f.add(parts[1].trim().toInt())
            } else if (line.contains(",")) {
                updates.add(line.split(",").map { it.toInt() })
            }
        }

        return updates.map { update ->
            val sorted = update.sortedWith { o1, o2 ->
                val f1 = orderMap[o1]
                val f2 = orderMap[o2]

                if (f1?.contains(o2) == true) {
                    return@sortedWith -1
                } else if (f2?.contains(o1) == true) {
                    return@sortedWith 1
                } else {
                    return@sortedWith 0
                }
            }
            if (sorted == update) {
                0
            } else {
                sorted[update.size / 2]
            }
        }.sum()
    }
}
