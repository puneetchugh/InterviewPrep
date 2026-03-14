package com.example.interviewprepration.ds_n_algo

import android.util.Log

object BST {

    val LOG_TAG: String = BST::class.java.name

    data class Node(val data: Int, var left: Node? = null, var right: Node? = null)

    fun driverFunction() {

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

        spiralPrint(root)
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
}