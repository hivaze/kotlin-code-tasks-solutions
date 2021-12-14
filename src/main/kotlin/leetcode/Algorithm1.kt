package leetcode

import utils.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) = floodFill()

private fun floodFill() {
    val image: Array<IntArray> = arrayOf(
        intArrayOf(1, 1, 1),
        intArrayOf(1, 1, 0),
        intArrayOf(1, 0, 1)
    )
    val (sr, sc, newColor) = listOf(1, 1, 2)

    val pixelColor = image[sr][sc]

    fun helper(image: Array<IntArray>, sr: Int, sc: Int, newColor: Int, oldColor: Int) {
        if (sr < 0 || sr >= image.size || sc < 0 || sc >= image[0].size || image[sr][sc] != oldColor || image[sr][sc] == newColor) return
        image[sr][sc] = newColor
        helper(image, sr + 1, sc, newColor, oldColor)
        helper(image, sr - 1, sc, newColor, oldColor)
        helper(image, sr, sc + 1, newColor, oldColor)
        helper(image, sr, sc - 1, newColor, oldColor)
    }

    helper(image, sr, sc, newColor, pixelColor)

    image.print()
}

fun Array<IntArray>.print(digitsInNumber: Int = 2) {
    (0..lastIndex).forEach { i ->
        (0..this[i].lastIndex).forEach { j->
            print("%${digitsInNumber}d ".format(this[i][j]))
        }
        println()
    }
}

// -------------------- DAY 6 -----------------------------------

// Оптимальная реализация с помощью массивов
private fun checkInclusion2() {
    val s1 = readLine()!!.trim()
    val s2 = readLine()!!.trim()

    if (s2.isEmpty() || s1.length > s2.length) {
        println(false)
        return
    }

    val window1 = IntArray(26){0}
    val window2 = IntArray(26){0}

    for (i in 0 .. s1.lastIndex) {
        window1[s1[i] - 'a']++
        window2[s2[i] - 'a']++
    }

    if (window1 contentEquals window2) {
        println(true)
        return
    }
    // move window and update most left count
    var start = 0
    for (i in s1.length .. s2.lastIndex) {
        val count = window2[s2[start] - 'a'] - 1
        if (count >= 0) {
            window2[s2[start] - 'a'] = count
        }
        window2[s2[i]- 'a']++
        if (window1 contentEquals window2) {
            println(true)
            return
        }
        start++
    }
    println(false)
}

// реализация с помощью HashMap и windowed()
private fun checkInclusion() {
    val s1 = readLine()!!.trim()
    val s2 = readLine()!!.trim()

    if (s2.isEmpty() || s1.length > s2.length) {
        println(false)
        return
    }

    val s1Letters = s1.toCharArray().toList().groupingBy { it }.eachCount()
//    val windowedMap = mutableMapOf<Char, Int>()

    val permutationCheck = s2.windowed(s1.length) {
        val letterCounts = it.toList().groupingBy { ch: Char -> ch }.eachCount() // работает слишком долго
        return@windowed letterCounts == s1Letters
    }

//    println(permutationCheck)
    println(permutationCheck.find { it } != null)
}

inline fun <R> CharSequence.runningFold(initial: R, operation: (acc: R, Char) -> R): List<R> {
    if (isEmpty()) return listOf(initial)
    val result = ArrayList<R>(length + 1).apply { add(initial) }
    var accumulator = initial
    for (element in this) {
        accumulator = operation(accumulator, element)
        result.add(accumulator)
    }
    return result
}

private fun lengthOfLongestSubstring() {
    val text = readLine()!!.trim()

    if (text.isEmpty()) {
        println(0)
        return
    }

    val context = object: Any() {
        var maxLength = -1
        var currentChars = mutableSetOf<Char>()
    }

    text.forEachIndexed { index, c ->
        if (!context.currentChars.add(c)) {
            context.currentChars.clear()
            context.currentChars.add(c)
            var prevCharInd = index
            while (context.currentChars.add(text[--prevCharInd]));
        }
        if (context.maxLength < context.currentChars.size) {
            context.maxLength = context.currentChars.size
        }
    }

    println(context.maxLength)

//    println(maxLength)

//    val context = object: Any() {
//        var maxLength = -1
//        var currentChars = mutableSetOf<Char>()
//    }
//
//    text.forEach {
//        if (!context.currentChars.add(it)) {
//
//            context.currentChars.remove(it)
//
//            context.currentChars.remove(it)
//        }
//        else if (context.maxLength < context.currentChars.size) {
//            context.maxLength = context.currentChars.size
//        }
//    }
//
//    if (context.maxLength == -1) {
//        println(text.length)
//        return
//    }
//
//    println(context.maxLength)
}

