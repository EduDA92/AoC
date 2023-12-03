package Day03

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {

        val partsRegex = Regex("\\d+")
        val symbolsRegex = Regex("[!@#$%^&*()+\\-=_/<>,`~|]")


        val symbolCoordinates = mutableListOf<Pair<Int,Int>>()
        val partList = mutableListOf<Part>()

        // Get all symbols and parts
        input.forEachIndexed { index, s ->
            val symbolMatches = symbolsRegex.findAll(s)
            val partMatches = partsRegex.findAll(s)

            symbolCoordinates.addAll(
                symbolMatches.map {
                    Pair(it.range.first, index)
                }
            )

            partMatches.forEach { part ->

                // Create the border of the part
                val border = mutableListOf<Pair<Int, Int>>()
                // Add right and left coordinates of the number
                border.add(Pair(part.range.first - 1, index))
                border.add(Pair(part.range.last + 1, index))

                IntRange(part.range.first - 1, part.range.last + 1).forEach {

                    border.add(Pair(it, index - 1))
                    border.add(Pair(it, index + 1))

                }

                partList.add(Part(
                    value = part.value.toInt(),
                    border = border
                ))

            }

        }

        val validParts = partList.filter { it.border.any { symbolCoordinates.contains(it) } }

        return validParts.sumOf { it.value }
    }

    fun part2(input: List<String>): Int {

        val partsRegex = Regex("\\d+")
        val symbolsRegex = Regex("\\*")


        val symbolCoordinates = mutableListOf<Pair<Int,Int>>()
        val partList = mutableListOf<Part>()

        var totalGearRatio = 0

        // Get all symbols and parts
        input.forEachIndexed { index, s ->
            val symbolMatches = symbolsRegex.findAll(s)
            val partMatches = partsRegex.findAll(s)

            symbolCoordinates.addAll(
                symbolMatches.map {
                    Pair(it.range.first, index)
                }
            )

            partMatches.forEach {part ->

                // Create the border of the part
                val border = mutableListOf<Pair<Int, Int>>()
                // Add right and left coordinates of the number
                border.add(Pair(part.range.first - 1, index))
                border.add(Pair(part.range.last + 1, index))

                IntRange(part.range.first - 1, part.range.last + 1).forEach {

                    border.add(Pair(it, index - 1))
                    border.add(Pair(it, index + 1))

                }

                partList.add(Part(
                    value = part.value.toInt(),
                    border = border
                ))

            }
        }

        symbolCoordinates.forEach { symbolCoordinate ->
            val validParts = partList.filter { it.border.any{ it == symbolCoordinate } }

            if(validParts.size == 2){
                totalGearRatio += validParts[0].value * validParts[1].value
            }
        }

        return totalGearRatio
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03", "Day03_test")
    check(part1(testInput) == 4361)
    check(part2(testInput) == 467835)
    val input = readInput("Day03", "Day03")
    part1(input).println()
    part2(input).println()
}

data class Part(
    val value: Int,
    val border: List<Pair<Int, Int>>
)