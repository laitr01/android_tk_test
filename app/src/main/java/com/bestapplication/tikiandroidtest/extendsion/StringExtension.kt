package com.bestapplication.tikiandroidtest.extendsion

import com.bestapplication.tikiandroidtest.model.KeywordTwoLine
import java.lang.StringBuilder

fun String.toTwoLines(): KeywordTwoLine {
    val delimiter = " "
    val wordList = split(delimiter).filter { it != "" }

    if (wordList.size == 1) return KeywordTwoLine(wordList[0], "")

    val lineOne = StringBuilder()
    val lineTwo = StringBuilder()

    var startIndex = 0
    var endIndex = wordList.size - 1

    while (startIndex <= endIndex) {

        val lineOneLen = lineOne.length
        val lineTwoLen = lineTwo.length
        when {
            startIndex == endIndex -> {
                if (lineOneLen <= lineTwoLen) {
                    lineOne.append(" ${wordList[startIndex++]}")
                } else {
                    lineTwo.insert(0, "${wordList[endIndex--]} ")
                }
            }
            lineOneLen < lineTwoLen -> lineOne.append(" ${wordList[startIndex++]}")
            lineOneLen > lineTwoLen -> lineTwo.insert(0, "${wordList[endIndex--]} ")
            else -> {
                lineOne.append(wordList[startIndex++])
                lineTwo.append(wordList[endIndex--])
            }
        }
    }
    return KeywordTwoLine(lineOne.toString(), lineTwo.toString())
}