package com.example.interviewprepration.ds_n_algo

import android.util.Log
import java.util.PriorityQueue
import java.util.concurrent.atomic.AtomicInteger
import kotlin.collections.ArrayDeque
import kotlin.math.max

object BST {

    val LOG_TAG: String = BST::class.java.name

    data class Node(var data: Int, var left: Node? = null, var right: Node? = null)
    data class NodeWrapperOnlyNode(var node: Node?)
    data class DLLNode(var data: Int, var prev: Node? = null, var next: Node? = null)

    fun driverFunction() {

        spiralPrint(root = sampleBST())
        val root = sampleBST()
        sumOfAllGreater(root = root)

        val root1 = sampleBST()
        levelOrderTraversal(root1)

        invertBT(root1)
        levelOrderTraversal(root1)

        val isBST = checkPreOrderForBST(arrayOf(10, 5, 2, 8, 20, 15))
        Log.e(LOG_TAG, "Inside driverFunction: isBST for given Pre-order: $isBST")

        createDLL()

        createBTFromInOrderPreOrder(
            inOrder = arrayOf(2, 5, 8, 10, 20, 30),
            preOrder = arrayOf(10, 5, 2, 8, 20, 30)
        )

        topView()
        printBottomView()
        leftView()
        createBSTFromPreorder()
        createBSTFromPostOrder()
        removeNodesOutsideRange()
        convertBST2DLL(sampleBST())
        printCousins(sampleBST(), 15)
        printCousins(sampleBST(), 18)

        val sampleBSTForSumTree = sampleBST()
        Log.e(LOG_TAG, "convertToSumTree(), Inorder before converting to sum tree: ")
        inOrder(root = sampleBSTForSumTree, tag = "convertToSumTree()")
        val sum = convertToSumTree(sampleBSTForSumTree)
        Log.e(
            LOG_TAG,
            "convertToSumTree(), Final sum: $sum, Inorder after converting to sum tree: "
        )
        inOrder(root = sampleBSTForSumTree, tag = "convertToSumTree()")

        findDistanceBtwNodesWrapper()
        findDiameterWrapper()

        isTreeSubTreeWrapper()
        inOrderSuccessorWrapper()
        convertBT2FullBT()
        deleteNodesOutsideRange()
        maxSumPathWrapper()
        extractLeavesWrapper()
        checkIsMinHeapWrapper()
        fixBSTWrapper()
        buildHuffmanTreeWrapper()
        checkIfSymmetric()
        boundaryTraversal()
        findNodesAncestors()
        printReverseLevelOrder()
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

    fun sampleBSTSymmetric(): Node {
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
        //nodeB.right = nodeE

        nodeA.right = nodeD

        nodeC.left = nodeH
        nodeC.right = nodeG

        nodeD.right = nodeF
        nodeD.left = nodeE

        return root
    }

    fun sampleSubTree(): Node? {

        val nodeB = Node(data = 32)
        val nodeC = Node(data = 25)
        val nodeE = Node(data = 50)
        val nodeG = Node(data = 30)
        val nodeH = Node(data = 22)

        nodeB.left = nodeC
        nodeB.right = nodeE

        nodeC.left = nodeH
        nodeC.right = nodeG

        return nodeB
    }

    fun sampleBTWithOneIssue(): Node? {
        val root = Node(data = 20)
        val nodeA = Node(data = 10)
        val nodeB = Node(data = 32)
        val nodeC = Node(data = 15)
        val nodeD = Node(data = 25)
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

    fun createMaxHeap(): Node {
        val root = Node(data = 44)
        val nodeA = Node(data = 40)
        val nodeB = Node(data = 32)
        val nodeC = Node(data = 33)
        val nodeD = Node(data = 15)
        val nodeE = Node(data = 11)
        val nodeF = Node(data = 18)
        val nodeG = Node(data = 30)
        val nodeH = Node(data = 22)

        root.left = nodeA
        root.right = nodeB

        nodeA.left = nodeC
        nodeA.right = nodeE

        nodeB.right = nodeD
        nodeB.left = nodeF

        nodeC.left = nodeH
        nodeC.right = nodeG

        return root
    }

    fun createMinHeap(): Node {
        val root = Node(data = 5)
        val nodeA = Node(data = 10)
        val nodeB = Node(data = 22)
        val nodeC = Node(data = 33)
        val nodeD = Node(data = 25)
        val nodeE = Node(data = 11)
        val nodeF = Node(data = 28)
        val nodeG = Node(data = 39)
        val nodeH = Node(data = 44)

        root.left = nodeA
        root.right = nodeB

        nodeA.left = nodeC
        nodeA.right = nodeE

        nodeB.right = nodeD
        nodeB.left = nodeF

        nodeC.left = nodeH
        nodeC.right = nodeG

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

    fun inOrder(root: Node?, tag: String = "") {
        if (root == null)
            return
        inOrder(root = root.left, tag = tag)
        Log.e(LOG_TAG, "$tag inOrder: ${root.data}")
        inOrder(root = root.right, tag = tag)
    }

    fun preOrder(root: Node?) {
        if (root == null)
            return
        Log.e(LOG_TAG, "preOrder: ${root.data}")
        preOrder(root.left)
        preOrder(root.right)
    }

    fun preOrderStorage(root: Node?, list: MutableList<Int>) {
        if (root == null)
            return
        list.add(root.data)
        preOrderStorage(root.left, list)
        preOrderStorage(root.right, list)
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

    //Check if sequence is for Pre-order of BST
    fun checkPreOrderForBST(preOrder: Array<Int>): Boolean {

        val newBinaryTreeRoot = createBSTFromPreOrder(0, preOrder.size - 1, preOrder)
        val preOrderTraversal = mutableListOf<Int>()
        preOrderStorage(newBinaryTreeRoot, preOrderTraversal)

        Log.e(LOG_TAG, "Pre-order traversal array original: ${preOrder.contentToString()}")

        Log.e(
            LOG_TAG,
            "Pre-order traversal of new binary tree: ${preOrderTraversal.joinToString()}"
        )

        val arrayEquals = preOrder.contentEquals(preOrderTraversal.toTypedArray())

        return arrayEquals
    }


    //Create BST from Pre-order
    fun createBSTFromPreOrder(left: Int, right: Int, preOrder: Array<Int>): Node? {

        if (left > right)
            return null

        Log.e(
            LOG_TAG,
            "Inside createBSTFromPreOrder() Pre-order, left: $left, right: $right, currentNode: ${preOrder[left]}"
        )
        val current = Node(data = preOrder[left])
        var counter = left + 1
        while (counter <= right && preOrder[counter] < current.data) {
            counter++
        }
        current.left = createBSTFromPreOrder(left + 1, counter - 1, preOrder)
        current.right = createBSTFromPreOrder(counter, right, preOrder)

        return current
    }

    // Create a sorted Doubly LinkedList from BST
    fun createDLL() {
        Log.e(LOG_TAG, "Inside createDLL()")
        var rootBST = sampleBST()
        inOrder(root = rootBST, tag = "createDLL()")
        var newDLLRoot = createDLLHelper(rootBST, next = null)
        Log.e(LOG_TAG, "Inside createDLL(), Printing the Doubly Linked List")
        while (newDLLRoot != null) {
            Log.e(LOG_TAG, "Inside createDLL(), current node: ${newDLLRoot.data}")
            newDLLRoot = newDLLRoot.right
        }
    }

    fun createDLLHelper(current: Node?, next: Node?): Node? {
        if (current == null)
            return next

        var nextNode = createDLLHelper(current = current.right, next = next)

        if (nextNode != null) {
            nextNode.left = current
        }
        current.right = nextNode
        return createDLLHelper(current = current.left, next = current)
    }

    //Create a BT from InOrder and PreOrder
    fun createBTFromInOrderPreOrder(inOrder: Array<Int>, preOrder: Array<Int>) {

        val root = createBTFromInOrderPreOrderHelper(
            inOrderMap = inOrder.withIndex().associate { (index, item) -> item to index },
            preOrder = preOrder,
            start = 0,
            end = inOrder.size - 1,
            preOrderIndex = AtomicInteger(0)
        )

        inOrder(root = root, "createBTFromInOrderPreOrder()")
    }

    fun createBTFromInOrderPreOrderHelper(
        inOrderMap: Map<Int, Int>,
        preOrder: Array<Int>,
        start: Int,
        end: Int,
        preOrderIndex: AtomicInteger
    ): Node? {
        if (start > end || preOrderIndex.get() >= preOrder.size)
            return null

        val node = Node(data = preOrder[preOrderIndex.get()])
        val index = inOrderMap[node.data]!!
        preOrderIndex.andIncrement
        node.left = createBTFromInOrderPreOrderHelper(
            inOrderMap = inOrderMap,
            preOrder = preOrder, start = start, end = index - 1,
            preOrderIndex = preOrderIndex
        )
        node.right = createBTFromInOrderPreOrderHelper(
            inOrderMap = inOrderMap,
            preOrder = preOrder, start = index + 1, end = end,
            preOrderIndex = preOrderIndex
        )

        return node
    }

    // top view of BST - using level order traversal
    fun topView() {

        Log.e(LOG_TAG, "Inside topView()")
        val root = sampleBST()

        val queue = ArrayDeque<Pair<Node, Int>>()
        queue.add(Pair(root, 0))

        val horizontalMapping = mutableMapOf<Int, Node>()
        var verticalLevel = 0
        while (queue.isNotEmpty()) {
            var nodeCount = queue.size

            while (nodeCount > 0) {
                val current = queue.removeFirst()

                if (!horizontalMapping.containsKey(current.second)) {
                    horizontalMapping[current.second] = current.first
                }

                current.first.left?.let {
                    queue.add(Pair(it, current.second - 1))
                }
                current.first.right?.let {
                    queue.add(Pair(it, current.second + 1))
                }
                nodeCount--
            }
            verticalLevel++
        }

        horizontalMapping.entries.forEach {
            Log.e(LOG_TAG, "topView: ${it.key},${it.value.data}")
        }
    }

    // bottom view using pre-prder traversal
    data class NodeWrapper(val node: Node, val horizontalLevel: Int)

    fun printBottomView() {
        val root = sampleBST()

        val mapping = mutableMapOf<Int, NodeWrapper>()

        preOrderTraversal(root = root, map = mapping, level = 0, horizontalDistance = 0)

        Log.e(LOG_TAG, "Inside printBottomView(), bottom view: ")
        mapping.forEach {
            Log.e(
                LOG_TAG,
                "printBottomView(): horizontalDist: ${it.key}, value: ${it.value.node.data}, level: ${it.value.horizontalLevel}"
            )
        }
    }

    fun preOrderTraversal(
        root: Node?,
        map: MutableMap<Int, NodeWrapper>,
        level: Int,
        horizontalDistance: Int
    ) {
        Log.e(
            LOG_TAG,
            "Inside printBottomView()->preOrderTraversal(), root: ${root?.data}, level: $level, horizontalDistance: $horizontalDistance"
        )
        if (root == null) {
            return
        }

        if (map.containsKey(horizontalDistance)) {
            if (map[horizontalDistance]!!.horizontalLevel <= level) {
                map[horizontalDistance] = NodeWrapper(node = root, horizontalLevel = level)
            }
        } else {
            map.put(horizontalDistance, NodeWrapper(node = root, horizontalLevel = level))
        }

        preOrderTraversal(root.left, map, level + 1, horizontalDistance - 1)
        preOrderTraversal(root.right, map, level + 1, horizontalDistance + 1)
    }

    fun leftView() {
        val root = sampleBST()

        leftViewTraversal(root = root)
    }

    fun leftViewTraversal(root: Node?) {
        if (root == null) {
            return
        }

        Log.e(LOG_TAG, "Left view, printing node: ${root.data}")
        if (root.left != null) {
            leftViewTraversal(root.left)
        } else {
            leftViewTraversal(root.right)
        }
    }

    fun createBSTFromPreorder() {

        val inputTraversal = intArrayOf(20, 10, 7, 15, 22, 30)
        val root = createBSTFromPreorderHelper(
            input = inputTraversal,
            start = 0,
            end = inputTraversal.size - 1
        )
        inOrder(root = root, tag = "createBSTFromPreorder")
    }

    fun createBSTFromPreorderHelper(input: IntArray, start: Int, end: Int): Node? {

        if (start > end)
            return null

        val node = Node(data = input[start])

        var counter = start + 1

        while (counter < input.size && input[counter] < node.data) {
            counter++
        }
        if (counter > start) {
            node.left = createBSTFromPreorderHelper(input, start + 1, counter - 1)
        }
        if (counter <= end) {
            node.right = createBSTFromPreorderHelper(input, counter, end)
        }

        return node
    }

    fun createBSTFromPostOrder() {
        val postOrder = intArrayOf(7, 15, 10, 25, 30, 22, 20)
        val root =
            createBSTFromPostOrderHelper(input = postOrder, start = 0, end = postOrder.size - 1)
        inOrder(root = root, tag = "createBSTFromPostOrder")
    }

    fun createBSTFromPostOrderHelper(input: IntArray, start: Int, end: Int): Node? {
        if (start > end)
            return null

        val node = Node(data = input[end])
        var counter = end - 1

        while (counter >= start && input[counter] > input[end]) {
            counter--
        }

        if (counter < end) {
            node.right = createBSTFromPostOrderHelper(input, counter + 1, end - 1)
        }

        if (counter >= start) {
            node.left = createBSTFromPostOrderHelper(input, start, counter)
        }
        return node
    }

    fun removeNodesOutsideRange() {

        val root = sampleBST()
        val range = Pair(20, 30)

        val newRoot = removeNodesOutsideRangeHelper(root, range)
        inOrder(newRoot, "removeNodesOutsideRange()")
    }

    fun removeNodesOutsideRangeHelper(root: Node?, range: Pair<Int, Int>): Node? {
        if (root == null)
            return null

        if (root.data < range.first) {
            return removeNodesOutsideRangeHelper(root.right, range)
        }
        if (root.data > range.second) {
            return removeNodesOutsideRangeHelper(root.left, range)
        }

        root.left = removeNodesOutsideRangeHelper(root.left, range)
        root.right = removeNodesOutsideRangeHelper(root.right, range)

        return root
    }

    fun convertBST2DLL(root: Node?) {
        if (root == null)
            return

        val head = convertBST2DLLHelper(root = root, head = null)
        var tmp = head
        while (tmp != null) {
            Log.e(LOG_TAG, "convertBST2DLL, node: ${tmp.data}")
            tmp = tmp?.right
        }
    }

    fun convertBST2DLLHelper(root: Node?, head: Node?): Node? {
        Log.e(LOG_TAG, "Inside convertBST2DLLHelper(), visiting, root: ${root?.data}")
        if (root == null) {
            return head
        }

        val right = convertBST2DLLHelper(root = root.right, head = head)
        root.right = right
        right?.let { right.left = root }

        return convertBST2DLLHelper(root = root.left, head = root)
    }

    fun printCousins(root: Node?, target: Int) {
        Log.e(LOG_TAG, "Inside printCousins(), target: $target, root: $root")
        if (root == null)
            return

        val queue = ArrayDeque<Node>()
        queue.add(root)

        var currentLevel = 0
        var nodeLevel = Int.MAX_VALUE

        val cousins = mutableListOf<Int>()

        while (queue.isNotEmpty()) {
            var nodeCount = queue.size
            Log.e(LOG_TAG, "Inside printCousins(), level: $currentLevel, nodeCount: $nodeCount")
            while (--nodeCount >= 0) {
                val visiting = queue.removeFirst()

                if (currentLevel == nodeLevel) {
                    cousins.add(visiting.data)
                }
                if ((visiting.left != null && visiting.left?.data == target)
                    || (visiting.right != null && visiting.right?.data == target
                            )
                ) {
                    nodeLevel = currentLevel + 1
                    continue
                }

                if (currentLevel < nodeLevel) {
                    if (visiting.left != null) {
                        queue.addLast(visiting.left!!)
                    }
                    if (visiting.right != null) {
                        queue.addLast(visiting.right!!)
                    }
                }
            }
            currentLevel++
        }
        Log.e(
            LOG_TAG,
            "Inside printCousins(), printing cousins of $target: ${cousins.joinToString()}"
        )
    }

    // Given a binary tree, in-place replace each node’s value to the sum of all elements present in its left and right subtree.
    // You may assume the value of an empty child node to be 0.
    fun convertToSumTree(root: Node?): Int {

        Log.e(LOG_TAG, "Inside convertToSumTree(), root: ${root?.data}")
        if (root == null) {
            return 0
        }

        val left = convertToSumTree(root.left)
        val right = convertToSumTree(root.right)
        Log.e(LOG_TAG, "Inside convertToSumTree(), root: ${root?.data}, left: $left, right: $right")

        val currentNodeData = root.data
        root.data = left + right
        return currentNodeData + root.data
    }

    fun findLCA(root: Node?, data1: Int, data2: Int): Node? {
        if (root == null) {
            return null
        }
        if (root.data == data1 || root.data == data2) {
            return root
        }

        val left = findLCA(root = root.left, data1 = data1, data2 = data2)
        val right = findLCA(root = root.right, data1 = data1, data2 = data2)

        if (left != null && right != null) {
            return root
        }

        if (left != null) {
            return left
        }

        if (right != null) {
            return right
        }
        return null
    }

    fun findDistanceBtwNodesWrapper() {
        val root = sampleBST()
        val distance1 = findDistanceBtwNodes(root = root, data1 = 20, data2 = 30)
        val distance2 = findDistanceBtwNodes(root = root, data1 = 10, data2 = 50)

        Log.e(
            LOG_TAG,
            "Inside findDistanceBtwNodesWrapper(), distance between 20 and 30: $distance1"
        )
        Log.e(
            LOG_TAG,
            "Inside findDistanceBtwNodesWrapper(), distance between 10 and 50: $distance2"
        )

    }

    // find distance between two nodes
    fun findDistanceBtwNodes(root: Node?, data1: Int, data2: Int): Int {
        val lca = findLCA(root = root, data1 = data1, data2 = data2)

        if (lca == null) {
            return -1
        }

        val heightOfData1FromLca = findHeightOfNode(root = lca, data = data1)
        val heightOfData2FromLca = findHeightOfNode(root = lca, data = data2)

        Log.e(LOG_TAG, "Inside findDistanceBtwNodes(), lca of $data2 and $data1 is: ${lca.data}")

        Log.e(
            LOG_TAG,
            "Inside findDistanceBtwNodes(), heightOfData1 $data1 is $heightOfData1FromLca, heightOfData2 $data2: $heightOfData2FromLca"
        )
        return heightOfData1FromLca + heightOfData2FromLca
    }

    fun findHeightOfNode(root: Node?, data: Int): Int {
        if (root == null) {
            return Int.MIN_VALUE
        }

        if (root.data == data) {
            return 0
        } else if (data > root.data) {
            return 1 + findHeightOfNode(root = root.right, data = data)
        } else {
            return 1 + findHeightOfNode(root = root.left, data = data)
        }
    }


    fun findDiameterWrapper() {
        val root = sampleBST()
        val diameter = findDiameter(root = root)
        Log.e(LOG_TAG, "Inside findDiameterWrapper(), diameter: ${diameter.diameter}")
    }

    data class DiameterClass(var diameter: Int, var height: Int)

    fun findDiameter(root: Node?): DiameterClass {

        if (root == null) {
            return DiameterClass(diameter = 0, height = 0)
        }

        val left = findDiameter(root = root.left)
        val right = findDiameter(root = root.right)

        Log.e(
            LOG_TAG,
            "Inside findDiameter(), root: ${root.data}, left, height: ${left.height}, diameter: ${left.diameter}, right, height: ${right.height}, diameter: ${right.diameter}"
        )

        val height = 1 + max(left.height, right.height)
        val diameter = max(
            if (left.height != 0 && right.height != 0) {
                left.height + right.height + 1
            } else {
                left.height + right.height
            }, max(left.diameter, right.diameter)
        )

        Log.e(
            LOG_TAG,
            "Inside findDiameter(), root: ${root.data} diameter: ${diameter}, height: ${height}"
        )
        return DiameterClass(height = height, diameter = diameter)
    }

    fun isTreeSubTreeWrapper() {
        val mainRoot = sampleBST()

        val subRoot1 = sampleBST()
        val isSubTree1 = isTreeSubTree(mainRoot = mainRoot, subRoot = subRoot1)
        Log.e(LOG_TAG, "Inside isTreeSubTreeWrapper(), isSubTree: $isSubTree1")

        val subRoot2 = sampleSubTree()
        val isSubTree2 = isTreeSubTree(mainRoot = mainRoot, subRoot = subRoot2)
        Log.e(LOG_TAG, "Inside isTreeSubTreeWrapper(), isSubTree: $isSubTree2")
    }

    fun isTreeSubTree(mainRoot: Node?, subRoot: Node?): Boolean {
        if (mainRoot == null || subRoot == null) {
            return false
        }
        return isTreeSubTreeHelper(root = mainRoot, subRoot = subRoot, startedMatching = false)
    }

    fun isTreeSubTreeHelper(root: Node?, subRoot: Node?, startedMatching: Boolean): Boolean {
        Log.e(
            LOG_TAG,
            "Inside isTreeSubTreeHelper(), startedMatching: $startedMatching, root: ${root?.data}, subRoot: ${subRoot?.data}"
        )
        if (startedMatching) {
            if (root == null && subRoot == null) {
                return true
            } else if (root == null || subRoot == null) {
                return false
            }
        } else if (root == null || subRoot == null) {
            return false
        }

        Log.e(
            LOG_TAG,
            "Inside isTreeSubTreeHelper(), root: ${root?.data}, subRoot: ${subRoot?.data}"
        )
        if (root.data == subRoot.data) {
            return isTreeSubTreeHelper(
                root = root.left,
                subRoot = subRoot.left,
                startedMatching = true
            ) && isTreeSubTreeHelper(
                root = root.right,
                subRoot = subRoot.right,
                startedMatching = true
            )
        } else {
            return isTreeSubTreeHelper(
                root = root.left,
                subRoot = subRoot,
                startedMatching = false
            ) || isTreeSubTreeHelper(
                root = root.right,
                subRoot = subRoot,
                startedMatching = false
            )
        }
    }

    fun inOrderSuccessorWrapper() {
        val root = sampleBST()
        val inOrderSuccessor = inOrderSuccessor(root = root, data = 15)
        Log.e(
            LOG_TAG,
            "Inside inOrderSuccessorWrapper(), inOrderSuccessor of 15 is: ${inOrderSuccessor?.data}"
        )

        val inOrderSuccessor1 = inOrderSuccessor(root = root, data = 20)
        Log.e(
            LOG_TAG,
            "Inside inOrderSuccessorWrapper(), inOrderSuccessor of 20 is: ${inOrderSuccessor1?.data}"
        )
    }

    fun inOrderSuccessor(root: Node?, data: Int): Node? {
        if (root == null) {
            return null
        }

        var current = root
        var parent: Node? = null
        while (current != null) {
            if (data > current.data) {
                parent = current
                current = current.right
            } else if (data < current.data) {
                parent = current
                current = current.left
            } else {
                break
            }
        }

        var inOrderSuccessor: Node? = current?.right
        while (inOrderSuccessor?.left != null) {
            inOrderSuccessor = inOrderSuccessor.left
        }
        return inOrderSuccessor
    }

    //Convert a binary tree to a full tree by removing half nodes
    fun convertBT2FullBT() {
        val root = sampleBST()
        Log.e(LOG_TAG, "Inside convertBT2FullBT()")
        inOrder(root = root, tag = "before convertBT2FullBT()")
        val newRoot = convertBT2FullBTHelper(root = root)
        inOrder(newRoot, "after convertBT2FullBT()")
    }

    fun convertBT2FullBTHelper(root: Node?): Node? {
        if (root == null) {
            return null
        }

        val temp = root
        temp.left = convertBT2FullBTHelper(root.left)
        temp.right = convertBT2FullBTHelper(root.right)

        if ((temp.left != null && temp.right != null) || (temp.left == null && temp.right == null)) {
            return root
        }

        return if (temp.left != null) {
            root.left
        } else {
            root.right
        }
    }

    fun deleteNodesOutsideRange() {
        val root = sampleBST()
        inOrder(root = root, tag = "before deleteNodesOutsideRange()")
        val range = Pair(15, 25)
        val newRoot =
            deleteNodesOutsideRangeHelper(root = root, low = range.first, high = range.second)
        inOrder(root = newRoot, tag = "after deleteNodesOutsideRange()")

    }

    fun deleteNodesOutsideRangeHelper(root: Node?, low: Int, high: Int): Node? {
        if (root == null) {
            return null
        }

        if (root.data in low..high) {
            root.left = deleteNodesOutsideRangeHelper(root = root.left, low, high)
            root.right = deleteNodesOutsideRangeHelper(root = root.right, low, high)
            return root
        } else if (root.data > high) {
            return deleteNodesOutsideRangeHelper(root = root.left, low, high)
        } else {//if(root.data < low){
            return deleteNodesOutsideRangeHelper(root = root.right, low, high)
        }
    }

    fun maxSumPathWrapper() {
        val root = sampleBST()
        inOrder(root, "maxSumPathWrapper()")
        val maxSumPath = AtomicInteger(Int.MIN_VALUE)
        val maxSumPathValue = maxSumPath(root = root, maxSumPath = maxSumPath)
        Log.e(LOG_TAG, "Inside maxSumPathWrapper(), maxSumPath: ${maxSumPath.get()}")
    }

    fun maxSumPath(root: Node?, maxSumPath: AtomicInteger): Int {
        if (root == null) {
            return 0
        }
        val left = maxSumPath(root.left, maxSumPath)
        val right = maxSumPath(root.right, maxSumPath)

        val currentSumPath = left + right + root.data
        Log.e(
            LOG_TAG,
            "Inside maxSumPath(), root: ${root.data}, left: ${left}, right: ${right}, currentSumPath: $currentSumPath, maxSumPath: ${maxSumPath.get()} "
        )
        maxSumPath.set(max(maxSumPath.get(), currentSumPath))

        return root.data + max(left, right)
    }

    fun extractLeavesWrapper() {
        val root = sampleBST()
        extractLeaves(root)
    }


    //Extract leaves of a binary tree into a doubly-linked list
    // creating extra space for DLL
    fun extractLeaves(root: Node) {

        val dllRoot = LinkedList.DLLNodeWrapper(node = null)
        reverseInOrder(root = root, dllRoot = dllRoot)
        Log.e(LOG_TAG, "Inside extractLeaves(), printing DLL: ")
        var temp = dllRoot.node
        while (temp != null) {
            Log.e(LOG_TAG, "Inside extractLeaves(), printing DLL: ${temp.data}")
            temp = temp.next
        }
    }

    fun reverseInOrder(root: Node?, dllRoot: LinkedList.DLLNodeWrapper) {
        if (root == null)
            return

        if (root.left == null && root.right == null) {
            LinkedList.DLLNode(data = root.data).apply {
                if (dllRoot.node != null) {
                    this.next = dllRoot.node
                    dllRoot.node?.prev = this
                }
                dllRoot.node = this
            }
            return
        }

        reverseInOrder(root.right, dllRoot)
        reverseInOrder(root.left, dllRoot)
    }

    fun checkIsMinHeapWrapper() {
        val rootOfHeap = createMaxHeap()
        val nodesCount = countNodes(rootOfHeap)
        val isComplete = checkIsComplete(nodesCount, rootOfHeap, 0)
        val isMinHeap = isComplete && isMinHeap(rootOfHeap)
        Log.e(
            LOG_TAG,
            "Inside checkIsMinHeapWrapper(), isComplete: ${isComplete}, isMinHeap: $isMinHeap"
        )

        val rootOfHeap2 = createMinHeap()
        val nodesCount2 = countNodes(rootOfHeap2)
        val isComplete2 = checkIsComplete(nodesCount2, rootOfHeap2, 0)
        val isMinHeap2 = isComplete2 && isMinHeap(rootOfHeap2)
        Log.e(
            LOG_TAG,
            "Inside checkIsMinHeapWrapper(), isComplete: ${isComplete2}, isMinHeap: $isMinHeap2"
        )
    }

    fun countNodes(root: Node?): Int {
        if (root == null)
            return 0

        return 1 + countNodes(root.left) + countNodes(root.right)
    }

    fun checkIsComplete(totalNode: Int, root: Node?, index: Int): Boolean {
        if (root == null)
            return true

        if (index >= totalNode)
            return false

        return checkIsComplete(
            totalNode = totalNode,
            root = root.left,
            index = 2 * index + 1
        ) && checkIsComplete(
            totalNode = totalNode,
            root = root.right,
            index = 2 * index + 2
        )
    }

    fun isMinHeap(root: Node?): Boolean {
        Log.e(
            LOG_TAG,
            "Inside isMinHeap(), root: ${root?.data}, left: ${root?.left?.data}, right: ${root?.right?.data}"
        )
        if (root == null)
            return true

        root.left?.let { left ->
            if (left.data < root.data)
                return false
            if (!isMinHeap(root.left))
                return false
        }

        root.right?.let { right ->
            if (right.data < root.data)
                return false
            if (!isMinHeap(root.right))
                return false
        }
        return true
    }

    fun fixBSTWrapper() {
        val root = sampleBTWithOneIssue()
        inOrder(root, "fixBSTWrapper() before fixing")
        val prev = NodeWrapperOnlyNode(null)
        val firstNode = NodeWrapperOnlyNode(null)
        val secondNode = NodeWrapperOnlyNode(null)
        fixBT(root = root, prev = prev, firstNode = firstNode, secondNode = secondNode)
        val temp = firstNode.node?.data
        firstNode.node?.data = secondNode.node?.data!!
        if (temp != null) {
            secondNode.node?.data = temp
        }
        inOrder(root, "fixBSTWrapper() after fixing")

    }

    //Fix a binary tree that is only one swap away from becoming a BST
    fun fixBT(
        root: Node?,
        prev: NodeWrapperOnlyNode,
        firstNode: NodeWrapperOnlyNode,
        secondNode: NodeWrapperOnlyNode
    ) {
        if (root == null)
            return

        fixBT(
            root = root.left,
            prev = prev,
            firstNode = firstNode,
            secondNode = secondNode
        )
        if (prev.node != null) {
            if (prev.node!!.data > root.data) {
                if (firstNode.node == null) {
                    firstNode.node = prev.node
                } else {
                    secondNode.node = root
                }
            }
        }
        prev.node = root
        fixBT(
            root = root.right,
            prev = prev,
            firstNode = firstNode,
            secondNode = secondNode
        )
    }

    data class HuffmanNode(
        val data: Char?,
        val freq: Int,
        var left: HuffmanNode?,
        var right: HuffmanNode?
    )

    fun buildHuffmanTreeWrapper() {
        val inputString = "Huffman coding is a data compression algorithm."
        val freqMap = mutableMapOf<Char, Int>()
        inputString.forEach {
            freqMap[it] = (freqMap[it] ?: 0) + 1
            Log.e(LOG_TAG, "buildHuffmanTreeWrapper(), char: $it, freq: ${freqMap[it]}")
        }
        val rootNode = buildHuffmanTree(freqMap = freqMap)
        val map = mutableMapOf<Char, String>()
        huffmanEncoding(root = rootNode, map = map, input = "")
        Log.e(LOG_TAG, "buildHuffmanTreeWrapper(), map: $map")

        //Encoding the input string
        var encodedString = StringBuilder()
        inputString.forEach {
            encodedString.append(map[it])
        }
        Log.e(LOG_TAG, "buildHuffmanTreeWrapper(), encodedString: $encodedString")
    }

    fun buildHuffmanTree(freqMap: Map<Char, Int>): HuffmanNode {

        val priorityQueue = PriorityQueue<HuffmanNode>(compareBy { it.freq })

        freqMap.forEach { key, value ->
            priorityQueue.add(HuffmanNode(data = key, freq = value, left = null, right = null))
        }

        Log.e(
            LOG_TAG,
            "Inside buildHuffmanTree(), priorityQueue.size: ${priorityQueue.size}, priorityQueue: $priorityQueue"
        )

        while (priorityQueue.size > 1) {
            val left = priorityQueue.poll()
            val right = priorityQueue.poll()
            Log.e(
                LOG_TAG,
                "Inside buildHuffmanTree(), building huffman tree, priorityQueue.size: ${priorityQueue.size}, left: ${left}, freq: ${left.freq}, right: ${right}, freq: ${right.freq}"
            )

            val newRoot =
                HuffmanNode(data = null, freq = left.freq + right.freq, left = left, right = right)
            priorityQueue.add(newRoot)
        }
        val node = priorityQueue.poll()
        Log.e(
            LOG_TAG,
            "buildHuffmanTree(), priorityQueue.size: ${priorityQueue.size}, node: ${node.data}, freq: ${node.freq}"
        )
        huffmanInorder(node)
        return node
    }

    fun huffmanInorder(root: HuffmanNode?) {
        if (root == null)
            return

        huffmanInorder(root.left)
        Log.e(LOG_TAG, "huffmanInorder(), char: ${root.data}, freq: ${root.freq}")
        huffmanInorder(root.right)
    }

    fun huffmanDecoding() {

    }

    fun huffmanEncoding(input: String, root: HuffmanNode?, map: MutableMap<Char, String>) {

        if (root == null)
            return

        if (root.left == null && root.right == null) {
            map[root.data!!] = if (input.isNotEmpty()) input else "1"
        }
        Log.e(
            LOG_TAG,
            "Inside huffmanEncoding(), root: ${root.data}, input: $input, freq: ${root.freq}"
        )

        huffmanEncoding(root = root.left, map = map, input = input + "0")
        huffmanEncoding(root = root.right, map = map, input = input + "1")
    }

    //Check if a binary tree is symmetric or not
    fun checkIfSymmetric() {
        val root = sampleBST()
        val isSymmetric = checkIfSymmetricHelper(root1 = root.left, root2 = root.right)
        Log.e(LOG_TAG, "Inside checkIfSymmetric(), isSymmetric: $isSymmetric")

        val root2 = sampleBSTSymmetric()
        val isSymmetric2 = checkIfSymmetricHelper(root1 = root2.left, root2 = root2.right)
        Log.e(LOG_TAG, "Inside checkIfSymmetric(), isSymmetric2: $isSymmetric2")
    }

    fun checkIfSymmetricHelper(root1: Node?, root2: Node?): Boolean {
        if (root1 == null && root2 == null) {
            return true
        }

        return (root1 != null && root2 != null) && checkIfSymmetricHelper(
            root1 = root1.left,
            root2 = root2.right
        ) && checkIfSymmetricHelper(root1 = root1.right, root2 = root2.left)
    }

    fun boundaryTraversal() {
        val root = sampleBSTSymmetric()
        boundaryTraversalLeftView(root = root)
        boundaryTraversalLeafNodes(root = root)
        boundaryTraversalRightNodes(root = root)
    }

    fun boundaryTraversalLeftView(root: Node?) {
        if (root == null || (root.left == null && root.right == null)) {
            return
        }

        Log.e(LOG_TAG, "Inside boundaryTraversalLeftView(), root: ${root.data}")
        if (root.left != null) {
            boundaryTraversalLeftView(root.left)
        } else {
            boundaryTraversalLeftView(root.right)
        }
    }

    fun boundaryTraversalLeafNodes(root: Node?) {
        if (root == null) {
            return
        } else if (root.left == null && root.right == null) {
            Log.e(LOG_TAG, "Inside boundaryTraversalLeafNodes(), root: ${root.data}")
            return
        }
        boundaryTraversalLeafNodes(root.left)
        boundaryTraversalLeafNodes(root.right)
    }

    fun boundaryTraversalRightNodes(root: Node?) {
        if (root == null || (root.left == null && root.right == null)) {
            return
        }
        if (root.right != null) {
            boundaryTraversalRightNodes(root.right)
        } else {
            boundaryTraversalRightNodes(root.left)
        }

        Log.e(LOG_TAG, "Inside boundaryTraversalRightNodes(), root: ${root.data}")
    }

    fun findNodesAncestors() {
        val root = sampleBST()
        val mutableList = mutableListOf<Int>()
        val data = 22
        val foundNode =
            findNodesAncestorsHelper(root = root, mutableList = mutableList, data = data)
        Log.e(
            LOG_TAG,
            "Inside findNodesAncestors(), foundNode: $foundNode, mutableList: $mutableList"
        )
    }

    fun findNodesAncestorsHelper(root: Node?, mutableList: MutableList<Int>, data: Int): Boolean {
        if (root == null) {
            return false
        }

        if (root.data == data) {
            return true
        } else if (data < root.data && findNodesAncestorsHelper(
                root = root.left,
                mutableList = mutableList,
                data = data
            )
        ) {
            mutableList.add(root.data)
        } else if (data > root.data && findNodesAncestorsHelper(
                root = root.right,
                mutableList = mutableList,
                data = data
            )
        ) {
            mutableList.add(root.data)
        } else {
            return false
        }
        return true
    }

    fun printReverseLevelOrder() {
        val root = sampleBST()

        val queue = ArrayDeque<Node>()
        queue.add(root)
        printReverseLevelOrderHelper(queue = queue)
    }

    fun printReverseLevelOrderHelper(queue: ArrayDeque<Node>) {

        if (queue.isEmpty()) {
            return
        }
        var nodesCount = queue.size
        val levelNodesList = mutableListOf<Int>()
        while (nodesCount > 0) {
            val current = queue.removeLast()
            levelNodesList.add(current.data)
            Log.e(LOG_TAG, "Inside printReverseLevelOrderHelper(), visiting: ${current.data}")
            if (current.left != null) {
                queue.addFirst(current.left!!)
            }
            if (current.right != null) {
                queue.addFirst(current.right!!)
            }
            nodesCount--
        }
        printReverseLevelOrderHelper(queue = queue)
        Log.e(
            LOG_TAG,
            "Inside printReverseLevelOrderHelper(), levelNodesList: ${levelNodesList.joinToString()}"
        )
    }
}