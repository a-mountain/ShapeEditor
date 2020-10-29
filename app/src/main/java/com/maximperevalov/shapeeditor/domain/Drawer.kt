package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 *  Містить базові функціїї малювання об'єктів.
 */

interface Drawer {

    fun drawPoint(x: Float, y: Float, style: Style)

    /**
     * @param x - x кордината верхьного-лівого кута прямокутника
     * @param y - y кордината верхьного-лівого кута прямокутника
     */
    fun drawRect(x: Float, y: Float, width: Float, height: Float, style: Style)

    fun drawLine(startX: Float, startY: Float, endX: Float, endY: Float, style: Style)

    /**
     * Параметри такі самі як і в "drawRect", тому що описують прямокутник,
     * описаний навколо цього еліпа
     */
    fun drawEllipse(x: Float, y: Float, width: Float, height: Float, style: Style)

    fun drawCircle(centerX: Float, centerY: Float, radius: Float, style: Style)
}
