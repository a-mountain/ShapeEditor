package com.maximperevalov.shapeeditor.drawers

import android.graphics.Canvas
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style
import com.maximperevalov.shapeeditor.AndroidStyleHelper

/**
 * В Android неможливо намалювати фігуру, в якої колір заповнення відрізняється від кольору обводки.
 * Тому це потрібно робити в два етапи, спочатку намалювати заповнення, а потім обводку.
 *
 * Цей клас інкапсулює вищезазначену логіку для малювання еліпса.
 */
class EllipseDrawer(private val canvas: Canvas) {

    fun drawEllipse(x: Float, y: Float, width: Float, height: Float, style: Style) {
        if (style.fillColor != null) {
            drawEllipseFill(x, y, width, height, style)
        }
        if (style.stroke != null) {
            drawEllipseStroke(x, y, width, height, style)
        }
    }

    private fun drawEllipseFill(x: Float, y: Float, width: Float, height: Float, style: Style) {
        canvas.drawOval(x, y, width, height, AndroidStyleHelper.getFillPaint(style))
    }

    private fun drawEllipseStroke(x: Float, y: Float, width: Float, height: Float, style: Style) {
        canvas.drawOval(x, y, width, height, AndroidStyleHelper.getStrokePaint(style))
    }
}
