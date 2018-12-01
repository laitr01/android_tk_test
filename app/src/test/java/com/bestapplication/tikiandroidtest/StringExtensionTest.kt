package com.bestapplication.tikiandroidtest

import com.bestapplication.tikiandroidtest.extendsion.toTwoLines
import com.bestapplication.tikiandroidtest.model.KeywordTwoLine
import junit.framework.TestCase.assertEquals
import org.junit.Test

class StringExtensionTest {

    @Test
    fun `test break a long line`() {
        assertEquals("tiki".toTwoLines(), KeywordTwoLine("tiki", "")) //one word
        assertEquals("tiki test".toTwoLines(), KeywordTwoLine("tiki", "test")) //two words
        assertEquals("today is black friday!".toTwoLines(), KeywordTwoLine("today is", "black friday!")) //many words
        assertEquals("".toTwoLines(), KeywordTwoLine("", "")) //empty
        assertEquals("     ".toTwoLines(), KeywordTwoLine("", "")) //empty
        assertEquals(
            "   tiki android test  ".toTwoLines(),
            KeywordTwoLine("tiki android", "test")
        ) //many words with space at head
        assertEquals("   tiki      android test  ".toTwoLines(), KeywordTwoLine("tiki android", "test"))
        assertEquals("   tiki       test  android".toTwoLines(), KeywordTwoLine("tiki test", "android"))

        assertEquals("xiaomi".toTwoLines(), KeywordTwoLine("xiaomi", ""))
        assertEquals("bitis hunter".toTwoLines(), KeywordTwoLine("bitis", "hunter"))
        assertEquals("bts".toTwoLines(), KeywordTwoLine("bts", ""))
        assertEquals("balo".toTwoLines(), KeywordTwoLine("balo", ""))
        assertEquals("bitis hunter x".toTwoLines(), KeywordTwoLine("bitis", "hunter x"))
        assertEquals("tai nghe".toTwoLines(), KeywordTwoLine("tai", "nghe"))
        assertEquals("harry potter".toTwoLines(), KeywordTwoLine("harry", "potter"))
        assertEquals("anker".toTwoLines(), KeywordTwoLine("anker", ""))
        assertEquals("iphone".toTwoLines(), KeywordTwoLine("iphone", ""))
        assertEquals("balo nữ".toTwoLines(), KeywordTwoLine("balo", "nữ"))
        assertEquals("nguyễn nhật ánh".toTwoLines(), KeywordTwoLine("nguyễn", "nhật ánh"))
        assertEquals("đắc nhân tâm".toTwoLines(), KeywordTwoLine("đắc nhân", "tâm"))
        assertEquals("ipad".toTwoLines(), KeywordTwoLine("ipad", ""))
        assertEquals("senka".toTwoLines(), KeywordTwoLine("senka", ""))
        assertEquals("tai nghe bluetooth".toTwoLines(), KeywordTwoLine("tai nghe", "bluetooth"))
        assertEquals("son".toTwoLines(), KeywordTwoLine("son", ""))
        assertEquals("maybelline".toTwoLines(), KeywordTwoLine("maybelline", ""))
        assertEquals("laneige".toTwoLines(), KeywordTwoLine("laneige", ""))
        assertEquals("kem chống nắng".toTwoLines(), KeywordTwoLine("kem chống", "nắng"))
        assertEquals("anh chính là thanh xuân của em".toTwoLines(), KeywordTwoLine("anh chính là", "thanh xuân của em"))
        assertEquals("banh trung thu kinh do".toTwoLines(), KeywordTwoLine("banh trung", "thu kinh do"))
    }

}