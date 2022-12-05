package advent.of.code.app.day05

import java.io.File;
import kotlin.collections.ArrayDeque;
import kotlin.math.ceil;
import kotlin.text.Regex;

val CHAR_REGEX = Regex("[[([A-Z])]]");
val MOVE_REGEX = Regex("""move (\d+) from (\d) to (\d)""")

fun partOne() {
    val lines = File("day05-input.txt")
        .bufferedReader()
        .readLines()

    val stacks = mutableListOf<ArrayDeque<Char>>()
    val numStacks = ceil(lines[0].length / 4.0).toInt()

    for (i in 1..numStacks) {
        stacks.add(ArrayDeque<Char>())
    }

    for (line in lines) {
        for (match in CHAR_REGEX.findAll(line)) {
            val stack = match.range.start / 4
            val content = match.groupValues[0]
            stacks[stack].add(content[0])
        }

        for (match in MOVE_REGEX.findAll(line)) {
            val num = match.groupValues[1].toInt()
            val start = match.groupValues[2].toInt() - 1
            val target = match.groupValues[3].toInt() - 1

            for (_unused in 1..num) {
                stacks[target].add(0, stacks[start].removeFirst())
            }
        }
    }

    println("Part 1: ${stacks.map { it[0] }.joinToString(separator = "")}")
}

fun partTwo() {
    val lines = File("day05-input.txt")
        .bufferedReader()
        .readLines()

    val stacks = mutableListOf<ArrayDeque<Char>>()
    val numStacks = ceil(lines[0].length / 4.0).toInt()

    for (i in 1..numStacks) {
        stacks.add(ArrayDeque<Char>())
    }

    for (line in lines) {
        for (match in CHAR_REGEX.findAll(line)) {
            val stack = match.range.start / 4
            val content = match.groupValues[0]
            stacks[stack].add(content[0])
        }

        for (match in MOVE_REGEX.findAll(line)) {
            val num = match.groupValues[1].toInt()
            val start = match.groupValues[2].toInt() - 1
            val target = match.groupValues[3].toInt() - 1

            val removed = stacks[start].subList(0, num);
            stacks[target].addAll(0, removed);
            removed.clear();
        }
    }

    println("Part 2: ${stacks.map { it[0] }.joinToString(separator = "")}")
}

fun main() {
    partOne();
    partTwo();
}
