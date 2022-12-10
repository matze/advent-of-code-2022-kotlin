package advent.of.code.app.day10

import java.io.File
import kotlin.text.Regex;

val ADDX_PATTERN = Regex("addx (-?\\d+)");

fun updateCrt(col: Int, x: Int): Int {
    if (col == x || col == x - 1 || col == x + 1) {
        print("#")
    }
    else {
        print(".")
    }

    if (col + 1 == 40) {
        println("")
        return 0
    }

    return col + 1
}

fun main() {
    val lines = File("day10-input.txt")
        .bufferedReader()
        .readLines()

    var cycle = 1
    var x = 1
    var strength = 0
    var col = 0

    for (line in lines) {
        col = updateCrt(col, x)

        if (line == "noop") {
            cycle += 1
        }
        else {
            cycle += 1

            col = updateCrt(col, x)

            if ((cycle - 20) % 40 == 0) {
                strength += x * cycle
            }

            x += ADDX_PATTERN.matchEntire(line)!!.groupValues[1].toInt()
            cycle += 1
        }

        if ((cycle - 20) % 40 == 0) {
            strength += x * cycle
        }
    }

    println("strength: ${strength}")
}
