package com.github.jbassett.y2024.d23

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolverY24D23Test {
    private val samepleInput = """
            kh-tc
            qp-kh
            de-cg
            ka-co
            yn-aq
            qp-ub
            cg-tb
            vc-aq
            tb-ka
            wh-tc
            yn-cg
            kh-ub
            ta-co
            de-co
            tc-td
            tb-wq
            wh-td
            ta-ka
            td-qp
            aq-cg
            wq-ub
            ub-vc
            de-ta
            wq-aq
            wq-vc
            wh-yn
            ka-de
            kh-ta
            co-tc
            wh-qp
            tb-vc
            td-yn
        """.trimIndent().lines()

    @Test
    fun group() {
        assertEquals(12, SolverY24D23.group(SolverY24D23.parse(samepleInput)).size)
    }

    @Test
    fun part1_sample() {
        val answer = SolverY24D23.part1(samepleInput)
        assertEquals(7, answer)
    }

    @Test
    fun part1() {
        val input = javaClass.getClassLoader().getResourceAsStream("2024/23/input.txt")!!.bufferedReader().readLines()
        val answer = SolverY24D23.part1(input)
        assertEquals(1599, answer)
    }

    @Test
    fun part2() {
    }

}