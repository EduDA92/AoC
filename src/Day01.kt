fun main() {
    fun part1(input: List<String>): Int {


        var totalValue = 0

        input.forEach {

            val input = it.filter { it.isDigit() }

            totalValue += if (input.length == 1) {
                (input + input).toInt()
            } else {
                (input.first().toString() + input.last().toString()).toInt()
            }

        }
        return totalValue

    }

    fun part2(input: List<String>): Int {
        val replacementMap = mapOf(
            "one" to "o1e",
            "two" to "t2o",
            "three" to "t3e",
            "four" to "f4r",
            "five" to "f5e",
            "six" to "s6x",
            "seven" to "s7n",
            "eight" to "e8t",
            "nine" to "n9e"
        )

        var totalValue = 0

        input.forEach { word ->

            var correctedInput = ""

            word.forEach {

                correctedInput += it

                replacementMap.forEach {map ->
                    correctedInput = correctedInput.replace(oldValue = map.key, newValue = map.value)
                }

            }

            totalValue += part1(listOf(correctedInput))

        }
        return totalValue
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val testInputPart2 = readInput("Day01_part2_test")
    check(part2(testInputPart2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

