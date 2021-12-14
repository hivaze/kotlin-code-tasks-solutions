package utils

data class LinkedNode<T>(var value: T, var next: LinkedNode<T>? = null) {

    /**
     *   @param k должен быть в диапазоне от 1 до totalLength() - 1
     */
    fun removeFromHead(k: Int) {
        assert(k > 0) { "Param k must be more than 0" }
        var (parent, node) = Pair(this, this)
        for (i in (0 until k)) {
            parent = node
            node = node.next!!
        }
        parent.next = node.next
    }

    /**
     *   @param k должен быть в диапазоне от 0 до totalLength() - 1
     */
    fun getValueOn(k: Int): T {
        var node = this
        for (i in (0 until k)) {
            node = node.next!!
        }
        return node.value
    }

    fun totalLength(): Int {
        var length = 0
        var node: LinkedNode<T>? = this
        while (node != null) {
            node = node.next
            length++
        }
        return length
    }

}