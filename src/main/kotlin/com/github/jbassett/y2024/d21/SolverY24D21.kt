package com.github.jbassett.y2024.d21

import kotlin.math.abs

object SolverY24D21 {

    enum class Move {
        UP, DOWN, LEFT, RIGHT, CLICK;

        override fun toString(): String {
            return when (this) {
                Move.UP -> "^"
                Move.DOWN -> "v"
                Move.LEFT -> "<"
                Move.RIGHT -> ">"
                Move.CLICK -> "A"
            }
        }
    }

    fun <T> findKey(
        board: List<List<T?>>,
        char: T
    ): Pair<Int, Int> {
        board.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c == char) {
                    return Pair(x, y)
                }
            }
        }
        throw IllegalStateException()
    }

    val keypadOneBoard = listOf(
        listOf('7', '8', '9'),
        listOf('4', '5', '6'),
        listOf('1', '2', '3'),
        listOf(null, '0', 'A')
    )

    fun keypadOneClick(
        start: Char,
        end: Char
    ): List<Move> {
        val startPos = findKey(keypadOneBoard, start)
        val endPos = findKey(keypadOneBoard, end)
        // If we need to go up, do that first to avoid null
        return if (endPos.second < startPos.second) {
            val ups = (1..startPos.second - endPos.second).map { u ->
                Move.UP
            }
            val horizontal = (1..abs(startPos.first - endPos.first)).map { v ->
                if (startPos.first - endPos.first > 0) {
                    Move.LEFT
                } else {
                    Move.RIGHT
                }
            }
            ups + horizontal + listOf(Move.CLICK)
        } else {
            val horizontal = (1..abs(startPos.first - endPos.first)).map { v ->
                if (startPos.first - endPos.first > 0) {
                    Move.LEFT
                } else {
                    Move.RIGHT
                }
            }
            val downs = (1..endPos.second - startPos.second).map { u ->
                Move.DOWN
            }
            horizontal + downs + listOf(Move.CLICK)
        }
    }

    val keypadTwoBoard = listOf(
        listOf(null, Move.UP, Move.CLICK),
        listOf(Move.LEFT, Move.DOWN, Move.RIGHT),
    )

    fun keypadTwoClick(
        start: Move,
        end: Move
    ): List<Move> {
        val startPos = findKey(keypadTwoBoard, start)
        val endPos = findKey(keypadTwoBoard, end)
        return if (endPos.second < startPos.second) {
            val horizontal = (1..abs(startPos.first - endPos.first)).map { v ->
                if (startPos.first - endPos.first > 0) {
                    Move.LEFT
                } else {
                    Move.RIGHT
                }
            }
            val ups = (1..startPos.second - endPos.second).map { u ->
                Move.UP
            }
            horizontal + ups + listOf(Move.CLICK)
        } else {
            val downs = (1..endPos.second - startPos.second).map { u ->
                Move.DOWN
            }
            val horizontal = (1..abs(startPos.first - endPos.first)).map { v ->
                if (startPos.first - endPos.first > 0) {
                    Move.LEFT
                } else {
                    Move.RIGHT
                }
            }
            downs + horizontal + listOf(Move.CLICK)
        }
    }

    fun part1(input: List<String>): Long {
        var numericKeypad = 'A'
        var directionalKeypad1 = Move.CLICK
        var directionalKeypad2 = Move.CLICK
        return input.sumOf { code ->

            val moves = code.flatMap { char ->
                val moves = keypadOneClick(numericKeypad, char)
                numericKeypad = char
                moves
            }.flatMap { neededMove ->
                val moves = keypadTwoClick(directionalKeypad1, neededMove)
                directionalKeypad1 = neededMove
                moves
            }.flatMap { neededMove ->
                val moves = keypadTwoClick(directionalKeypad2, neededMove)
                directionalKeypad2 = neededMove
                moves
            }
            moves.size * code.dropLast(1).toLong()
        }
    }

    fun part2(input: List<String>): Long {
        return -1
    }
}
