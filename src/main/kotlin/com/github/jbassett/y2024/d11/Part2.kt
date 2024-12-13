package com.github.jbassett.y2024.d11

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import java.io.InputStream

class Part2 {
    fun solve(input: InputStream): Long {
        val stones = input.bufferedReader().readLine().trim().split(" ").map { it.toLong() }

        return stones
            .mapIndexed { i, initial ->
                getStoneCount(0, initial)
            }
            .sum()
    }

    private val cache = mutableMapOf<String, Long>()

    fun getStoneCount(
        depth: Int,
        stone: Long
    ): Long {
        return cache.getOrPut("$depth,$stone") {
            if (depth == 75)
                return@getOrPut 1

            return@getOrPut when {
                stone == 0L -> {
                    getStoneCount(depth + 1, 1L)
                }

                stone.toString().length % 2 == 0 -> {
                    val stoneString = stone.toString()
                    val mid = stoneString.length / 2
                    listOf(
                        getStoneCount(depth + 1, stoneString.substring(0, mid).toLong()),
                        getStoneCount(depth + 1, stoneString.substring(mid).toLong())
                    ).sum()

                }

                else -> {
                    getStoneCount(depth + 1, stone * 2024)
                }
            }
        }
    }
}
