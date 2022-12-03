package advent.of.code.app.day02

import java.io.File;

enum class Choice {
    ROCK,
    PAPER,
    SCISSOR,
}

fun scoreFromChoice(c: Choice): Int {
    when (c) {
        Choice.ROCK -> return 1
        Choice.PAPER -> return 2
        Choice.SCISSOR -> return 3
    }
}

fun choiceFromInt(n: Int): Choice {
    when (n) {
        0 -> return Choice.ROCK
        1 -> return Choice.PAPER
        2 -> return Choice.SCISSOR
        else -> return Choice.ROCK
    }
}

class Game(val other: Choice, val me: Choice) {
    fun score(): Int {
        // Draw
        if (other == me) {
            return scoreFromChoice(me) + 3;
        }

        // Win
        if ((other == Choice.ROCK && me == Choice.PAPER) ||
            (other == Choice.PAPER && me == Choice.SCISSOR) ||
            (other == Choice.SCISSOR && me == Choice.ROCK)) {
            return scoreFromChoice(me) + 6;
        }

        // Loss
        return scoreFromChoice(me);
    }
}

fun toScorePart1(s: String): Int {
    val split = s.split(' ');
    val other = choiceFromInt(split[0].first().code - 'A'.code);
    val me = choiceFromInt(split[1].first().code - 'X'.code);
    val game = Game(other, me);
    return game.score();
}

fun toScorePart2(s: String): Int {
    val split = s.split(' ');
    val other = choiceFromInt(split[0].first().code - 'A'.code);
    val outcome = split[1].first();

    when (outcome) {
        // Lose
        'X' -> when (other) {
            Choice.ROCK -> return scoreFromChoice(Choice.SCISSOR)
            Choice.PAPER -> return scoreFromChoice(Choice.ROCK)
            Choice.SCISSOR -> return scoreFromChoice(Choice.PAPER)
        }
        // Draw
        'Y' -> return scoreFromChoice(other) + 3
        // Win
        'Z' -> when (other) {
            Choice.ROCK -> return scoreFromChoice(Choice.PAPER) + 6
            Choice.PAPER -> return scoreFromChoice(Choice.SCISSOR) + 6
            Choice.SCISSOR -> return scoreFromChoice(Choice.ROCK) + 6
        }
    }

    return 0;
}

fun main() {
    val part1 = File("day02-input.txt")
        .bufferedReader()
        .lineSequence()
        .map({ toScorePart1(it) })
        .sum();

    println("part 1: ${part1}");

    val part2 = File("day02-input.txt")
        .bufferedReader()
        .lineSequence()
        .map({ toScorePart2(it) })
        .sum();

    println("part 2: ${part2}");
}
