package dev.deltric.aoc23.days

object Day1 : Day {

    override fun start(lines: List<String>) {
        val result = lines.sumOf(::parseCalibration)
        println("Calibration Sum: $result")
    }

    /**
     * Parses the calibration entry by filtering out all non-digits.
     * Then combines the first and last digit to form the calibration entry.
     * @param line The calibration entry line
     * @return Combined calibration entry
     */
    fun parseCalibration(line: String): Int {
        return line.filter(Char::isDigit)
            .let { "${it[0]}${it[it.length - 1]}".toInt() }
    }

}