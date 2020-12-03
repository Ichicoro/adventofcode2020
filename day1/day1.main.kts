#!/usr/bin/env kotlin

import java.io.File
import kotlin.*

File("input.txt").readLines().map { it.toInt() }.toTypedArray().let { numArray ->
    numArray.forEachIndexed part1@{ idx, elem ->
        (idx+1 until numArray.size).forEach { a ->
            if (elem + numArray[a] == 2020) {
                println("$elem * ${numArray[a]} = ${elem * numArray[a]}")
                return@part1
            }
        }
    }

    numArray.forEachIndexed { idx, elem ->
        (idx+1 until numArray.size).forEach part2@{ a ->
            (a+1 until numArray.size).forEach { b ->
                if (elem + numArray[a] + numArray[b] == 2020) {
                    println("$elem + ${numArray[a]} * ${numArray[b]} = ${elem * numArray[a] * numArray[b]}")
                    return@part2
                }
            }
        }
    }
}

