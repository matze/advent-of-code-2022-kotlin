package advent.of.code.app.day01

import java.io.File;


fun main() {
    var sums = mutableListOf<Int>();
    var sum = 0;

    File("day01-part1.txt").forEachLine {
        if (it == "") {
            sums.add(sum);
            sum = 0;
        }
        else {
            sum += it.toInt();
        }
    }

    val sorted_sums = sums.sortedDescending();
    println("${sorted_sums[0]}");
    println("${sorted_sums[0] + sorted_sums[1] + sorted_sums[2]}");
}
