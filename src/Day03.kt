/**
 * Day 03
 */
class Day03 {
    companion object {
        const val NUMBER_MAX_LENGTH = 3
        const val DO_COMMAND = "do()"
        const val DONT_COMMAND = "don't()"
    }

    /**
     * Use regexp to find each mul() function, and sum the inputs together.
     */
    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val components = line.split("mul(", ")")
            components.sumOf { component ->
                val numbers = component.split(",")
                if (numbers.size == 2 && numbers[0].isNotEmpty() && numbers[0].length <= NUMBER_MAX_LENGTH && numbers[0].all { it.isDigit() } &&
                    numbers[1].isNotEmpty() && numbers[1].length <= NUMBER_MAX_LENGTH && numbers[1].all { it.isDigit() }) {
                    numbers[0].toInt() * numbers[1].toInt()
                } else {
                    0
                }
            }
        }
    }

    /**
     * Preprocess input by disregarding all don't() sections in each line.
     * Then delegate to part 1 solution.
     */
    fun part2(input: List<String>): Int {
        var enabled = true
        val newInputs = input.map { line ->
            var startIndex = 0
            var index = 0
            var newLine = ""
            line.forEach { _ ->
                if (line.regionMatches(index, DO_COMMAND, 0, DO_COMMAND.length)) {
                    if (!enabled) {
                        enabled = true
                        startIndex = index
                    }
                    index += DO_COMMAND.length
                } else if (line.regionMatches(index, DONT_COMMAND, 0, DONT_COMMAND.length)) {
                    if (enabled) {
                        enabled = false
                        newLine += line.substring(startIndex, index)
                    }
                    index += DONT_COMMAND.length
                }
                index += 1
            }
            if (enabled) {
                // Final append
                newLine += line.substring(startIndex)
            }
            newLine
        }

        // Delegate to part 1
        return part1(newInputs)
    }
}

fun main() {
    val input = readInput("Day03")
    val day03 = Day03()
    day03.part1(input).println()
    day03.part2(input).println()
}
