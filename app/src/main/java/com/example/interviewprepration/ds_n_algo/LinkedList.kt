package com.example.interviewprepration.ds_n_algo

import android.util.Log

object LinkedList {

    val LOG_TAG: String = LinkedList::class.java.simpleName

    data class LLNode(var data: Int, var next: LLNode? = null)

    fun driverFunction() {

        val oddTotalNodes = oddCountNode()
        val evenTotalNodes = evenCountNode()

        val middleNodeOdd = findMiddle(oddTotalNodes)
        val middleNodeEven = findMiddle(evenTotalNodes)

        Log.e(LOG_TAG, "Inside driverFunction(), middleNodeOdd: ${middleNodeOdd?.data}")
        Log.e(LOG_TAG, "Inside driverFunction(), middleNodeEven: ${middleNodeEven?.data}")


        val oddTotalNodes1 = oddCountNode()
        val evenTotalNodes1 = evenCountNode()
        val newOddTotalNodes = moveEvenNodes(oddTotalNodes1)
        val newEvenTotalNodes = moveEvenNodes(evenTotalNodes1)

    }

    fun oddCountNode(): LLNode? {
        val node1 = LLNode(data = 10)
        val node2 = LLNode(data = 4)
        val node3 = LLNode(data = 88)
        val node4 = LLNode(data = 7)
        val node5 = LLNode(data = 11)

        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5

        return node1
    }

    fun evenCountNode(): LLNode? {
        val node1 = LLNode(data = 10)
        val node2 = LLNode(data = 4)
        val node3 = LLNode(data = 99)
        val node4 = LLNode(data = 7)
        val node5 = LLNode(data = 11)
        val node6 = LLNode(data = 22)

        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node6

        return node1
    }


    fun findMiddle(root: LLNode?): LLNode? {
        if (root == null) {
            return null
        }

        var fastPtr = root
        var slowPtr = root

        // this will return (n/2)+1 node for even count of nodes
        // This will return (n+1)/2 node for odd count of nodes
        /*while (fastPtr != null && fastPtr.next != null) {
            fastPtr = fastPtr.next?.next
            slowPtr = slowPtr?.next
        }*/

        // this will return (n/2) node for even count of nodes
        // This will return (n+1)/2 node for odd count of nodes
        while (fastPtr != null && fastPtr.next != null && fastPtr.next?.next != null) {
            fastPtr = fastPtr.next?.next
            slowPtr = slowPtr?.next
        }
        return slowPtr
    }

    //Move even nodes to the end of the linked list in reverse order
    // Input:  1 —> 2 —> 3 —> 4 —> 5 —> 6 —> 7 —> null
    // Output: 1 —> 3 —> 5 —> 7 —> 6 —> 4 —> 2 —> null
    fun moveEvenNodes(head: LLNode?): LLNode? {
        if (head == null)
            return null

        var temp = head
        var evenHead: LLNode? = null
        var prev = head
        var newHead: LLNode? = null

        while (temp?.next != null) {
            Log.e(LOG_TAG, "Inside moveEvenNodes(), original LL, current node: ${temp.data}")
            if ((temp.data) % 2 == 0) {
                var evenNode = temp
                temp = temp.next
                evenNode.next = evenHead
                evenHead = evenNode
            } else {
                if (newHead == null) {
                    newHead = temp
                }
                prev = temp
                temp = temp.next
            }
        }

        temp?.next = evenHead
        if (newHead == null) {
            newHead = temp
        }

        var temp1 = newHead
        while (temp1 != null) {
            Log.e(LOG_TAG, "Inside moveEvenNodes(), new LL current node: ${temp1.data}")
            temp1 = temp1.next
        }
        return newHead
    }
}