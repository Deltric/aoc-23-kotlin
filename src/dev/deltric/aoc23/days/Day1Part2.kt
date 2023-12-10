package dev.deltric.aoc23.days

object Day1Part2 : Day {

    override fun start(lines: List<String>) {
        val result = lines
            .map(::parseNumbers)
            .sumOf(::parseCalibration)
        println("Calibration Sum: $result")
    }

    /**
     * Parses all numbers from digit characters and digit words.
     *
     * My sanity was lost during this method.
     * I rewrote it so many times until I finally realized numbers could share letters
     * For example "eightwo", both can share the t :(
     *
     * @param line The calibration entry line
     * @return All digits in the calibration sequence
     */
    private fun parseNumbers(line: String): String {
        val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val sb = StringBuilder()

        line.forEachIndexed { index, c ->
            if (c.isDigit()) {
                sb.append(c.digitToIntOrNull())
                return@forEachIndexed
            }

            val subLine = line.substring(index)
            for (i in 1..9) {
                if (subLine.startsWith(numbers[i - 1])) {
                    sb.append(i)
                    break
                }
            }
        }
        return sb.toString()
    }

    /**
     * Takes the first and last digit to form the calibration entry.
     * @param line The calibration entry line
     * @return Combined calibration entry
     */
    private fun parseCalibration(line: String): Int {
        return "${line[0]}${line[line.length - 1]}".toInt()
    }

}