package com.github.jbassett.y2024.d19

import java.io.InputStream

class Part2 {
    fun solve(input: InputStream): Long {
        val inputReader = input.bufferedReader()
        val towels = inputReader.readLine().split(", ").sortedByDescending { it.length }
        val designs = inputReader.readLines().filter { !it.isBlank() }


        return designs.mapIndexed { i, design ->
            println("Working on $i / ${designs.size}")
            println("Design: $design")
            addTowel(design, towels)
        }.sum()
    }

    val cache = mutableMapOf<String, Long>()

    fun addTowel(design: String, towels: List<String>): Long {
        if (design.isEmpty()) {
            return 1
        }
        return cache.getOrPut(design) {
            towels.sumOf { towel ->
                if (design.startsWith(towel)) {
                    addTowel(design.substringAfter(towel), towels)
                } else {
                    0L
                }
            }
        }
    }
}
