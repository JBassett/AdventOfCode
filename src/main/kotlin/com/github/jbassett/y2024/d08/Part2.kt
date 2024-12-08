package com.github.jbassett.y2024.d08

import java.io.InputStream
import java.util.stream.Collectors

class Part2 {
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
                    if (a == b) {
                        return@forEach
                    }
                    antiNodes.add(a)
                    antiNodes.add(b)
                    val (bx, by) = b
                    val deltaX = bx - ax
                    val deltaY = by - ay
                    var i = 1
                    while (true) {
                        val cx = bx + deltaX * i
                        val cy = by + deltaY * i
                        if (cx >= 0 && cx < maxX && cy >= 0 && cy < maxY) {
                            antiNodes.add(Pair(cx, cy))
                        }

                        val dx = ax - deltaX * i
                        val dy = ay - deltaY * i
                        if (dx >= 0 && dx < maxX && dy >= 0 && dy < maxY) {
                            antiNodes.add(Pair(dx, dy))
                        }
                        if (!(cx >= 0 && cx < maxX && cy >= 0 && cy < maxY) && !(dx >= 0 && dx < maxX && dy >= 0 && dy < maxY)) {
                            break
                        }
                        i++
                    }
                }
            }
        }

        return antiNodes.size
    }
}
