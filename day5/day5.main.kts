#!/usr/bin/env kotlin

import java.io.File
import kotlin.*

val start = System.currentTimeMillis()

data class BoardingPass(val row: Int, val col: Int, val seatId: Int)

File("input.txt").readLines().map { line -> line.map { if (it == 'F' || it == 'L') '0' else '1' }.joinToString("") }.map { line ->
    mapOf(
        "row" to Integer.parseInt(line.slice(0..6), 2),
        "col" to Integer.parseInt(line.slice(7..9), 2)
    )
}.map {
    it + mapOf("seatId" to (it["row"]!! * 8) + it["col"]!!)
}.sortedWith(compareBy { it["seatId"] }).let { boardingPassList ->
    println("Map: ${boardingPassList}")
    println("Part 1: ${boardingPassList.last()["seatId"]}")
    println("Part 2: ${boardingPassList.reduce{ acc, item ->
        if (item["seatId"]!! - 1 == acc["seatId"]) item else acc }["seatId"]!! + 1
    }")
}

println("Elapsed: ${System.currentTimeMillis() - start}ms")
