package com.maximperevalov.shapeeditor.drawers

import android.graphics.Canvas
import com.maximperevalov.shapeeditor.AndroidStyleMapper
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 * В Android неможливо намалювати фігуру, в якої колір заповнення відрізняється від кольору обводки.
 * Тому це потрібно робити в два етапи, спочатку намалювати заповнення, а потім обводку.
 *
 * Цей клас інкапсулює вищезазначену логіку для малювання прямокутника.
 */
class RectangleDrawer(
    style: Style,
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
) : ShapeWithAreaDrawer(style) {

    override fun drawFill(canvas: Canvas) {
        canvas.drawRect(x, y, width, height, AndroidStyleMapper.getFillPaint(style))
    }

    override fun drawStroke(canvas: Canvas) {
        canvas.drawRect(x, y, width, height, AndroidStyleMapper.getStrokePaint(style))
    }
}
