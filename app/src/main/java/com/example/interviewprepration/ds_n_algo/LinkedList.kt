package com.example.interviewprepration.ds_n_algo

import android.util.Log
import kotlin.math.abs

object LinkedList {

    val LOG_TAG: String = LinkedList::class.java.simpleName

    data class LLNode(var data: Int, var next: LLNode? = null)
    data class LLNodeWrapper(var node: LLNode? = null)
    data class DLLNode(var data: Int, var next: DLLNode? = null, var prev: DLLNode? = null)
    data class DLLNodeWrapper(var node: DLLNode?)
    data class DLLNodeAndReference(
        var data: Int,
        var prev: DLLNodeAndReference? = null,
        var next: DLLNodeAndReference? = null,
        var reference: DLLNodeAndReference? = null
    )

    fun driverFunction() {

        val oddTotalNodes = oddCountNode()
        val evenTotalNodes = evenCountNode()

        val middleNodeOdd = findMiddle(oddTotalNodes)
        val middleNodeEven = findMiddle(evenTotalNodes)

        Log.e(LOG_TAG, "Inside driverFunction(), middleNodeOdd: ${middleNodeOdd?.data}")
        Log.e(LOG_TAG, "Inside driverFunction(), middleNodeEven: ${middleNodeEven?.data}")


        val oddTotalNodes1 = oddCountNode()
        val evenTotalNodes1 = evenCountNode()
        val allEvenNodes = allEvenNodes()
        val newOddTotalNodes = moveEvenNodes(oddTotalNodes1)
        val newEvenTotalNodes = moveEvenNodes(evenTotalNodes1)
        val newAllEvenNodes = moveEvenNodes(allEvenNodes)

        val linkedListCycle1 = linkedListCycle1()
        Log.e(
            LOG_TAG,
            "Inside driverFunction(), linkedListCycle1 has loop: ${if (linkedListCycle1 == null) "false" else "true"}"
        )
        val linkedListCycle2 = linkedListCycle2()
        Log.e(
            LOG_TAG,
            "Inside driverFunction(), linkedListCycle2 has loop: ${if (linkedListCycle2 == null) "false" else "true"}"
        )

        val linkedListsWithIntersection = linkedListsWithIntersection()
        Log.e(
            LOG_TAG,
            "Inside driverFunction(), detectIntersection: ${
                detectIntersection(linkedListsWithIntersection)
            }"
        )

        val head = oddCountNode()
        traversal(head = head, tag = "deleteANodeWithReference() before")
        deleteANodeWithReference(head = head?.next?.next)
        traversal(head = head, tag = "deleteANodeWithReference() after")

        val head1 = evenCountNode()
        traversal(head = head1, tag = "deleteANodeWithReference() before")
        deleteANodeWithReference(head = head1?.next?.next)
        traversal(head = head1, tag = "deleteANodeWithReference() after")

