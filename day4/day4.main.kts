#!/usr/bin/env kotlin

import java.io.File
import kotlin.*

val start = System.currentTimeMillis()

File("input.txt").readText().let{
    Regex("^.+(?:\\r?\\n(?!\\r?\\n).*)*", RegexOption.MULTILINE)
        .findAll(it)
        .toList()
}.map { it.value.replace("\n", " ") }.map { line ->
    Regex("([\\w\\d]+):([#\\w\\d]+)")
        .findAll(line)
        .toList()
        .map { it.groupValues }
        .fold(mutableMapOf<String, String>(), { acc, data ->
            acc[data[1]] = data[2]; acc
        })
}.filter { pp ->
    pp.keys.containsAll(listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"))
}.let { ppArray ->
    ppArray.count().let { println("Part 1: $it"); it }
    ppArray.filter { pp ->
        pp["byr"]!!.toInt() in 1920..2002
            && pp["iyr"]!!.toInt() in 2010..2020
            && pp["eyr"]!!.toInt() in 2020..2030
            && pp["hcl"].toString().matches(Regex("^(#[a-fA-F0-9]{6})$"))
            && pp["ecl"] in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
            && pp["pid"].toString().matches(Regex("^\\d{9}$"))
            && Regex("(\\d+)(cm|in)")
                .find(pp["hgt"]
                .toString())?.groupValues.let {
            it != null &&
                ((it[2] == "cm" && it[1].toInt() in 150..193)
                    || (it[2] == "in" && it[1].toInt() in 59..76))
        }
    }.count().let { println("Part 2: $it") }
}

println("Elapsed: ${System.currentTimeMillis() - start}ms")