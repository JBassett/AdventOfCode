package com.github.jbassett.y2024.d17

import java.io.InputStream
import kotlin.math.pow

class Part1 {

    fun solve(input: InputStream): String {
        var registerA = -1
        var registerB = -1
        var registerC = -1
        var program = mutableListOf<Int>()

        input.bufferedReader().readLines().forEach { line ->
            when {
                line.startsWith("Register A: ") -> registerA = line.split(": ")[1].toInt()
                line.startsWith("Register B: ") -> registerB = line.split(": ")[1].toInt()
                line.startsWith("Register C: ") -> registerC = line.split(": ")[1].toInt()
                line.startsWith("Program: ") -> program = line.split(": ")[1]
                    .split(",")
                    .map { it.toInt() }
                    .toMutableList()
            }
        }
        val getOperand = { operand: Int ->
            when (operand) {
                in 0..3 -> operand
                4 -> registerA
                5 -> registerB
                6 -> registerC
                else -> throw IllegalStateException("Unknown operand: $operand")
            }
        }
        val output = mutableListOf<Int>()
        var i = 0
        while (i < program.size) {
            when (program[i]) {
                // The adv instruction (opcode 0) performs division. The numerator is the value in the A register. The
                // denominator is found by raising 2 to the power of the instruction's combo operand. (So, an operand
                // of 2 would divide A by 4 (2^2); an operand of 5 would divide A by 2^B.) The result of the division
                // operation is truncated to an integer and then written to the A register.
                0 -> {
                    registerA = (registerA / 2.0.pow(getOperand(program[i + 1]).toDouble())).toInt()
                    i += 2
                }
                // The bxl instruction (opcode 1) calculates the bitwise XOR of register B and the instruction's
                // literal operand, then stores the result in register B.
                1 -> {
                    registerB = registerB xor program[i + 1]
                    i += 2
                }
                // The bst instruction (opcode 2) calculates the value of its combo operand modulo 8 (thereby keeping
                // only its lowest 3 bits), then writes that value to the B register.
                2 -> {
                    registerB = (getOperand(program[i + 1]) % 8) and 0b111
                    i += 2
                }
                // The jnz instruction (opcode 3) does nothing if the A register is 0. However, if the A register is
                // not zero, it jumps by setting the instruction pointer to the value of its literal operand; if this
                // instruction jumps, the instruction pointer is not increased by 2 after this instruction.
                3 -> {
                    if (registerA != 0 && program[i + 1] != i) {
                        i = program[i + 1]
                    } else {
                        i += 2
                    }
                }
                // The bxc instruction (opcode 4) calculates the bitwise XOR of register B and register C, then stores
                // the result in register B. (For legacy reasons, this instruction reads an operand but ignores it.)
                4 -> {
                    registerB = registerB xor registerC
                    i += 2
                }
                // The out instruction (opcode 5) calculates the value of its combo operand modulo 8, then outputs that
                // value. (If a program outputs multiple values, they are separated by commas.)
                5 -> {
                    output.add(getOperand(program[i + 1]) % 8)
                    i += 2
                }
                // The bdv instruction (opcode 6) works exactly like the adv instruction except that the result is
                // stored in the B register. (The numerator is still read from the A register.)
                6 -> {
                    registerB = (registerA / 2.0.pow(getOperand(program[i + 1]).toDouble())).toInt()
                    i += 2
                }
                // The cdv instruction (opcode 7) works exactly like the adv instruction except that the result is
                // stored in the C register. (The numerator is still read from the A register.)
                7 -> {
                    registerC = (registerA / 2.0.pow(getOperand(program[i + 1]).toDouble())).toInt()
                    i += 2
                }

                else -> throw IllegalStateException("Unknown instruction[$i]: ${program[i]}")
            }
        }

        return output.joinToString(",")
    }
}
