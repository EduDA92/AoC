package Day04

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {

        var points = 0

        input.forEach {card ->

            val (card, numbers) = card.split(":")
            val (winningNumbers, playerNumbers) = numbers.trim().split("|")
            val winningNumbersList = winningNumbers.trim().split(Regex("\\s+"))
            val playerNumbersList = playerNumbers.trim().split(Regex("\\s+"))

            val validNumbers = playerNumbersList.filter { winningNumbersList.contains(it) }

              points += Math.pow(2.0, (validNumbers.size - 1).toDouble()).toInt()

        }

        return points
    }

    fun part2(input: List<String>): Int {

        var list = IntArray(input.size) { 1 }

        var totalScratchCards = 0

        input.forEachIndexed { index, card ->

            val (_, numbers) = card.split(":")
            val (winningNumbers, playerNumbers) = numbers.trim().split("|")
            val winningNumbersList = winningNumbers.trim().split(Regex("\\s+"))
            val playerNumbersList = playerNumbers.trim().split(Regex("\\s+"))
            val validNumbers = playerNumbersList.filter { winningNumbersList.contains(it) }


            for(i in list[index]downTo 1){

                for(j in (validNumbers.size + index) downTo index+1){
                    list[j] += 1
                }

            }

            totalScratchCards = list.sumOf { it }

        }

        return totalScratchCards
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04", "Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("Day04", "Day04")
    part1(input).println()
    part2(input).println()
}