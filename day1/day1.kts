import java.io.File
import kotlin.*

val numArray = File("input.txt").readLines().map { it.toInt() }.toTypedArray()

// Part 1
fun part1(array: Array<Int>, sumEquals: Int): Int {
    numArray.forEachIndexed { idx, elem ->
        (idx+1 until numArray.size).forEach { a ->
            if (elem + numArray[a] == sumEquals) {
                println("$elem * ${numArray[a]} = ${elem * numArray[a]}")
                return elem * numArray[a]
            }
        }
    }
    return -1
}

// Part 2
fun part2(array: Array<Int>, sumEquals: Int): Int {
    numArray.forEachIndexed { idx, elem ->
        (idx+1 until numArray.size).forEach { a ->
            (a+1 until numArray.size).forEach { b ->
                if (elem + numArray[a] + numArray[b] == sumEquals) {
                    println("$elem + ${numArray[a]} * ${numArray[b]} = ${elem * numArray[a] * numArray[b]}")
                    return elem * numArray[a] * numArray[b]
                }
            }
        }
    }
    return -1
}

println("Part 1 -> ${part1(numArray, 2020)}")
println("Part 2 -> ${part2(numArray, 2020)}")

