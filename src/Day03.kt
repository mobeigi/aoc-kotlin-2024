/*
 * Day 03.
 */
fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val components = line.split("mul(", ")")
            components.sumOf { component ->
                val numbers = component.split(",")
                if (numbers.size == 2 && numbers[0].isNotEmpty() && numbers[0].length <= 3 && numbers[0].all { it.isDigit() } &&
                    numbers[1].isNotEmpty() && numbers[1].length <= 3 && numbers[1].all { it.isDigit() }) {
                    numbers[0].toInt() * numbers[1].toInt()
                } else {
                    0
                }
            }
        }
    }

    fun part2(input: List<String>): Int {
        var enabled = true
        val newInputs = input.map { line ->
            var startIndex = 0
            var index = 0
            var newLine = ""
            line.forEach { _ ->
                if (index + 4 <= line.length && line.substring(index, index + 4) == "do()") {
                    if (!enabled) {
                        enabled = true
                        startIndex = index
                    }
                    index += 4
                } else if (index + 7 <= line.length && line.substring(index, index + 7) == "don't()") {
                    if (enabled) {
                        enabled = false
                        newLine += line.substring(startIndex, index)
                    }
                    index += 7
                }
                index += 1
            }
            if (enabled) {
                newLine += line.substring(startIndex)
            }
            newLine
        }
        return part1(newInputs)
    }

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
