package advent.of.code.app.day06

import java.io.File

fun startMarker(s: String, size: Int): Int {
    for (it in s.windowed(size = size, step = 1).withIndex()) {
        if (it.value.toHashSet().size == size) {
            return it.index + size
        }
   }

    return 0
}

fun main() {
    val s = File("day06-input.txt").readText();
    println("Part 1: ${startMarker(s, 4)}")
    println("Part 2: ${startMarker(s, 14)}")
}