        flattenLinkedListWrapper()
        removeLoopWrapper()
        reverseLinkedList()
        deleteMAfterNWrapper()
        reverseInGroupsOfNWrapper()
        splitIntoFrontBackWrapper()
        swapKthNodeFromStartEnd()
        checkingPalindromeWrapper()
    }

    fun traversal(head: LLNode?, tag: String = "") {
        if (head == null)
            return

        Log.e(LOG_TAG, "Inside traversal(), $tag, header: ${head.data}")
        var temp = head
        while (temp != null) {
            Log.e(LOG_TAG, "Inside traversal()->$tag, current node: ${temp.data}")
            temp = temp.next
        }
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

    fun evenCountNode(): LLNode {
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

    fun allEvenNodes(): LLNode? {
        val node1 = LLNode(data = 20)
        val node2 = LLNode(data = 44)
        val node3 = LLNode(data = 88)
        val node4 = LLNode(data = 70)
        val node5 = LLNode(data = 10)
        val node6 = LLNode(data = 22)

        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node6

        return node1
    }

    fun linkedListCycle1(): LLNode {
        val node1 = LLNode(data = 20)
        val node2 = LLNode(data = 44)
        val node3 = LLNode(data = 88)
        val node4 = LLNode(data = 70)
        val node5 = LLNode(data = 10)
        val node6 = LLNode(data = 22)

        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node6
        node6.next = node1
        return node1
    }

    fun linkedListCycle2(): LLNode {
        val node1 = LLNode(data = 20)
        val node2 = LLNode(data = 44)
        val node3 = LLNode(data = 88)
        val node4 = LLNode(data = 70)
        val node5 = LLNode(data = 10)
        val node6 = LLNode(data = 22)

        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5
        node5.next = node6
        node6.next = node3
        return node1
    }

    fun createLinkedListWithReference(): DLLNodeAndReference {
        val node11 = DLLNodeAndReference(data = 10)
        val node12 = DLLNodeAndReference(data = 5, prev = node11)
        val node13 = DLLNodeAndReference(data = 40, prev = node12)
        val node14 = DLLNodeAndReference(data = 30, prev = node13)

        node11.next = node12
        node12.next = node13
        node13.next = node14
        node14.prev = node13
        node13.prev = node12
        node12.prev = node11

        val node21 = DLLNodeAndReference(data = 25)
        val node22 = DLLNodeAndReference(data = 60, prev = node21)
        val node23 = DLLNodeAndReference(data = 20, prev = node22)

        node21.next = node22
        node22.next = node23
        node23.prev = node22
        node22.prev = node21

        val node31 = DLLNodeAndReference(data = 15)
        val node32 = DLLNodeAndReference(data = 55, prev = node31)
        val node33 = DLLNodeAndReference(data = 35, prev = node32)

        node31.next = node32
        node32.next = node33
        node33.prev = node32
        node32.prev = node31

        val node41 = DLLNodeAndReference(data = 45)

        //references
        node32.reference = node41
        node22.reference = node32
        node12.reference = node22

        return node11

    }

    fun linkedListsWithIntersection(): Pair<LLNode?, LLNode?> {

        val node11 = LLNode(data = 10)
        val node12 = LLNode(data = 20)
        val node13 = LLNode(data = 40)
        val node14 = LLNode(data = 15)
        val node15 = LLNode(data = 25)

        node11.next = node12
        node12.next = node13
        node13.next = node14
        node14.next = node15
        node15.next = null

        val node21 = LLNode(data = 35)
        val node22 = LLNode(data = 50)
        val node23 = LLNode(data = 20)
        val node24 = LLNode(data = 30)
        val node25 = LLNode(data = 10)

        node21.next = node22
        node22.next = node23
        node23.next = node24
        node24.next = node25
        node25.next = node14

        return Pair(node11, node21)
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

    fun detectLoop(head: LLNode?): LLNode? {
        if (head == null) {
            return null
        }
        var slowPtr = head
        var fastPtr = head.next

        while (slowPtr != fastPtr && fastPtr != null) {
            slowPtr = slowPtr?.next
            fastPtr = fastPtr.next?.next
        }
        return fastPtr
    }

    fun removeLoopWrapper() {
        val head1 = linkedListCycle1()
        removeLoop(head = head1)

        val head2 = linkedListCycle2()
        removeLoop(head = head2)
    }

    fun removeLoop(head: LLNode) {

        val loopNode = detectLoop(head = head)
        if (loopNode == null) {
            return
        }

        var newHeader: LLNode? = head
        var loopStart = loopNode
        while (newHeader != null) {

            while (loopStart != null && newHeader != loopStart.next && loopNode.next != loopStart) {
                loopStart = loopStart?.next
            }

            if (loopStart?.next == newHeader) {
                loopStart.next = null
                break
            } else {
                newHeader = newHeader.next
            }
        }

        traversal(head = head, tag = "removeLoop(), after loop removal")
    }

    fun detectIntersection(heads: Pair<LLNode?, LLNode?>): LLNode? {
        Log.e(LOG_TAG, "Inside detectIntersection()")
        if (heads.first == null || heads.second == null) {
            return null
        }

        var firstLLNodesCount = 0
        var secondLLNodesCount = 0

        var temp = heads.first
        while (temp != null) {
            firstLLNodesCount++
            temp = temp.next
        }
        temp = heads.second
        while (temp != null) {
            secondLLNodesCount++
            temp = temp.next
        }

        Log.e(
            LOG_TAG,
            "Inside detectIntersection(), firstLLNodesCount: $firstLLNodesCount, secondLLNodesCount: $secondLLNodesCount"
        )

        var diff = abs(firstLLNodesCount - secondLLNodesCount)
        val largerOne = if (firstLLNodesCount > secondLLNodesCount) "first" else "second"

        var temp1 = heads.first
        var temp2 = heads.second

        Log.e(LOG_TAG, "Inside detectIntersection(), largerOne: $largerOne")

        if (largerOne == "first") {
            while (diff > 0) {
                temp1 = temp1?.next
                diff--
            }
        } else {
            while (diff > 0) {
                temp2 = temp2?.next
                diff--
            }
        }

        Log.e(
            LOG_TAG,
            "Inside detectIntersection() after moving difference, temp1: ${temp1?.data}, temp2: ${temp2?.data}"
        )

        while (temp1 != null && temp2 != null && temp1 != temp2) {
            temp1 = temp1.next
            temp2 = temp2.next
        }
        return temp2
    }

    // Delete a node in a linked list without head pointer, given only access to that node
    fun deleteANodeWithReference(head: LLNode?) {
        if (head == null) {
            return
        }

        var temp = head

        while (temp != null && temp.next != null) {
            Log.e(LOG_TAG, "Inside deleteANodeWithReference(), current node: ${temp.data}")
            temp.data = temp.next?.data!!
            if (temp.next?.next == null) {
                temp.next = null
                break
            }
            temp = temp.next
        }
    }

    fun flattenLinkedListWrapper() {
        val head = createLinkedListWithReference()
        flattenLinkedList(node = head, prev = null)
        var temp: DLLNodeAndReference? = head
        while (temp != null) {
            Log.e(LOG_TAG, "Inside flattenLinkedListWrapper(), current node: ${temp.data}")
            temp = temp.next
        }
    }

    fun flattenLinkedList(
        node: DLLNodeAndReference?,
        prev: DLLNodeAndReference?
    ): DLLNodeAndReference? {
        Log.e(LOG_TAG, "Inside flattenLinkedList(), current node: ${node?.data}")
        if (node == null) {
            return null
        }
        var temp = node
        while (temp?.prev != null) {
            temp = temp.prev
        }
        if (prev != null) {
            prev.next = temp
            temp?.prev = prev
        }
        Log.e(LOG_TAG, "Inside flattenLinkedList(), before while loop, temp node: ${temp?.data}")
        while (temp?.next != null) {
            Log.e(LOG_TAG, "Inside flattenLinkedList(), while loop, next node: ${temp.next?.data}")
            val next = temp.next
            if (temp.reference != null) {
                val currentLastNode = flattenLinkedList(node = temp?.reference, prev = temp)
                currentLastNode?.next = next
                next?.prev = currentLastNode
            }
            temp = next
        }

        return if (temp?.reference != null) {
            flattenLinkedList(
                node = temp,
                prev = temp.reference
            )
        } else {
            temp
        }
    }

    fun reverseLinkedList() {

        val head = evenCountNode()

        if (head == null)
            return

        traversal(head = head, tag = "before reverseLinkedList()")

        var current: LLNode? = head
        var prev: LLNode? = null
        var next: LLNode? = null

        while (current != null) {
            next = current.next
            current.next = prev
            prev = current
            current = next

        }

        val newHeader = prev
        traversal(head = newHeader, tag = "after reverseLinkedList()")
    }

    fun deleteMAfterNWrapper() {
        val head = evenCountNode()
        traversal(head = head, tag = "before deleteMAfterN()")
        deleteMAfterN(head = head, m = 2, n = 3)
        traversal(head = head, tag = "after deleteMAfterN()")
    }

    fun deleteMAfterN(head: LLNode?, m: Int, n: Int) {
        if (head == null)
            return

        var temp = head
        var mCounter = 0
        var nCounter = 1

        while (temp != null) {
            if (nCounter < n) {
                nCounter++
                temp = temp.next
            } else {
                temp.next = temp.next?.next
                mCounter++
                if (mCounter == m) {
                    mCounter = 1
                    nCounter = 0
                }
            }
        }
    }

    fun reverseInGroupsOfNWrapper() {
        val head = evenCountNode()
        traversal(head = head, tag = "before reverseInGroupsOfN()")
        val newHeader = reverseInGroupsOfN(head = head, groupSize = 3)
        traversal(head = newHeader, tag = "after reverseInGroupsOfN()")
    }

    fun reverseInGroupsOfN(head: LLNode?, groupSize: Int): LLNode? {
        if (head == null) {
            return null
        }

        var newHeader: LLNode? = null
        var prev: LLNode? = null
        var current: LLNode? = head
        var next: LLNode? = current?.next
        var counter = 0
        var prevListLast: LLNode? = null
        var currentListLast: LLNode? = null
        var groupHeader: LLNode? = null

        while (current != null) {

            prevListLast = currentListLast
            currentListLast = current
            while (counter < groupSize && current != null) {

                next = current.next
                current.next = prev
                prev = current
                current = next
                counter++
            }
            counter = 0

            if (newHeader == null) {
                newHeader = prev
            } else {
                groupHeader = prev
            }

            if (groupHeader != null) {
                prevListLast?.next = groupHeader
            }
            prev = null
        }
        return newHeader
    }


    fun splitIntoFrontBackWrapper() {
        val evenNodesHeader = evenCountNode()
        val oddNodesHeader = oddCountNode()

        val evenNodesPair = splitIntoFrontBack(evenNodesHeader)
        val oddNodesPair = splitIntoFrontBack(oddNodesHeader)

        traversal(head = evenNodesHeader, "Even node counts: splitIntoFrontBackWrapper()")
        Log.e(
            LOG_TAG,
            "Inside splitIntoFrontBackWrapper(), evenNodesPair.first: ${evenNodesPair.first?.data}, evenNodesPair.second: ${evenNodesPair.second?.data}"
        )

        traversal(head = oddNodesHeader, "Odd node counts: splitIntoFrontBackWrapper()")
        Log.e(
            LOG_TAG,
            "Inside splitIntoFrontBackWrapper(), oddNodesPair.first: ${oddNodesPair.first?.data}, oddNodesPair.second: ${oddNodesPair.second?.data}"
        )
    }

    //Split nodes of a linked list into the front and back halves
    fun splitIntoFrontBack(head: LinkedList.LLNode?): Pair<LLNode?, LLNode?> {

        if (head == null) {
            return Pair(null, null)
        }

        var slowPtr = head
        var fastPtr = head.next

        while (fastPtr != null && fastPtr.next != null) {

            fastPtr = fastPtr.next?.next
            slowPtr = slowPtr?.next
        }

        val first = head
        val second = slowPtr?.next

        return Pair(first, second)
    }

    // Construct a height-balanced BST from a sorted doubly linked list
    fun constructBSTFromSortedDLL(head: DLLNodeWrapper, nodes: Int): DLLNode? {
        if (head.node == null) {
            return null
        }

        val root = head.node

        val left = constructBSTFromSortedDLL(head = head, nodes = nodes / 2)
        root!!.prev = left

        head.node = head.node!!.next

        val right = constructBSTFromSortedDLL(head = head, nodes = (nodes - (nodes / 2 + 1)))
        root.next = right

        return root
    }

    fun swapKthNodeFromStartEnd() {
        val head = evenCountNode()
        //traversal(head = head, tag = "swapKthNodeFromStartEnd() before")
        swapKthNodeFromStartEndHelper(head = head, k = 3)
    }

    //Swap k’th node from beginning with k’th node from the end in a linked list
    fun swapKthNodeFromStartEndHelper(head: LLNode, k: Int) {

        traversal(head = head, tag = "swapKthNodeFromStartEnd() before")

        var counter: Int = 1
        var temp: LLNode? = head

        while (counter < k && temp != null) {
            temp = temp.next
            counter++
        }

        var kthNodeFromStart: LLNode? = temp
        var temp2: LLNode? = head
        while (temp?.next != null) {
            temp2 = temp2?.next
            temp = temp.next
        }
        val tempData = temp2!!.data
        temp2.data = kthNodeFromStart!!.data
        kthNodeFromStart.data = tempData

        traversal(head = head, tag = "swapKthNodeFromStartEnd() after")
    }

    fun checkingPalindromeWrapper() {

        val node1 = LLNode(data = 1)
        val node2 = LLNode(data = 2)
        val node3 = LLNode(data = 3)
        val node4 = LLNode(data = 2)
        val node5 = LLNode(data = 1)

        node1.next = node2
        node2.next = node3
        node3.next = node4
        node4.next = node5

        val head1 = node1

        val node11 = LLNode(data = 1)
        val node21 = LLNode(data = 2)
        val node31 = LLNode(data = 3)
        val node41 = LLNode(data = 3)
        val node51 = LLNode(data = 2)
        val node61 = LLNode(data = 1)

        node11.next = node21
        node21.next = node31
        node31.next = node41
        node41.next = node51
        node51.next = node61

        val head11 = node11

        traversal(head = head1, tag = "First list, checkingPalindromeWrapper() before")
        val isPalindrome1 = checkingPalindrome(left = LLNodeWrapper(node = head1), right = head1)
        Log.e(LOG_TAG, "Inside checkingPalindromeWrapper(), isPalindrome: $isPalindrome1")

        traversal(head = head11, tag = "SEcond list, checkingPalindromeWrapper() before")
        val isPalindrome2 = checkingPalindrome(left = LLNodeWrapper(node = head11), right = head11)
        Log.e(LOG_TAG, "Inside checkingPalindromeWrapper(), isPalindrome: $isPalindrome2")
    }

    fun checkingPalindrome(left: LLNodeWrapper, right: LLNode?): Boolean {

        if (right == null)
            return true
        val isPalindrome = checkingPalindrome(left = left, right = right.next)
        if (isPalindrome.not()) {
            return false
        }
        left.node = left.node?.next
        return true
    }
}