package com.github.jbassett.y2024.d06

import java.io.InputStream

class Part2 {
    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }
    fun solve(input: InputStream): Int {

        var position = Pair(-1, -1)
        val board = input.bufferedReader().readLines().mapIndexed { y, line ->
            val x = line.indexOf('^')
            if (x != -1) {
                position = Pair(x, y)
            }
            line.toCharArray()
        }
        val start = position

        board[position.second][position.first] = 'X'
        return board.mapIndexed { y, line ->
            line.mapIndexed x@{ x, c ->
                if (c != '.'){
                    return@x 0
                }
                var direction = Direction.UP
                position = start
                val localBoard = board.map { it.copyOf() }
                localBoard[y][x] = '#'

                while (true){
                    val (x, y) = when(direction){
                        Direction.UP -> {
                            if (position.second == 0){
                                return@x 0
                            }
                            if(localBoard[position.second - 1][position.first] == '#'){
                                direction = Direction.RIGHT
                                continue
                            }
                            if (localBoard[position.second - 1][position.first] == '^'){
                                return@x 1
                            }
                            localBoard[position.second - 1][position.first] = '^'

                            Pair(position.first, position.second - 1)
                        }
                        Direction.DOWN -> {
                            if (position.second == localBoard.size - 1){
                                return@x 0
                            }
                            if(localBoard[position.second + 1][position.first] == '#'){
                                direction = Direction.LEFT
                                continue
                            }
                            if (localBoard[position.second + 1][position.first] == 'v'){
                                return@x 1
                            }
                            localBoard[position.second + 1][position.first] = 'v'

                            Pair(position.first, position.second + 1)
                        }
                        Direction.LEFT -> {
                            if (position.first == 0){
                                return@x 0
                            }
                            if(localBoard[position.second][position.first - 1] == '#'){
                                direction = Direction.UP
                                continue
                            }
                            if (localBoard[position.second][position.first - 1] == '<'){
                                return@x 1
                            }
                            localBoard[position.second][position.first - 1] = '<'


                            Pair(position.first - 1, position.second)
                        }
                        Direction.RIGHT -> {
                            if (position.first == localBoard[0].size - 1){
                                return@x 0
                            }
                            if(localBoard[position.second][position.first + 1] == '#'){
                                direction = Direction.DOWN
                                continue
                            }
                            if (localBoard[position.second][position.first + 1] == '>'){
                                return@x 1
                            }
                            localBoard[position.second][position.first + 1] = '>'

                            Pair(position.first + 1, position.second)
                        }
                    }
                    position = Pair(x, y)
                }
                return@x 0
            }.sum()
        }.sum()
    }
}
