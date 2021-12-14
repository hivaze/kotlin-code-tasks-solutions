package geekforgeeks

import kotlin.math.abs
import kotlin.math.max

fun main(args: Array<String>) = closestPair()

/*
    Дано 2 сортированных массива и число x
    Нужно найти лучшую пару чисел, содержащюю числа из обоих массивов, такую чтобы их сумма была наиболее близка к x
    https://www.geeksforgeeks.org/given-two-sorted-arrays-number-x-find-pair-whose-sum-closest-x/?ref=lbp
 */
private fun closestPair() {
    val x = readLine()!!.toInt()
    val firstArray = readLine()!!.trim().split(" ").map{ it.toInt() }.toIntArray().sortedArray()
    val secondArray = readLine()!!.trim().split(" ").map{ it.toInt() }.toIntArray().sortedArray()

    var (i, j) = listOf(0, secondArray.size - 1)
    var (res_i, res_j) = listOf(0, 0)
    var difference = Int.MAX_VALUE

    while ((i < firstArray.size) and (j >= 0)) {
        val diff = abs((firstArray[i] + secondArray[j]) - x)

        if (diff < difference) {
            res_j = j
            res_i = i
            difference = diff
        }

        if (firstArray[i] + secondArray[j] > x)
            j--;
        else
            i++;
    }
    println(Pair(res_i, res_j))
    println(difference)

}

/*
    Нужно найти максимальную сумму элементов отсортированных массивов
    Суммы начинаются с первого элемента одного из двух массивов, переключаться между массивами можно только по общим элементам
    Необхжимо решить за O(N+M)
    https://www.geeksforgeeks.org/maximum-sum-path-across-two-arrays/?ref=lbp
 */
private fun maxSumPath() {
    val firstArray = readLine()!!.trim().split(" ").map{ it.toInt() }.toIntArray().sortedArray()
    val secondArray = readLine()!!.trim().split(" ").map{ it.toInt() }.toIntArray().sortedArray()

    var (i, j) = listOf(0, 0)
    var (result, sum1, sum2) = listOf(0, 0, 0)

    while ((i < firstArray.size) and (j < secondArray.size)) {
        if (firstArray[i] < secondArray[j]) {
            sum1 += firstArray[i++]
        };
        else if (firstArray[i] > secondArray[j]) {
            sum2 += secondArray[j++]
        }
        else {
            result += max(sum1, sum2) + firstArray[i]

            sum1 = 0; sum2 = 0

            i++; j++
        }
    }

    while (i < firstArray.size)
        sum1 += firstArray[i++];

    while (j < secondArray.size)
        sum2 += secondArray[j++];

    result += max(sum1, sum2);

    println(result)
}