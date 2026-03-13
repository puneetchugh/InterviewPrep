package com.example.interviewprepration.ds_n_algo

import android.util.Log

data class Node(val distance: Int, val position: Int)

object SnakesNLadders {

    val LOG_TAG = SnakesNLadders::class.java.name

    fun driverFunction() {
        val size = 30
        val ladders = listOf(Pair(3, 22), Pair(4, 8), Pair(11, 26), Pair(20, 21))
        val snakes = listOf(
            Pair(27, 1),
            Pair(21, 9),
            Pair(17, 4),
            Pair(19, 7)
        )

        val shortestPath = shortestPath(size, snakes, ladders, Array<Boolean>(30) { false })
        Log.e(LOG_TAG, "Shortest path: $shortestPath")
    }

    // BFS for shortest path
    fun shortestPath(
        boardSize: Int,
        snakes: List<Pair<Int, Int>>,
        ladders: List<Pair<Int, Int>>,
        visited: Array<Boolean>
    ): Int {

        val board = Array(boardSize) { -1 }

        for (ladder in ladders) {
            board[ladder.first - 1] = ladder.second - 1
        }

        for (snake in snakes) {
            board[snake.first - 1] = snake.second - 1
        }

        val queue = ArrayDeque<Node>()
        queue.add(Node(position = 0, distance = 0))

        while (queue.isNotEmpty()) {

            val visiting = queue.removeFirst()
            Log.e(
                LOG_TAG,
                "Shortest path: visiting, position: ${visiting.position}, distance: ${visiting.distance}"
            )
            if (visiting.position == boardSize - 1) {
                return visiting.distance
            }

            if (visited[visiting.position]) {
                continue
            }
            visited[visiting.position] = true

            for (i in 1..6) {


                if (visiting.position + i >= boardSize) {
                    continue
                }

                val nextPosition = if (board[visiting.position + i] != -1) {
                    board[visiting.position + i]
                } else {
                    visiting.position + i
                }
                val node = Node(position = nextPosition, distance = visiting.distance + 1)
                queue.add(node)
            }
        }
        return -1
    }
}