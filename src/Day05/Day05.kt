package Day05

import println
import readFullInput

fun main() {
    fun part1(input: String): Long {

        val almanac = mutableListOf<Pair<String, List<MapValue>>>()

        // Split by two \n\n because theres an empty space between lines
        val (seedsString, mapsString) = input.split("\n\n", limit = 2)
        val seedList = seedsString.substringAfter(": ").split(" ")
        val locationList = mutableListOf<Long>()

        // Again split the mapString every two newlines
        val maps = mapsString.split("\n\n")

        maps.forEach {

            val (mapName, mapValues) = it.split(":\n")

            val mapValueList = mapValues.splitToSequence("\n").map {

                val (destinationRangeStart, sourceRangeStart, rangeLength) = it.split(" ")

                MapValue(destinationRangeStart.toLong(), sourceRangeStart.toLong(), rangeLength.toLong())

            }.toList()

            almanac.add(Pair(mapName, mapValueList))

        }

        seedList.forEach { seed ->

            var currentLocation = seed.toLong()

            almanac.forEach almanac@{

                it.second.forEach {mapValue ->

                    if(mapValue.convertNumber(currentLocation) != null){
                        currentLocation = mapValue.convertNumber(currentLocation)!!
                        return@almanac
                    }

                }

            }

            locationList.add(currentLocation)
        }

        return locationList.min()
    }


    fun part2(input: String): Long {

        val almanac = mutableListOf<Pair<String, List<MapValue>>>()


        val (seedsString, mapsString) = input.split("\n\n", limit = 2)
        val seedList = seedsString.substringAfter(": ").split(" ")
        val ranges = seedList.chunked(2).map { (number, range ) ->  LongRange(number.toLong(), number.toLong() + range.toLong() - 1)}
        val maps = mapsString.split("\n\n")

        maps.forEach {

            val (mapName, mapValues) = it.split(":\n")

            val mapValueList = mapValues.splitToSequence("\n").map {

                val (destinationRangeStart, sourceRangeStart, rangeLength) = it.split(" ")

                MapValue(destinationRangeStart.toLong(), sourceRangeStart.toLong(), rangeLength.toLong())

            }.toList()

            almanac.add(Pair(mapName, mapValueList))

        }

        var currentMinLocation =  9223372036854775807

        ranges.forEach {seeds ->

            seeds.forEach { seed ->

                var currentLocation = seed

                almanac.forEach almanac@{

                    it.second.forEach {mapValue ->

                        if(mapValue.convertNumber(currentLocation) != null){
                            currentLocation = mapValue.convertNumber(currentLocation)!!
                            return@almanac
                        }

                    }

                }

                if(currentLocation < currentMinLocation){
                    currentMinLocation = currentLocation
                }

            }
        }


        return currentMinLocation

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readFullInput("Day05", "Day05_test")
    check(part1(testInput) == 35L)
    check(part2(testInput) == 46L)

    val input = readFullInput("Day05", "Day05")
    part1(input).println()
    part2(input).println()
}


data class MapValue(
    val destinationRangeStart: Long,
    val sourceRangeStart: Long,
    val rangeLength: Long
) {

    fun convertNumber(value: Long): Long? {

        return if (value in sourceRangeStart..(sourceRangeStart + (rangeLength - 1))) {
            destinationRangeStart + (value - sourceRangeStart)
        } else {
            null
        }

    }

}