package Day06

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {

        val time = input[0].substringAfter("Time:").trim().split(Regex("\\s+")).map { it.toInt() }
        val distance = input[1].substringAfter("Distance:").trim().split(Regex("\\s+")).map { it.toInt() }

        val timeDistance = time.zip(distance)

        val recordBeatsList = mutableListOf<Int>()

        timeDistance.forEach {range ->

            var recordBeatTimes = 0
            IntRange(0, range.first).forEach {buttonTime ->

                val distance = buttonTime * (range.first - buttonTime)

                if(distance > range.second){
                    recordBeatTimes += 1
                }

            }

            recordBeatsList.add(recordBeatTimes)

        }

        return recordBeatsList.reduce { acc, i ->  acc*i}
    }

    fun part2(input: List<String>): Int {

        val time = input[0].substringAfter("Time:").trim().split(Regex("\\s+")).joinToString(separator = "").toLong()
        val distance = input[1].substringAfter("Distance:").trim().split(Regex("\\s+")).joinToString(separator = "").toLong()


        val timeDistance = Pair(time, distance)



            var recordBeatTimes = 0
            LongRange(0, timeDistance.first).forEach {buttonTime ->

                val distance = buttonTime * (timeDistance.first - buttonTime)

                if(distance > timeDistance.second){
                    recordBeatTimes += 1
                }

            }

        return recordBeatTimes
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06", "Day06_test")
    check(part1(testInput) == 288)
    val testInput2 = readInput("Day06", "Day06_test_part2")
    check(part2(testInput2) == 71503)

    val input = readInput("Day06", "Day06")
    part1(input).println()
    part2(input).println()
}
