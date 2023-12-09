package Day09

import println
import readInput

fun main() {
    fun part1(input: List<String>): Long {

        val listOfReports = mutableListOf<List<Long>>()

        input.forEach {
            listOfReports.add(it.split(" ").map { it.toLong() })
        }


        return listOfReports.sumOf { extrapolateNumber(it, false) }

    }

    fun part2(input: List<String>): Long {

        val listOfReports = mutableListOf<List<Long>>()

        input.forEach {
            listOfReports.add(it.split(" ").map { it.toLong() })
        }

        return listOfReports.sumOf { extrapolateNumber(it, true) }
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09", "Day09_test")
    check(part1(testInput) == 114L)
    check(part2(testInput) == 2L)

    val input = readInput("Day09", "Day09")
    part1(input).println()
    part2(input).println()
}


fun extrapolateNumber(reportValues: List<Long>, reversed: Boolean): Long {

    if (reportValues.all { it == 0L }) {
        return 0
    } else {
        val newList = mutableListOf<Long>()

        for (i in 0 until (reportValues.size - 1)) {
            newList.add(reportValues[i + 1] - reportValues[i])
        }

        return if (!reversed) reportValues.last() + extrapolateNumber(newList, reversed)
        else reportValues.first() - extrapolateNumber(newList, reversed)
    }

}
