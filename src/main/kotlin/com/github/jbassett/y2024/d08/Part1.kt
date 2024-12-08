package com.github.jbassett.y2024.d08

import java.io.InputStream

class Part1 {
    fun solve(input: InputStream): Int {
        val antenna = mutableMapOf<Char, MutableSet<Pair<Int, Int>>>()
        val board = input.bufferedReader().readLines().mapIndexed { y, line ->
            line.mapIndexed { x, c ->
                if (c != '.') {
                    antenna.getOrPut(c) { mutableSetOf() }.add(Pair(x, y))
                }
                c
            }.toMutableList()
        }.toMutableList()
        val maxY = board.size
        val maxX = board[0].size

        val antiNodes = mutableSetOf<Pair<Int, Int>>()
        antenna.forEach { c, n ->
            n.forEach { a ->
                val (ax, ay) = a
                n.forEach { b ->
                    val (bx, by) = b
                    val deltaX = bx - ax
                    val deltaY = by - ay

                    val cx = bx + deltaX
                    val cy = by + deltaY
                    if (cx >= 0 && cx < maxX && cy >= 0 && cy < maxY) {
                        val anti = Pair(cx, cy)
                        if (anti != a && anti != b) {
                            antiNodes.add(Pair(cx, cy))
                        }
                    }

                    val dx = ax - deltaX
                    val dy = ay - deltaY
                    if (dx >= 0 && dx < maxX && dy >= 0 && dy < maxY) {
                        val anti = Pair(dx, dy)
                        if (anti != a && anti != b) {
                            antiNodes.add(Pair(dx, dy))
                        }
                    }
                }
            }
        }

        return antiNodes.size
    }
}
