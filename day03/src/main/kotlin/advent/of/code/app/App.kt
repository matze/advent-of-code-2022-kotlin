package advent.of.code.app.day03

import java.io.File;

fun score(c: Char): Int {
    if (c >= 'a' && c <= 'z') {
        return c - 'a' + 1;
    }

    return c - 'A' + 27;
}

fun priorityPerLine(line: String): Int {
    val a = line.take(line.length / 2).toHashSet();
    val b = line.takeLast(line.length / 2).toHashSet();
    return score(a.intersect(b).first());
}

fun priorityPerGroup(group: List<String>): Int {
    val a = group[0].toHashSet();
    val b = group[1].toHashSet();
    val c = group[2].toHashSet();
    return score(a.intersect(b.intersect(c)).first());
}

fun main() {
    val sum1 = File("day03-first.txt")
        .bufferedReader()
        .lineSequence()
        .map({ priorityPerLine(it) })
        .sum();

    println("part 1: ${sum1}");

    val sum2 = File("day03-first.txt")
        .bufferedReader()
        .lineSequence()
        .chunked(3)
        .map({ priorityPerGroup(it) })
        .sum();

    println("part 2: ${sum2}");
}
