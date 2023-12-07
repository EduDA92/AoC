package Day07

import println
import readInput

fun main() {

    fun part1(input: List<String>): Int {

        val cardList = mutableListOf<HandInfo>()

        input.forEach { line ->
            val (cards, bid) = line.split(" ")
            cardList.add(HandInfo(cards, bid.toInt(), false))
        }

        var result = 0

        val sortedList =
            cardList.sortedWith(compareBy({ it.rating }, { it.transformedCard[0] }, { it.transformedCard[1] },
                { it.transformedCard[2] }, { it.transformedCard[3] }, { it.transformedCard[4] })
            )


        sortedList.forEachIndexed { index, handInfo ->
            result += handInfo.cardBid * (index + 1)
        }

        return result

    }

    fun part2(input: List<String>): Int {

        val cardList = mutableListOf<HandInfo>()

        input.forEach { line ->
            val (cards, bid) = line.split(" ")
            cardList.add(HandInfo(cards, bid.toInt(), true))
        }

        var result = 0

        val sortedList =
            cardList.sortedWith(compareBy({ it.rating }, { it.transformedCard[0] }, { it.transformedCard[1] },
                { it.transformedCard[2] }, { it.transformedCard[3] }, { it.transformedCard[4] })
            )


        sortedList.forEachIndexed { index, handInfo ->
            result += handInfo.cardBid * (index + 1)
        }

        return result


        return input.size
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07", "Day07_test")
    check(part1(testInput) == 6440)
    check(part2(testInput) == 5905)

    val input = readInput("Day07", "Day07")
    part1(input).println()
    part2(input).println()

}

data class HandInfo(
    val cards: String,
    val cardBid: Int,
    val joker: Boolean,
) {

    private val values = mapOf(
        'A' to 14, 'K' to 13, 'Q' to 12, 'J' to 11, 'T' to 10, '9' to 9, '8' to 8, '7' to 7,
        '6' to 6, '5' to 5, '4' to 4, '3' to 3, '2' to 2
    )

    private val jokerValues = mapOf(
        'A' to 14, 'K' to 13, 'Q' to 12, 'J' to 0, 'T' to 10, '9' to 9, '8' to 8, '7' to 7,
        '6' to 6, '5' to 5, '4' to 4, '3' to 3, '2' to 2
    )

    val transformedCard = transformCard(cards, joker)
    val rating = if (joker) jokerCardRating(cards) else cardRating(cards)

    private fun jokerCardRating(cards: String): Int {
        val valueList = cards.groupingBy { it }.eachCount()
        val maxValue = valueList.maxOf { it.value }

        // Five of a kind
        if (valueList.size == 1) {
            return 7
        }

        /* If joker it means we have 4 of a kind and a joker so we have five of a kind = value 7
       * else we have only four of a kind = value 6 */
        if (valueList.size == 2 && maxValue == 4) {
            return if (valueList.containsKey('J')) 7 else 6
        }

        /* If joker then it means we have 3 of a king and a joker so five of a kind = value 7
        * if not just full house value = 5 */
        if (valueList.size == 2 && maxValue == 3) {
            return if (valueList.containsKey('J')) 7 else 5
        }

        /* If joker the it means we have 3 of a king plus joker so four of a kind = value 6
        * if not just three of a kind  = value 4 */
        if (valueList.size == 3 && maxValue == 3) {
            return if (valueList.containsKey('J')) 6 else 4
        }

        /* If there is a joker there is two possibilities:
        * 2 jokers, so we have 2 cards same value and 2 jokers so four of a kind = value 6
        * 1 joker, so we have 2 pairs of cards same value and joker so Full house = value 5
        * else just two pair = value 3  */
        if (valueList.size == 3 && maxValue == 2) {
            return if (valueList['J'] == 2) 6 else if (valueList['J'] == 1) 5 else 3
        }

        /* Here if joker we can have max a three of a kind = value 4
        * else just one pair = value 2 */
        if (valueList.size == 4) {
            return if (valueList.containsKey('J')) 4 else 2
        }

        /* If joker then one pair = value 2 else 1*/
        return if (valueList.containsKey('J')) 2 else 1

    }

    private fun cardRating(cards: String): Int {
        val valueList = cards.groupingBy { it }.eachCount()
        val maxValue = valueList.maxOf { it.value }

        // Five of a kind
        if (valueList.size == 1) {
            return 7
        }

        // Four of a kind
        if (valueList.size == 2 && maxValue == 4) {
            return 6
        }

        // Full House
        if (valueList.size == 2 && maxValue == 3) {
            return 5
        }

        // three of a kind
        if (valueList.size == 3 && maxValue == 3) {
            return 4
        }

        // Two Pair
        if (valueList.size == 3 && maxValue == 2) {
            return 3
        }


        // One pair
        if (valueList.size == 4) {
            return 2
        }

        // High card
        return 1
    }

    private fun transformCard(card: String, joker: Boolean): List<Int> {


        val cardValueList = mutableListOf<Int>()

        if (joker) {
            card.forEach {
                cardValueList.add(jokerValues[it] ?: 0)
            }

        } else {
            card.forEach {
                cardValueList.add(values[it] ?: 0)
            }

        }

        return cardValueList
    }


}

