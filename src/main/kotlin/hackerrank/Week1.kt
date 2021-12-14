package hackerrank

import utils.MutablePair
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = println("Elapsed time: " + measureTimeMillis(::matchingStrings))

private fun matchingStrings() {
    val inputStrings = (0 until readLine()!!.trim().toInt()).map { readLine()!! }
    val queryStrings = (0 until readLine()!!.trim().toInt()).map { readLine()!! }
    queryStrings.map { q: String ->  inputStrings.count { s: String -> q == s } }.forEach(::println)
}

private fun divisibleSumPairs() {
    val first_multiple_input = readLine()!!.trim().split(" ").map { it.toInt() }
    val nk = Pair(first_multiple_input[0], first_multiple_input[1])
    val nums = readLine()!!.trim().split(" ").map{ it.toInt() }.toTypedArray()
    val element_pairs = nums.flatMapIndexed { i: Int, a: Int -> nums.mapIndexed { j, b -> if (i < j) Pair(a, b) else null } }.filterNotNull()
    val rule_fildtered = element_pairs.filter { (it.first + it.second) % nk.second == 0 }
    println(rule_fildtered.size)
}

private fun camelCase4() {
    while (true) {
        val line = readLine() ?: break
        val splittedLine = line.split(";")
        when (splittedLine.component1()) {
            "S" -> {
                val fixedLine= splittedLine.component3().replace("[^\\w\\d]".toRegex(), "")
                val splittedCamel = fixedLine.split("(?<=[a-z])(?=[A-Z])".toRegex())
                println(splittedCamel.joinToString(separator = " ") { it.lowercase() })
            }
            "C" -> {
                val splittedName = splittedLine.component3().split(" ")
                val combinedUpper = splittedName.joinToString(separator = "") { it.replaceFirstChar(Char::uppercase) }
                when (splittedLine.component2()) {
                    "C" -> println(combinedUpper)
                    "V" -> println(combinedUpper.replaceFirstChar(Char::lowercase))
                    "M" -> println(combinedUpper.replaceFirstChar(Char::lowercase).plus("()"))
                }
            }
        }
    }
}

private fun breakingRecords() {
    val scores = readLine()!!.trim().split(" ").map{ it.toInt() }.toTypedArray()
    val breakingCounts = arrayOf(MutablePair(scores.first(), 0), MutablePair(scores.first(), 0))
    scores.forEach { score ->
        when {
            (score > breakingCounts[0].first) -> {
                breakingCounts[0].first = score
                breakingCounts[0].second += 1
            }
            (score < breakingCounts[1].first) -> {
                breakingCounts[1].first = score
                breakingCounts[1].second += 1
            }
        }
    }
    println("${breakingCounts.first().second} ${breakingCounts.last().second}")
}

private fun timeConversation() {
    val input_time = readLine()!!.trim()
    val numbers = input_time.split(":").map { it.take(2).toInt() }.toTypedArray()
    val day_split = input_time.takeLast(2)
    when (day_split) {
        "AM" -> {
            if (numbers.first() == 12) numbers[0] = 0
        }
        "PM" -> {
            if (numbers.first() < 12) numbers[0] += 12
        }
    }
    println(numbers.joinToString(separator = ":") { "0".repeat(2 - it.toString().length) + it.toString() })
}

private fun minimaxSum() {
    val num_array = readLine()!!.trim().split(" ").map { it.toLong() }.toTypedArray()
    val sorted_array = num_array.sortedArray()
    println("${sorted_array.take(4).sum()} ${sorted_array.takeLast(4).sum()}")
}

private fun plusMinus() {
    val num_array = readLine()!!.trim().split(" ").map { it.toInt() }.toTypedArray()
    val density_by_group = num_array.groupingBy {
        when {
            it > 0 -> 1
            it < 0 -> (-1)
            else -> 0
        }
    }.eachCount().mapValues { (k, v) -> v.toFloat() / num_array.size}
    println(density_by_group.getOrDefault(1, 0))
    println(density_by_group.getOrDefault(-1, 0))
    println(density_by_group.getOrDefault(0, 0))
}

private fun findMostFrequent() {
    val num_array = readLine()!!.trim().split(" ").map { it.toInt() }.toTypedArray()
    println(num_array.groupingBy { it }.eachCount().toList().maxByOrNull { (key, value) -> value }!!.first)
}
