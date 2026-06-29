package com.example.interviewprepration.ds_n_algo

import android.util.Log
import java.util.Stack

object ExpressionTree {

    val LOG_TAG = ExpressionTree::class.java.name

    fun driverFunction() {
        expressionTreeWrapper()
    }

    // converting expressions like (a+b)*c-d/y into expression tree

    data class Node(val data: Char, val left: Node? = null, val right: Node? = null)

    fun operatorPrecedence(operator: Char): Int {
        Log.e(LOG_TAG, "Inside expressionTreeWrapper -> operatorPrecedence(), operator: $operator")
        val operatorPrecedenceMap = mapOf(
            '+' to 1, '-' to 1,
            '*' to 2, '/' to 2,
            '^' to 3,
            '(' to 0
        )
        return operatorPrecedenceMap[operator]!!
    }

    fun expressionTreeWrapper() {
        val inputExpression = "(a+b)*c-d/y"
        val root = expressionTree(inputExpression)
        inOrder(root, "ExpressionTree")
    }

    fun inOrder(root: Node?, tag: String = "") {
        if (root == null)
            return

        inOrder(root.left)
        Log.e(LOG_TAG, "$tag, ExpressionTree inorder: ${root.data}")
        inOrder(root.right)
    }

    fun expressionTree(inputExpression: String): Node {
        val operandStack = Stack<Node>()
        val operatorStack = Stack<Char>()

        for (currentElement in inputExpression) {
            Log.e(LOG_TAG, "Inside expressionTree(), currentElement: $currentElement")
            Log.e(
                LOG_TAG,
                "Inside expressionTree(), inside loop, operatorStack: ${operatorStack.joinToString()}, operandStack: ${operandStack.joinToString()}"
            )
            if (currentElement == ')') {
                while (operatorStack.isNotEmpty() && operatorStack.peek() != '(' && operandStack.size >= 2) {

                    val nodeA = operandStack.pop()
                    val nodeB = operandStack.pop()

                    val node = Node(
                        data = operatorStack.pop(),
                        left = nodeB,
                        right = nodeA
                    )
                    operandStack.push(node)
                }
            } else if (currentElement in "+-/*") {
                while (operatorStack.isNotEmpty() &&
                    operatorPrecedence(operatorStack.peek()) >= operatorPrecedence(currentElement)
                ) {
                    Log.e(
                        LOG_TAG,
                        "Inside expressionTree(), inside while loop when element is operator: $currentElement"
                    )
                    val nodeA = operandStack.pop()
                    val nodeB = operandStack.pop()
                    val operator = operatorStack.pop()
                    val node = Node(
                        data = operator,
                        left = nodeB,
                        right = nodeA
                    )
                    operandStack.push(node)
                }
                operatorStack.push(currentElement)
            } else if (currentElement in 'a'..'z') {
                operandStack.push(Node(data = currentElement))
            } else {
                if (currentElement == '(') {
                    operatorStack.push(currentElement)
                }
            }
        }

        while (operatorStack.isNotEmpty() && operandStack.size >= 2) {
            val nodeA = operandStack.pop()
            val nodeB = operandStack.pop()
            val node = Node(data = operatorStack.pop(), left = nodeB, right = nodeA)
            operandStack.push(node)
            Log.e(
                LOG_TAG,
                "Inside expressionTree() last while loop, operandStack size: ${operandStack.size} operandStack: ${operandStack.joinToString()}, operatorStack size: ${operatorStack.size}: ${operatorStack.joinToString()}"
            )
        }
        Log.e(
            LOG_TAG,
            "Inside expressionTree() at the end, operandStack size: ${operandStack.size} operandStack: ${operandStack.joinToString()}, operatorStack size: ${operatorStack.size}: ${operatorStack.joinToString()}"
        )
        return operandStack.pop()
    }
}