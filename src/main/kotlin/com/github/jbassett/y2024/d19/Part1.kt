package com.github.jbassett.y2024.d19

import java.io.InputStream

class Part1 {
    fun solve(input: InputStream): Int {
        val inputReader = input.bufferedReader()
        val towels = inputReader.readLine().split(", ").sortedByDescending { it.length }
        val designs = inputReader.readLines().filter { !it.isBlank() }


        return designs.mapIndexed { i, design ->
            println("Working on $i / ${designs.size}")
            println("Design: $design")
            if (addTowel(design, towels)) {
                1
            } else {
                0
            }
        }.sum()
    }

    val cache = mutableMapOf<String, Boolean>()

    fun addTowel(design: String, towels: List<String>): Boolean {
        if (design.isEmpty()) {
            return true
        }
        return cache.getOrPut(design) {
            towels.any { towel ->
                design.startsWith(towel) && addTowel(design.substring(towel.length), filterTowels(design, towels))
            }
        }
    }

    fun filterTowels(design: String, towels: List<String>): List<String> {
        return towels.filter { towel ->
            towel.length <= design.length
        }
    }
}
