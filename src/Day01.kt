import kotlin.math.abs

/**
 * Day 01
 */
class Day01 {

    private fun parseInput(input: List<String>): Pair<List<Int>, List<Int>> {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()

        input.map { line ->
            val first = line.take(5)
            left.add(first.toInt())
            val second = line.takeLast(5)
            right.add(second.toInt())
        }
        return left to right
    }

    fun part1(input: List<String>): Int {
        val (left, right) = parseInput(input)
        check(left.size == right.size)

        // 2x O(n log n)
        val sortedLeft = left.sorted()
        val sortedRight = right.sorted()

        return left.indices.sumOf {
            abs(sortedLeft[it] - sortedRight[it])
        }
    }

    fun part2(input: List<String>): Int {
        val (left, right) = parseInput(input)
        check(left.size == right.size)

        // Create frequency map for right list
        val rightFreqMap = right.groupingBy { it }.eachCount()

        // Compute total similarity
        return left.sumOf {
            it * rightFreqMap.getOrDefault(it, 0)
        }
    }
}

fun main() {
    val input = readInput("Day01")
    val day01 = Day01()
    day01.part1(input).println()
    day01.part2(input).println()
}
