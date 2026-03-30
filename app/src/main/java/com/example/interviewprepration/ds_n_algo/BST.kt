package com.example.interviewprepration.ds_n_algo

import android.util.Log
import kotlin.collections.ArrayDeque

object BST {

    val LOG_TAG: String = BST::class.java.name

    data class Node(var data: Int, var left: Node? = null, var right: Node? = null)


    fun driverFunction() {

        spiralPrint(root = sampleBST())
        val root = sampleBST()
        sumOfAllGreater(root = root)

        val root1 = sampleBST()
        levelOrderTraversal(root1)

        invertBT(root1)
        levelOrderTraversal(root1)
    }

    fun sampleBST(): Node {
        val root = Node(data = 20)
        val nodeA = Node(data = 10)
        val nodeB = Node(data = 32)
        val nodeC = Node(data = 25)
        val nodeD = Node(data = 15)
        val nodeE = Node(data = 50)
        val nodeF = Node(data = 18)
        val nodeG = Node(data = 30)
        val nodeH = Node(data = 22)

        root.left = nodeA
        root.right = nodeB

        nodeB.left = nodeC
        nodeB.right = nodeE

        nodeA.right = nodeD

        nodeC.left = nodeH
        nodeC.right = nodeG

        nodeD.right = nodeF
        return root
    }

    fun spiralPrint(root: Node?) {
        Log.e(LOG_TAG, "Inside spiralPrint: root: ${root?.data}")
        if (root == null)
            return

        val queue = ArrayDeque<Node>()
        queue.add(root)

        var leftToRight = true

        while (!queue.isEmpty()) {
            var counter = queue.size

            while (counter > 0) {
                val current = if (leftToRight) queue.removeFirst() else queue.removeLast()

                Log.e(LOG_TAG, "SpiralPrint: ${current.data}")

                if (leftToRight) {
                    current.left?.let {
                        queue.add(it)
                    }
                    current.right?.let {
                        queue.add(it)
                    }
                } else {
                    current.right?.let {
                        queue.addFirst(it)
                    }
                    current.left?.let {
                        queue.addFirst(it)
                    }
                }
                counter--
            }
            leftToRight = !leftToRight
        }
    }

    //Update every key in a BST to contain the sum of all greater keys
    fun sumOfAllGreater(root: Node) {
        Log.e(LOG_TAG, "Inside sumOfAllGreater: original inOrder: ")
        inOrder(root)
        reverseInorder(root)
        Log.e(LOG_TAG, "Inside sumOfAllGreater: updated inOrder: ")
        inOrder(root)
    }

    var sum = 0

    fun reverseInorder(root: Node?) {
        if (root == null)
            return
        reverseInorder(root.right)
        root.data += sum
        sum = root.data
        reverseInorder(root.left)

    }

    fun inOrder(root: Node?) {
        if (root == null)
            return
        inOrder(root.left)
        Log.e(LOG_TAG, "inOrder: ${root.data}")
        inOrder(root.right)

    }

    fun levelOrderTraversal(node: Node?) {
        if (node == null)
            return

        val queue = ArrayDeque<Node>()
        queue.add(node)
        var level = 0

        while (!queue.isEmpty()) {
            var levelSize = queue.size
            Log.e(LOG_TAG, "levelOrderTraversal: Level: $level, levelSize: $levelSize")
            while (levelSize > 0) {
                val current = queue.removeFirst()
                Log.e(LOG_TAG, "levelOrderTraversal: ${current.data}")
                current.left?.let {
                    queue.add(it)
                }
                current.right?.let {
                    queue.add(it)
                }
                levelSize--
            }
            level++
        }
    }

    fun invertBT(root: Node?) {

        if (root == null) {
            return
        }
        val queue = ArrayDeque<Node>()
        queue.add(root)

        while (!queue.isEmpty()) {

            val current = queue.removeFirst()
            val temp = current.left
            current.left = current.right
            current.right = temp

            current.left?.let {
                queue.addLast(it)
            }

            current.right?.let {
                queue.addLast(it)
            }
        }
    }

}