package hackerrank

import kotlin.experimental.inv
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = println("Elapsed time: " + measureTimeMillis(::flippingBits))

private fun flippingBits() {
    val linesCount = readLine()!!.trim().toInt()
    val numbers = (1..linesCount).map { readLine()!!.trim().toUInt() }.toTypedArray()
    numbers.map { it.inv().toString(radix = 10) }.forEach(::println)
}

private fun gradingStudents() {
    val gradesCount = readLine()!!.trim().toInt()
    val grades = (1..gradesCount).map { readLine()!!.trim().toInt() }.toTypedArray()
    val additions = grades.map { if (it >= 38) (5 - (it % 5)) else 0 }.map { if (it < 3) it else 0 }.toTypedArray()
    grades.zip(additions) { a: Int, b: Int -> a + b }.forEach(::println)
}

private fun lonelyInteger() {
    val num_array = readLine()!!.trim().split(" ").map { it.toInt() }.toTypedArray()
    println(num_array.groupingBy { it }.eachCount().filterValues { it == 1 }.keys.first())
}