package Day02

import println
import readInput

fun main() {
    fun part1(input: List<String>): Int {

        var totalId = 0

        input.forEach {

            val (id, info) = it.split(":")
            val (_, gameId) = id.split(" ")

            val infoMaps = info.split(";").map {
                it.trim().split(", ").associate {
                    val(nCubes, color) = it.split(" ")
                    color to nCubes.toInt()
                }
            }

            val valid = infoMaps.all {
                it.all {map ->
                    when(map.key){
                        "red" -> map.value <= 12
                        "blue" -> map.value <= 14
                        "green" -> map.value <= 13
                        else -> false
                    }
                }
            }


            if(valid) {
                totalId += gameId.toInt()
            }

        }

        return totalId
    }

    fun part2(input: List<String>): Int {

        var power = 0

        input.forEach {

            val (id, info) = it.split(":")
            val (_, gameId) = id.split(" ")

            val infoMaps = info.split(";").map {
                it.trim().split(", ").associate {
                    val(nCubes, color) = it.split(" ")
                    color to nCubes.toInt()
                }
            }

            val fewestNumberMap = mutableMapOf("blue" to 0, "red" to 0, "green" to 0)

            infoMaps.forEach {
               it.forEach {color, number ->
                   fewestNumberMap[color] = maxOf(number, fewestNumberMap[color] ?: 0)
               }
            }

           power += fewestNumberMap.values.reduce { acc, i -> acc*i }



        }

        return power
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02", "Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02","Day02")

    part1(input).println()
    part2(input).println()
}