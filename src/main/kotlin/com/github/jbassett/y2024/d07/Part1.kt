package com.github.jbassett.y2024.d07

import java.io.InputStream

class Part1 {
    enum class Operation {
        ADD, MULTIPLY
    }

    fun solve(input: InputStream): Long {
        return input.bufferedReader().readLines().map { lines ->
            val topSplit = lines.split(":")
            val total = topSplit[0].trim().toLong()
            val inputs = topSplit[1].trim().split(" ").map { it.trim().toLong() }

            val operations: MutableSet<List<Operation>> = mutableSetOf()
            operations.add(List(inputs.size - 1) { Operation.ADD })
            for (i in 0 until inputs.size - 1) {
                for (j in 0 until inputs.size - 1) {
                    val n: List<List<Operation>> = operations.map {
                        val copy = it.toMutableList()
                        copy[j] = Operation.MULTIPLY
                        copy
                    }
                    operations.addAll(n)
                }
            }
            operations.forEach { operation->
                var acc = inputs[0]
                for(i in 1 ..< inputs.size) {
                    val two = inputs[i]
                    if (operation[i-1] == Operation.ADD) {
                        acc += two
                    } else {
                        acc *= two
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
