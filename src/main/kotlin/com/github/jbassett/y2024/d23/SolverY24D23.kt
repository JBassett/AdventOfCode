package com.github.jbassett.y2024.d23

object SolverY24D23 {

    fun parse(input: List<String>): Map<String, Set<String>> {
        val computerToConnections = mutableMapOf<String, MutableSet<String>>()
        val connections = input.map { line ->
            line.split("-").toSet()
        }.toSet()

        connections.forEach {
            it.forEach { computer ->
                computerToConnections.getOrPut(computer) { mutableSetOf<String>() }.addAll(it)
            }
        }

        return computerToConnections
    }

    fun group(allConnections: Map<String, Set<String>>): Set<Set<String>> {
        return allConnections.flatMap { (computer1, connections) ->
            connections.flatMap { computer2 ->
                allConnections
                    .filter { (_, connections) -> connections.containsAll(setOf(computer1, computer2)) }
                    .map { (computer3, _) -> setOf(computer1, computer2, computer3) }
            }
        }
            .toSet()
            .filter { it.size == 3 }
            .toSet()
            .also {
                println("Found ${it.size} connections")
            }
    }

    fun part1(input: List<String>): Int {
        return group(parse(input)).count { it.any { it.startsWith("t") } }
    }

    fun part2(input: List<String>): Long {
        return -1
    }
}
