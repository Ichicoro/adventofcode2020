#!/usr/bin/env kotlin

import java.io.File
import kotlin.*

data class PasswordLine(val min: Int, val max: Int, val char: Char, val str: String)

val reg = Regex("(\\d+)-(\\d+)\\s([a-zA-Z]): (.+)")

File("input.txt").readLines().map { line ->
    reg.findAll(line, 0).toList().let { it -> it[0].groupValues }.let { values ->
        PasswordLine(values[1].toInt(), values[2].toInt(), values[3][0], values[4])
    }
}.toTypedArray().let { lineArray ->
    // Part 1
    print("Part 1: ")
    println(lineArray.filter { line ->
        line.str.chars().filter { it -> it == line.char.toInt() }.count() in line.min..line.max
    }.count())

    // Part 2
    print("Part 2: ")
    println(lineArray.filter { line ->
        (line.str[line.min-1] == line.char) xor (line.str[line.max-1] == line.char)
    }.count())
}

