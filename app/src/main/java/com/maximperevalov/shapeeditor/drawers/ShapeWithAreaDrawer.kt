package com.maximperevalov.shapeeditor.drawers

import android.graphics.Canvas
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 * В Android неможливо намалювати фігуру, в якої колір заповнення відрізняється від кольору обводки.
 * Тому це потрібно робити в два етапи, спочатку намалювати заповнення, а потім обводку.
 *
 * Цей клас інкапсулює вищезазначену логіку для малювання фігури з площую: прямокутник, коло ... .
 */
abstract class ShapeWithAreaDrawer(val style: Style) {
    fun draw(canvas: Canvas) {
        if (style.fillColor != null) {
            drawFill(canvas)
        }
        if (style.stroke != null) {
            drawStroke(canvas)
        }
    }

    protected abstract fun drawFill(canvas: Canvas)

    protected abstract fun drawStroke(canvas: Canvas)
}
