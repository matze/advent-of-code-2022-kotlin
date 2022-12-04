package advent.of.code.app.day04

import java.io.File;

class Range(val start: Int, val end: Int) {
    companion object {
        fun fromString(s: String): Range {
            val parts = s.split('-');
            return Range(parts.get(0).toInt(), parts.get(1).toInt());
        }
    }

    fun contains(other: Range): Boolean {
        return other.start >= start && other.end <= end;
    }

    fun overlaps(other: Range): Boolean {
        return (start >= other.start && start <= other.end) || (end >= other.start && end <= other.end);
    }
}

class Pair(val first: Range, val second: Range) {
    companion object {
        fun fromString(s: String): Pair {
            val parts = s.split(',');
            return Pair(Range.fromString(parts.get(0)), Range.fromString(parts.get(1)));
        }
    }

    fun fullyContained(): Boolean {
        return first.contains(second) || second.contains(first);
    }

    fun overlapped(): Boolean {
        return first.overlaps(second) || second.overlaps(first);
    }
}

fun main() {
    val sum1 = File("day04-input.txt")
        .bufferedReader()
        .lineSequence()
        .map({ Pair.fromString(it) })
        .filter({ it.fullyContained() })
        .count();

    println("Part 1: ${sum1}");

    val sum2 = File("day04-input.txt")
        .bufferedReader()
        .lineSequence()
        .map({ Pair.fromString(it) })
        .filter({ it.overlapped() })
        .count();

    println("Part 2: ${sum2}");
}
