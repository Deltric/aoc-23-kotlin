package dev.deltric.aoc23.days

object Day2 : Day {

    override fun start(lines: List<String>) {
        val expected = mapOf(
            Cube.RED to 12,
            Cube.GREEN to 13,
            Cube.BLUE to 14
        )

        val sum = lines.map(::parseGame)
            .filter { it.isPossible(expected) }
            .sumOf { it.id }
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
             * Gets a Cube by the color name
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
         * Checks each round for if it exceeds the expectation
         * @param expectation Expected max values of [Cube]s
         * @return true if this game was possible, false otherwise.
         */
        fun isPossible(expectation: Map<Cube, Int>): Boolean {
            for (cube in expectation.keys) {
                val expected = expectation[cube] ?: 0

                for (round in this.rounds) {
                    if (round.cubes.getOrDefault(cube, 0) > expected) {
                        return false
                    }
                }
            }
            return true
        }
    }

    data class Round(
        val cubes: MutableMap<Cube, Int> = mutableMapOf()
    ) {
        /**
         * Adds an amount of [Cube]s to the round
         * @param cube The Cube to add
         * @param amount The amount of cubes
         */
        fun addCubes(cube: Cube, amount: Int) {
            this.cubes[cube] = amount
        }
    }
}