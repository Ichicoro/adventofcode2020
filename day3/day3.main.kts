#!/usr/bin/env kotlin

import java.io.File
import kotlin.*

fun checkNext(map: Array<Array<Boolean>>, xJump: Int = 3, yJump: Int = 1, xPos: Int = 0, yPos: Int = 0): Int {
    return (if (map[yPos][xPos % map[0].size]) 1 else 0) + (if (yPos + yJump < map.size) checkNext(map, xJump, yJump, xPos + xJump, yPos + yJump) else 0)
}

File("input.txt").readLines().map { line -> line.map { char -> char != '.' }.toTypedArray() }.toTypedArray().let { map ->
    println("Part 1: ${checkNext(map)}")
    println("Part 2: ${
        checkNext(map, 1, 1)
            * checkNext(map, 3, 1)  // No need to specify xJump and yJump
            * checkNext(map, 5, 1)
            * checkNext(map, 7, 1)
            * checkNext(map, 1, 2)
    }")
}
