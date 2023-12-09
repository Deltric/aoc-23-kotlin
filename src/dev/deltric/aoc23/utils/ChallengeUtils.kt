package dev.deltric.aoc23.utils

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun readDay(day: Int): List<String> {
    return Path("./input/day_$day.txt").readLines()
}