package dev.deltric.aoc23.days

object Day2Part2 : Day {

    override fun start(lines: List<String>) {
        val sum = lines.map(::parseGame)
            .sumOf { it.getPower() }
        println("Game Sum: $sum")
    }

    /**
     * Creates a game from the separated format
     * Retrieves the ID and groups rounds by cubes and totals.
     * @param line The separated game line
     * @return The [Game]
     */
    private fun parseGame(line: String): Game {
        val parts = line.split(": ")
        val id = parts[0].split(" ")[1].toInt()
        val game = Game(id)

        for (subset in parts[1].split(";")) {
            val round = Round()
            for (cube in subset.split(", ")) {
                val element = cube.trim().split(" ")
                val color = Cube.getFromString(element[1])
                round.addCubes(color, element[0].toInt())
            }
            game.rounds.add(round)
        }
        return game
    }

    enum class Cube {
        RED,
        GREEN,
        BLUE;

        companion object {
            /**
             * Gets a cube by the color name
             * @param name The name of the color
             * @return The [Cube]
             */
            fun getFromString(name: String): Cube {
                return entries.first { it.name.equals(name, ignoreCase = true) }
            }
        }
    }

    data class Game(
        val id: Int,
        val rounds: MutableList<Round> = mutableListOf()
    ) {
        /**
         * Looks through all rounds in the game to find the minimum amount of cubes
         * Multiplies the minimum amounts all against each other to gauge power
         * @return The game's power
         */
        fun getPower(): Int {
            val minimum = mutableMapOf<Cube, Int>()

            this.rounds.forEach { round ->
                Cube.entries.forEach { cube ->
                    val value = round.getCubes(cube)
                    if (value > minimum.getOrDefault(cube, 0)) {
                        minimum[cube] = value
                    }
                }
            }

            return minimum.values.reduce { a, b -> a * b }
        }
    }

    data class Round(
        val cubes: MutableMap<Cube, Int> = mutableMapOf()
    ) {
        /**
         * Adds an amount of [Cube]s to the round
         * @param cube The cube to add
         * @param amount The amount of cubes
         */
        fun addCubes(cube: Cube, amount: Int) {
            this.cubes[cube] = amount
        }

        /**
         * Gets the amount of a [Cube]
         * @param cube The type of cube
         * @return The cube amount
         */
        fun getCubes(cube: Cube): Int {
            return this.cubes.getOrDefault(cube, 0)
        }
    }
}