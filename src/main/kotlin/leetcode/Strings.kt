package leetcode

fun main(args: Array<String>) = findAnagrams()

// https://leetcode.com/problems/find-all-anagrams-in-a-string/
private fun findAnagrams() {
    val s = readLine()!!.trim()
    val p = readLine()!!.trim()

    val pLetters = p.groupingBy { it }.eachCount()
    val movingHashMap = mutableMapOf<Char, Int>()

    val answers = mutableListOf<Int>()

    var start = -1
    s.forEachIndexed { index, ch ->
        if (pLetters.containsKey(ch)) {
            if (start == -1) {
                start = index
            }
            movingHashMap.putIfAbsent(ch, 0)
            movingHashMap.computeIfPresent(ch) { _, c -> c + 1}
            if (index - start == p.lastIndex) {
                if (movingHashMap == pLetters) {
                    answers.add(start)
                }
                movingHashMap.computeIfPresent(s[start++]) { _, c -> (c - 1).coerceAtLeast(0) }
            }
        } else if (start != -1) {
            start = -1
            movingHashMap.clear()
        }
//        println(movingHashMap)
    }
    println(answers)

}