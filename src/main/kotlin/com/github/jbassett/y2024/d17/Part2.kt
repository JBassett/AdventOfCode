package com.github.jbassett.y2024.d17

import java.io.InputStream
import kotlin.math.pow

class Part2 {

    fun solve(input: InputStream): Long {
        var startRegisterA = -1
        var startRegisterB = -1
        var startRegisterC = -1
        var program = mutableListOf<Int>()

        input.bufferedReader().readLines().forEach { line ->
            when {
                line.startsWith("Register A: ") -> startRegisterA = line.split(": ")[1].toInt()
                line.startsWith("Register B: ") -> startRegisterB = line.split(": ")[1].toInt()
                line.startsWith("Register C: ") -> startRegisterC = line.split(": ")[1].toInt()
                line.startsWith("Program: ") -> program = line.split(": ")[1]
                    .split(",")
                    .map { it.toInt() }
                    .toMutableList()
            }
        }

        fun findA(currentA: Long): Long? =
            (currentA..currentA + 8).firstNotNullOfOrNull { a ->
                val output = run(a, startRegisterB, startRegisterC, program)

                if (program.takeLast(output.size) == output) {
                    if (program == output) {
                        a
                    } else {
                        findA(maxOf(a shl 3, 8))
                    }
                } else {
                    null
                }
            }

        return findA(0L)!!
    }

    private fun run(
        j: Long,
        startRegisterB: Int,
        startRegisterC: Int,
        program: MutableList<Int>
    ): List<Int> {
        var registerA = j
        var registerB = startRegisterB.toLong()
        var registerC = startRegisterC.toLong()

        val getOperand: (Int) -> Long = { operand: Int ->
            when (operand) {
                in 0..3 -> operand.toLong()
                4 -> registerA
                5 -> registerB
                6 -> registerC
                else -> throw IllegalStateException("Unknown operand: $operand")
            }
        }
        val output = mutableListOf<Int>()
        var i = 0
        loop@ while (i < program.size) {
            when (program[i]) {
                0 -> {
                    registerA = (registerA / 2.0.pow(getOperand(program[i + 1]).toDouble())).toLong()
                    i += 2
                }

                1 -> {
                    registerB = registerB xor program[i + 1].toLong()
                    i += 2
                }

                2 -> {
                    registerB = (getOperand(program[i + 1]) % 8) and 0b111
                    i += 2
                }

                3 -> {
                    if (registerA != 0L && program[i + 1] != i) {
                        i = program[i + 1].toInt()
                    } else {
                        i += 2
                    }
                }

                4 -> {
                    registerB = registerB xor registerC
                    i += 2
                }

                5 -> {
                    output.add((getOperand(program[i + 1]) % 8).toInt())
                    i += 2
                }

                6 -> {
                    registerB = (registerA / 2.0.pow(getOperand(program[i + 1]).toDouble())).toLong()
                    i += 2
                }

                7 -> {
                    registerC = (registerA / 2.0.pow(getOperand(program[i + 1]).toDouble())).toLong()
                    i += 2
                }

                else -> throw IllegalStateException("Unknown instruction[$i]: ${program[i]}")
            }
        }
        return output
    }
}
