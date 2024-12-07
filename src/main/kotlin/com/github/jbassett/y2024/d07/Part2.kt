package com.github.jbassett.y2024.d07

import java.io.InputStream

class Part2 {
    enum class Operation {
        ADD, MULTIPLY, CONCAT
    }
    fun solve(input: InputStream): Long {
        return input.bufferedReader().readLines().map { lines ->
            val topSplit = lines.split(":")
            val total = topSplit[0].trim().toLong()
            val inputs = topSplit[1].trim().split(" ").map { it.trim().toLong() }

            val allOperations: MutableSet<List<Operation>> = mutableSetOf()
            allOperations.add(List(inputs.size - 1) { Operation.ADD })
            for (i in 0 until inputs.size - 1) {
                for (j in 0 until inputs.size - 1) {
                    val n: List<List<Operation>> = allOperations.flatMap {
                        Operation.entries.map { op ->
                            val copy = it.toMutableList()
                            copy[j] = op
                            copy
                        }
                    }
                    allOperations.addAll(n)
                }
            }
            println("Total: $total, AllOpSize: ${allOperations.size}")
            allOperations.forEach { operation ->
                var acc = inputs[0]
                for (i in 1..<inputs.size) {
                    val two = inputs[i]
                    when(operation[i - 1]) {
                        Operation.ADD -> acc += two
                        Operation.MULTIPLY -> acc *= two
                        Operation.CONCAT -> acc = "$acc$two".toLong()
                    }
                    if (acc > total) {
                        break
                    }
                }
                if (acc == total) {
                    return@map total
                }
            }
            return@map 0
        }.sum()
    }
}
