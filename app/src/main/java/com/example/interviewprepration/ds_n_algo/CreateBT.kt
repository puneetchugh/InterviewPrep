package com.example.interviewprepration.ds_n_algo

import android.util.Log
import java.util.concurrent.atomic.AtomicInteger

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

        createBTFromInorderPreorder()
    }

    fun inOrder(node: Node?, tag: String? = "") {
        if (node == null)
            return

        inOrder(node.left, tag = tag)
        Log.e(LOG_TAG, "inOrder, ${tag}: ${node.data}")
        inOrder(node.right, tag = tag)
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

    fun createBTFromInorderPreorder() {
        val inOrder = arrayOf(4, 8, 10, 12, 14, 20, 22)
        val inOrderMap = inOrder.withIndex().associate { (index, item) -> item to index }
        val preOrder = arrayOf(20, 8, 4, 12, 10, 14, 22)
        val root = createBTFromInorderPreorderHelper(
            preOrder = preOrder,
            inOrderMap = inOrderMap,
            startInorder = 0,
            endInorder = inOrder.size - 1,
            index = AtomicInteger()
        )
        inOrder(root, tag = "createBTFromInorderPreorder")
    }

    fun createBTFromInorderPreorderHelper(
        preOrder: Array<Int>,
        inOrderMap: Map<Int, Int>,
        startInorder: Int,
        endInorder: Int,
        index: AtomicInteger
    ): Node? {

        if (startInorder > endInorder)
            return null

        val node = Node(data = preOrder[index.get()])
        index.andIncrement
        node.left = createBTFromInorderPreorderHelper(
            preOrder = preOrder,
            inOrderMap = inOrderMap,
            startInorder = startInorder,
            endInorder = inOrderMap[node.data]!! - 1,
            index = index
        )

        node.right = createBTFromInorderPreorderHelper(
            preOrder = preOrder,
            inOrderMap = inOrderMap,
            startInorder = inOrderMap[node.data]!! + 1,
            endInorder = endInorder,
            index = index
        )
        return node
    }
}