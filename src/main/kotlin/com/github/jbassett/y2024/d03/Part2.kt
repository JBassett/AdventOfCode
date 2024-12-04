package com.github.jbassett.y2024.d03

import java.io.InputStream

class Part2 {
    val mulRegex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
    val enableRegex = Regex("""do\(\)""")
    val disableRegex = Regex("""don't\(\)""")
    fun solve(input: InputStream): Int {
        var mulEnabled = true
        return input.bufferedReader().readLines().sumOf {
            var total = 0
            var line = it
            while (line.isNotEmpty()) {
                if (mulEnabled) {
                    val last = disableRegex.find(line)?.range?.last
                    total += mulRegex.findAll(line.substring(0, last ?: line.length)).map { match ->
                        val (a, b) = match.destructured
                        a.toInt() * b.toInt()
                    }.sum()
                    if (last == null) break
                    line = line.substring(last)
                    mulEnabled = false
                } else {
                    val last = enableRegex.find(line)?.range?.last ?: break
                    line = line.substring(last)
                    mulEnabled = true
                }
            }
            total
        }
    }
}
