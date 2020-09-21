package com.maximperevalov.shapeeditor.drawers

import android.graphics.Canvas
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style
import com.maximperevalov.shapeeditor.AndroidStyleHelper
/**
 * В Android неможливо намалювати фігуру, в якої колір заповнення відрізняється від кольору обводки.
 * Тому це потрібно робити в два етапи, спочатку намалювати заповнення, а потім обводку.
 *
 * Цей клас інкапсулює вищезазначену логіку для малювання прямокутника.
 */
class RectangleDrawer(private val canvas: Canvas) {

    fun drawRect(x: Float, y: Float, width: Float, height: Float, style: Style) {
        if (style.fillColor != null) {
            drawRectFill(x, y, width, height, style)
        }
        if (style.stroke != null) {
            drawRectStroke(x, y, width, height, style)
        }
    }

    private fun drawRectFill(x: Float, y: Float, width: Float, height: Float, style: Style) {
        canvas.drawRect(x, y, width, height, AndroidStyleHelper.getFillPaint(style))
    }

    private fun drawRectStroke(x: Float, y: Float, width: Float, height: Float, style: Style) {
        canvas.drawRect(x, y, width, height, AndroidStyleHelper.getStrokePaint(style))
    }
}
