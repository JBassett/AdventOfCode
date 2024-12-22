package com.github.jbassett.y2024.d22

object SolverY24D22 {

    fun Long.nextRandom(): Long {
        var next = (this xor (this * 64)) % 16777216
        next = (next xor (next / 32)) % 16777216
        next = (next xor (next * 2048)) % 16777216
        return next
    }

    fun part1(input: List<String>): Long {
        return input
            .map { it.toLong() }
            .sumOf {
                var next = it
                for (i in 0..<2000) {
                    next = next.nextRandom()
                }
                next
            }
    }

    fun part2(input: List<String>): Long {
        println("Step One")
        val changes = input
            .map { it.toLong() }
            .map {
                val randoms = mutableListOf<Long>(it)
                for (i in 0..<2000) {
                    randoms.add(randoms.last().nextRandom())
                }
                randoms.toList()
            }.map {
                it.map { random ->
                    random.toString().last().toString().toInt()
                }
            }.map { lastDigits ->
                lastDigits.mapIndexedNotNull { i, d ->
                    if (i == 0) {
                        null
                    } else {
                        d - lastDigits[i - 1] to d
                    }
                }
            }
        println("Step Two")
        val sequences = changes.mapIndexed { i, changes ->
            val sequences = mutableMapOf<List<Int>, Int>()
            changes.windowed(4) { window ->
                val seq = window.map { it.first }
                val b = window.last().second
                if (seq !in sequences) {
                    sequences[seq] = b
                }
            }
            sequences
        }

        println("Step Three")
        val seenSequences = mutableSetOf<List<Int>>()
        var bananas = Int.MIN_VALUE
        var bestSequence = emptyList<Int>()
        sequences.forEach { entry ->
            entry.keys.forEach { seq ->
                if (seenSequences.add(seq)) {
                    val ban = sequences.sumOf { b -> b[seq] ?: 0 }
                    if (ban > bananas) {
                        bananas = ban
                        bestSequence = seq
                    }
                }
            }
        }



        return bananas.toLong()
    }
}
