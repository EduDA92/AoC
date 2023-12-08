package Day08

import Utils.lcm
import println
import readInput

fun main() {

    fun part1(input: List<String>): Int {

        val map = mutableMapOf<String, Pair<String, String>>()
        val instructions = input[0]

        for (i in 2 until input.size) {
            val (origin, left, right) = Regex("[A-Z]+").findAll(input[i]).map { it.value }.toList()
            map[origin] = Pair(left, right)
        }

        var iterations = 0
        var current = "AAA"

        while (current != "ZZZ") {
            instructions.forEach {
                current = if (it == 'L') {
                    map[current]?.first ?: "err"
                } else {
                    map[current]?.second ?: "err"
                }
                iterations += 1
            }
        }

        return iterations
    }

    fun part2(input: List<String>):Long {

        val map = mutableMapOf<String, Pair<String, String>>()
        val instructions = input[0]

        for (i in 2 until input.size) {
            val (origin, left, right) = Regex("[A-Z]+").findAll(input[i]).map { it.value }.toList()
            map[origin] = Pair(left, right)
        }

        val iterationList = mutableListOf<Long>()


        map.filter { it.key.contains('A') }.forEach {
            var current = it.key
            var iterations = 0L

            while (!current.endsWith('Z')) {
                instructions.forEach {
                    current = if (it == 'L') {
                        map[current]?.first ?: "err"
                    } else {
                        map[current]?.second ?: "err"
                    }
                    iterations += 1
                }
            }

            iterationList.add(iterations)
        }
        
        return iterationList.reduce { acc, i ->  lcm(acc, i) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08", "Day08_test")
    check(part1(testInput) == 6)

    val input = readInput("Day08", "Day08")
    part1(input).println()
    part2(input).println()
}

