package dev.deltric.aoc23

import dev.deltric.aoc23.days.Day1
import java.lang.Exception
import kotlin.io.path.Path
import kotlin.io.path.readLines

private val days = mapOf(
    1 to mapOf(1 to Pair(Day1, "day_1"))
)

fun main(args: Array<String>) {
    val day = args.getOrNull(0)?.toIntOrNull() ?: throw Exception("Please specify a numerical day arg to run.")
    val part = args.getOrNull(1)?.toIntOrNull() ?: 1

    startDay(day, part)
}

fun startDay(day: Int, part: Int = 1) {
    val challengeDay = days[day] ?: throw NullPointerException("Day $day does not exist! Are you sure this day has been completed or registered?")
    val challengePart = challengeDay[part] ?: throw NullPointerException("Day $day Part $part does not exist! Are you sure this part has been completed or registered?")

    val lines = Path("./input/${challengePart.second}.txt").readLines()
    challengePart.first.start(lines)
}