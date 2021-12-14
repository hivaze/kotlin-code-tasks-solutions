package utils

data class MutablePair<A, B>(var first: A, var second: B )

class Reference<T>(var variable: T)

fun IntArray.swap(firstIndex: Int, secondIndex: Int): IntArray {
    this[firstIndex] = this[secondIndex].also { this[secondIndex] = this[firstIndex] }
    return this
}

fun IntArray.shiftRightConditional(startPosition: Int, predicate: ((Int) -> Boolean)): IntArray {
    var (it, movingIndex, count) = listOf(startPosition, -1, 0)
    while (it <= lastIndex) {
        if (predicate(this[it])) {
            if (movingIndex == -1) {
                movingIndex = it
            }
            count++
        } else if (movingIndex != -1) {
            swap(it, movingIndex)
            if (count > 0) {
                movingIndex++
            } else {
                movingIndex = it
            }
        }
        it++
    }
    return this
}

fun IntArray.shiftRightRecursion(startPosition: Int, shiftsCount: Reference<Int> = Reference(0), predicate: ((Int) -> Boolean)? = null): IntArray {
    var it = startPosition
    while (it < lastIndex - shiftsCount.variable) {
        if (predicate != null) {
            if (predicate(this[it])) {
                if (predicate(this[it + 1])) {
                    shiftRightRecursion(it + 1, shiftsCount, predicate)
                    shiftsCount.variable += 1
                }
                swap(it, it + 1)
            }
        } else {
            swap(it, it + 1)
        }
        it++
    }
    return this
}

fun IntArray.binarySearch(value: Int, fromIndex: Int = 0, toIndex: Int = size-1): Int {
    var start = fromIndex
    var end = toIndex
    while (start <= end) {
        val middle_ix = (start + end) / 2
        val middle_elem = get(middle_ix)
        when {
            middle_elem > value -> end = middle_ix - 1
            middle_elem < value -> start = middle_ix + 1
            else -> return middle_ix
        }
    }
    return -(start)
}

fun IntArray.permutation(indexTransform: (Int) -> Int): IntArray {
    val mapping = indices.associateBy(indexTransform)
    val newArray = IntArray(size) { this[mapping[it]!!] }
    indices.forEach { this[it] = newArray[it]}
    return this
}

fun IntArray.reverse(fromIndex: Int, toIndex: Int): Unit {
    val midPoint = (fromIndex + toIndex) / 2
    if (fromIndex == midPoint) return
    var reverseIndex = toIndex - 1
    for (index in fromIndex until midPoint) {
        val tmp = this[index]
        this[index] = this[reverseIndex]
        this[reverseIndex] = tmp
        reverseIndex--
    }
}