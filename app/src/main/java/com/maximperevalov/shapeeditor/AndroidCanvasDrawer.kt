package com.maximperevalov.shapeeditor

import android.graphics.Canvas
import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style
import com.maximperevalov.shapeeditor.drawers.EllipseDrawer
import com.maximperevalov.shapeeditor.drawers.RectangleDrawer

/**
 * Реалізує малювання базових фігур для операційної системи Android, базується на класі "Canvas"
 */
class AndroidCanvasDrawer(private val canvas: Canvas) : Drawer {

    override fun drawPoint(x: Float, y: Float, style: Style) {
        try {
            canvas.drawPoint(x, y, AndroidStyleMapper.getStrokePaint(style))
        } catch (e: Exception) {
            // Draw nothing
        }
    }

    override fun drawLine(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        style: Style
    ) {
        try {
            canvas.drawLine(startX, startY, endX, endY, AndroidStyleMapper.getStrokePaint(style))
        } catch (e: Exception) {
            // Draw nothing
        }
    }

    override fun drawRect(x: Float, y: Float, width: Float, height: Float, style: Style) {
        try {
            RectangleDrawer(style, x, y, width, height).draw(canvas)
        } catch (e: Exception) {
            // Draw nothing
        }
    }

    override fun drawEllipse(x: Float, y: Float, width: Float, height: Float, style: Style) {
        try {
            EllipseDrawer(style, x, y, width, height).draw(canvas)
        } catch (e: Exception) {
            // Draw nothing
        }
    }
}
