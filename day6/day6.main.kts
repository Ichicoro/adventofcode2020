#!/usr/bin/env kotlin

import java.io.File
import kotlin.*

val start = System.currentTimeMillis()

File("input.txt").readText().let {
    Regex("^.+(?:\\r?\\n(?!\\r?\\n).*)*", RegexOption.MULTILINE)
        .findAll(it)
        .toList()
}.map{ it.value }.let { blocks ->
    blocks.map {
        it.replace("\n", "")
            .toCharArray()
            .distinct()
            .joinToString("")
    }.map { it.length }.sum().let { println("Part 1: $it") }
    blocks.map { block ->
        block.split("\n").map { it.toCharArray().toSet() }.reduce { acc, item ->
            acc intersect item
        }
    }.map { it.size }.sum().let { println("Part 2: $it") }
}

println("Elapsed: ${System.currentTimeMillis() - start}ms")
