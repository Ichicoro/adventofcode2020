#!/usr/bin/env kotlin

import java.io.File
import kotlin.*

val start = System.currentTimeMillis()

val BAG_COLOR = "shiny gold"

fun part1(bagMap: Map<String, Map<String, Int>>, bagColor: String? = null): Int {
    return when (bagColor) {
        null -> bagMap.keys.map { color -> part1(bagMap, color) }.filter { it != 0 }.count() - 1
        BAG_COLOR -> 1
        else -> bagMap[bagColor]!!.keys.map { color -> part1(bagMap, color) }.maxOrNull() ?: 0
    }
}

fun part2(bagMap: Map<String, Map<String, Int>>, bagColor: String? = BAG_COLOR, amount: Int = 1): Int {
    return if (bagMap[bagColor]!!.keys.isEmpty()) amount

    else bagMap[bagColor]!!.keys.fold(1) { acc, color ->
        acc + part2(bagMap, color, bagMap[bagColor]!![color]!!)
    } * (amount) + if (bagColor == BAG_COLOR) -1 else 0
}

File("input.txt").readLines().fold(mapOf<String, Map<String, Int>>()) { acc, line ->
    Regex("(.+) bags contain (.+).")
        .find(line).let { match ->
            acc + mapOf(
                match!!.groupValues[1] to Regex("""(\d+) ([\w ]+) (?:bags|bag)""")
                    .findAll(match.groupValues[2])
                    .toList()
                    .map { it.groupValues }
                    .drop(0)
                    .fold(mapOf()) { acc, item ->
                        acc + mapOf(item[2] to item[1].toInt())
                    }
            )
        }
}.let { bagMap ->
    bagMap.forEach { println(it) }
    print("\n")
    println("Part 1: ${part1(bagMap)}")
    println("Part 2: ${part2(bagMap)}")
}


println("Elapsed: ${System.currentTimeMillis() - start}ms")