// -------------------- DAY 5 -----------------------------------

private fun removeNthFromEnd() {
    var tail = LinkedNode(1)
    (2 until 7).forEach { tail = LinkedNode(it, next = tail) }
    tail.removeFromHead(tail.totalLength() - 1)
    println(tail)
}

private fun reverseWordsSentence() {
    val text = readLine()!!
    val splitReversed = text.split(" ").map { it.reversed() }
    println(splitReversed.joinToString(separator = " ") { it })
}

private fun reverseString() {
    val text = readLine()!!.toCharArray()
    val mid = text.size / 2  - 1
    (0..mid).forEach { index: Int ->
        text[index] = text[text.lastIndex - index].also { text[text.lastIndex - index] = text[index] }
    }
    println(text)
}

private fun twoSum2() {
    val target = readLine()!!.toInt()
    val numbers = readLine()!!.trim().split(" ").map{ it.toInt() }.toIntArray().sortedArray()

    var (left, right) = listOf(0, numbers.size - 1)
    while (left < right) {
        if (numbers[left] + numbers[right] == target) {
            println(listOf(left + 1, right + 1).toIntArray().toList())
            return
        }
        if (numbers[left] + numbers[right] > target) {
            right--
        } else {
            left++
        }
    }
}

private fun moveZeroes() {
    val numbers = readLine()!!.trim().split(" ").map{ it.toInt() }.toIntArray()
//    val numbers = (1..100000).map { (-1..1).random() }.toIntArray()
    println("Time taken ${measureTimeMillis { numbers.shiftRightConditional(0) {it == 0} } }")
    println(numbers.toList())
}

private fun rotateArray2() {
    var k = readLine()!!.toInt()
    val numbers = readLine()!!.trim().split(" ").map{ it.toInt() }.toIntArray()
    k %= numbers.size
    if (k < 0) k += numbers.size
    numbers.reverse(0, numbers.size - k - 1)
    println(numbers.toList())
    numbers.reverse(numbers.size - k, numbers.size - 1)
    println(numbers.toList())
    numbers.reverse(0, numbers.size - 1)
    println(numbers.toList())
}

private fun rotateArray() {
    val k = readLine()!!.toInt()
    val numbers = readLine()!!.trim().split(" ").map{ it.toInt() }.toIntArray()
    numbers.reverse()
    println(numbers.permutation { (it + k) % (numbers.size) }.toList())
}

private fun sortedSquares() {
    val numbers = readLine()!!.trim().split(" ").map{ it.toInt() }.toIntArray()
    val positiveMapped = numbers.filter { it >= 0 }.map { it * it }.toMutableList()
    val negativeMapped = numbers.filter { it < 0 }.map { it * it }
    negativeMapped.forEach {
        val index = positiveMapped.binarySearch(it)
        val indexToInsert = if (index >= 0) index else max(abs(index) - 1, 0)
        positiveMapped.add(indexToInsert, it)
    }
    println(positiveMapped)
}

// -------------------- DAY 1 -----------------------------------

private fun searchInsert() {
    val value = readLine()!!.trim().toInt()
    val numbers = readLine()!!.trim().split(" ").map{ it.toInt() }.toIntArray()
    println(abs(numbers.binarySearch(value)))
}

private object BadVersionEmulator {

    private var unknownBadVersion: Int = 0
    private var currentVersion: Int = 0

    init {
        for (i: Int in 0..100) {
            generate()
            print("Test $i: ${toString()} ")
            println(measureTimeMillis { print( if (firstBadVersion(currentVersion) == unknownBadVersion) "passed in " else "failed in ")})
        }
    }

    fun firstBadVersion(currentVersion: Int): Int {
        if (isBadVersion(1)) return 1
        var start = 2
        var end = currentVersion
        while (start <= end) {
            val middle_ix = ((start.toLong() + end.toLong()) / 2).toInt()
            val middle_elem = isBadVersion(middle_ix)
            when {
                middle_elem -> end = middle_ix - 1
                else -> start = middle_ix + 1
            }
        }
        return start
    }

    fun isBadVersion(number: Int) = number >= unknownBadVersion

    private fun generate() {
        currentVersion = Random.nextInt(1, Int.MAX_VALUE)
        unknownBadVersion = Random.nextInt(1, currentVersion)
    }

    override fun toString(): String {
        return "BadVersionEmulator(unknownBadVersion=$unknownBadVersion, currentVersion=$currentVersion)"
    }

}

private fun binarySearch() {
    val target = readLine()!!.toInt()
    val numbers = readLine()!!.trim().split(" ").map{ it.toInt() }.toIntArray()
    val result = numbers.binarySearch(target)
    when {
        result < 0 -> println(-1)
        else -> println(result)
    }
}