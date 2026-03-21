package com.example.interviewprepration.ds_n_algo

import android.util.Log

object CreateBT {

    data class Node(val data: Int, var left: Node? = null, var right: Node? = null)

    val LOG_TAG: String = CreateBT::class.java.name

    fun driverFunction() {
        val inOrder = arrayOf(4, 8, 10, 12, 14, 20, 22)
        val levelOrder = arrayOf(20, 8, 22, 4, 12, 10, 14)
        val levelOrderMap = levelOrder.withIndex().associate { (index, item) -> item to index }
        Log.e(
            LOG_TAG,
            "Inside driverFunction: levelOrderMap: $levelOrderMap"
        )
        val root = createBTUsingInorderLevelOrder(
            inOrder = inOrder,
            levelOrderMap = levelOrderMap,
            start = 0,
            end = inOrder.size - 1
        )
        Log.e(
            LOG_TAG,
            "Inside CreateBT: createBTUsingInorderLevelOrder(), inOrder: ${inOrder.contentToString()}, levelOrder: ${levelOrder.contentToString()}"
        )

        Log.e(
            LOG_TAG,
            "Inside CreateBT: createBTUsingInorderLevelOrder(), inOrder traversal: "
        )

        inOrder(root)
    }

    fun inOrder(node: Node?) {
        if (node == null)
            return

        inOrder(node.left)
        Log.e(LOG_TAG, "inOrder: ${node.data}")
        inOrder(node.right)
    }

    // Create BT from inOrder and levelOrder
    fun createBTUsingInorderLevelOrder(
        inOrder: Array<Int>,
        levelOrderMap: Map<Int, Int>,
        start: Int,
        end: Int
    ): Node? {
        if (start > end) {
            return null
        }

        var lowestIndex = levelOrderMap[inOrder[start]]!!
        var counter = start + 1
        var inOrderIndex = start
        Log.e(LOG_TAG, "Just before the while loop, start: $start, end: $end, counter: $counter")
        while (counter <= end) {
            Log.e(
                LOG_TAG,
                "Inside while loop, counter: $counter, lowestIndex: $lowestIndex, inOrder[counter]: ${inOrder[counter]}, levelOrderMap[inOrder[counter]]: ${levelOrderMap[inOrder[counter]]}"
            )
            if (levelOrderMap[inOrder[counter]]!! < lowestIndex) {
                lowestIndex = levelOrderMap[inOrder[counter]]!!
                inOrderIndex = counter
            }
            counter++
        }
        val node = Node(data = inOrder[inOrderIndex])
        Log.e(LOG_TAG, "Node created: ${node.data}")
        node.left = createBTUsingInorderLevelOrder(
            inOrder = inOrder,
            levelOrderMap = levelOrderMap,
            start = start,
            end = inOrderIndex - 1
        )
        node.right = createBTUsingInorderLevelOrder(
            inOrder = inOrder,
            levelOrderMap = levelOrderMap,
            start = inOrderIndex + 1,
            end = end
        )
        return node
    }
}