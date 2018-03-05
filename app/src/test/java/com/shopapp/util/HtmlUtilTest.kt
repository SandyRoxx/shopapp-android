package com.shopapp.util

import com.shopapp.test.Reader
import com.shopapp.test.ext.replaceCommandSymbols
import junit.framework.Assert.assertEquals
import org.junit.Test

class HtmlUtilTest {

    companion object {
        private const val HEIGHT = 200
    }

    @Test
    fun shouldAddStyleToAllImages() {
        val testedString = Reader.readFile("testImage.html")!!
        val expected = Reader.readFile("resultImage.html")!!.replaceCommandSymbols()
        val actual = HtmlUtil.fitHtmlImages(testedString).replaceCommandSymbols()
        assertEquals(expected, actual)
    }

    @Test
    fun shouldSetSizeToAllFrames() {
        val testedString = Reader.readFile("testFrame.html")!!
        val expected = Reader.readFile("resultFrame.html")!!.replaceCommandSymbols()
        val actual = HtmlUtil.fitHtmlFrames(testedString, HEIGHT).replaceCommandSymbols()
        assertEquals(expected, actual)
    }

    @Test
    fun shouldSetSizeToAllFramesInStyles() {
        val testedString = Reader.readFile("testFrameStyle.html")!!
        val expected = Reader.readFile("resultFrameStyle.html")!!.replaceCommandSymbols()
        val actual = HtmlUtil.fitHtmlFrames(testedString, HEIGHT).replaceCommandSymbols()
        assertEquals(expected, actual)
    }

}