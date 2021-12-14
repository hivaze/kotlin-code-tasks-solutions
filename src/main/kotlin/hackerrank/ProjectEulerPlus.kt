package hackerrank

import java.util.*
import kotlin.system.measureTimeMillis


fun main(args: Array<String>) = println("Elapsed time: " + measureTimeMillis(::primeSubsetSums))

/*
    Задача понять сколько сущесвует пар чисел в массиве сумма которых содержится в этом массиве
    Повторяющихся элементов нет
 */
private fun primeSubsetSums() {
    val numbers = readLine()!!.trim().split(" ").map { it.toInt() }
    println("Elapsed time: " + measureTimeMillis {
        var sorted: List<Int> = numbers.sorted()
        val (minElement, maxElement) = Pair(numbers.first(), numbers.last())
        sorted = sorted.filter { it + minElement <= maxElement }
        sorted.forEach {

        }
    })
}

fun sieveOfEratosthenes(n: Int): List<Int>? {
    val prime = BooleanArray(n + 1)
    Arrays.fill(prime, true)
    var p = 2
    while (p * p <= n) {
        if (prime[p]) {
            var i = p * 2
            while (i <= n) {
                prime[i] = false
                i += p
            }
        }
        p++
    }
    val primeNumbers: MutableList<Int> = LinkedList()
    for (i in 2..n) {
        if (prime[i]) {
            primeNumbers.add(i)
        }
    }
    return primeNumbers
}