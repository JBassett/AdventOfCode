package com.github.jbassett.y2024.d06

import java.io.InputStream

class Part1 {
    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }
    fun solve(input: InputStream): Int {
        var direction = Direction.UP
        var position = Pair(-1, -1)
        val board = input.bufferedReader().readLines().mapIndexed { y, line ->
            val x = line.indexOf('^')
            if (x != -1) {
                position = Pair(x, y)
            }
            line.toCharArray()
        }
        board[position.second][position.first] = 'X'
        while (true){
            val (x, y) = when(direction){
                Direction.UP -> {
                    if (position.second == 0){
                        break
                    }
                    if(board[position.second - 1][position.first] == '#'){
                        direction = Direction.RIGHT
                        continue
                    }
                    board[position.second - 1][position.first] = 'X'

                    Pair(position.first, position.second - 1)
                }
                Direction.DOWN -> {
                    if (position.second == board.size - 1){
                        break
                    }
                    if(board[position.second + 1][position.first] == '#'){
                        direction = Direction.LEFT
                        continue
                    }
                    board[position.second + 1][position.first] = 'X'

                    Pair(position.first, position.second + 1)
                }
                Direction.LEFT -> {
                    if (position.first == 0){
                        break
                    }
                    if(board[position.second][position.first - 1] == '#'){
                        direction = Direction.UP
                        continue
                    }
                    board[position.second][position.first - 1] = 'X'

                    Pair(position.first - 1, position.second)
                }
                Direction.RIGHT -> {
                    if (position.first == board[0].size - 1){
                        break
                    }
                    if(board[position.second][position.first + 1] == '#'){
                        direction = Direction.DOWN
                        continue
                    }
                    board[position.second][position.first + 1] = 'X'

                    Pair(position.first + 1, position.second)
                }
            }
            position = Pair(x, y)
        }
        return board.sumOf { it.map { if (it == 'X') 1 else 0 }.sum() }
    }
}
