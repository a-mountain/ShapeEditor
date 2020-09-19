package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.styles.EllipseStyle
import com.maximperevalov.shapeeditor.domain.styles.LineStyle
import com.maximperevalov.shapeeditor.domain.styles.PointStyle
import com.maximperevalov.shapeeditor.domain.styles.RectangleStyle

/**
 *  Drawer містить базові функціїї малювання об'єктів.
 */

interface Drawer {

    fun drawPoint(x: Float, y: Float, style: PointStyle)

    /**
     * @param x - x кордината верхьного-лівого кута
     * @param y - y кордината верхьного-лівого кута
     */
    fun drawRect(x: Float, y: Float, width: Float, height: Float, style: RectangleStyle)

    fun drawLine(startX: Float, startY: Float, endX: Float, endY: Float, style: LineStyle)

    /**
     * Параметри такі самі як і в "drawRect", тому що описують прямокутник,
     * описаний навколо цього еліпа
     */
    fun drawEllipse(x: Float, y: Float, width: Float, height: Float, style: EllipseStyle)
}
