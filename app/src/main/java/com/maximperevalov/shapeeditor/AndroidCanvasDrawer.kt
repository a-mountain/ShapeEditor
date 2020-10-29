package com.maximperevalov.shapeeditor

import android.graphics.Canvas
import android.graphics.Paint
import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 * Реалізує малювання базових фігур для операційної системи Android, базується на класі "Canvas"
 */
class AndroidCanvasDrawer(private val canvas: Canvas) : Drawer {

    override fun drawPoint(x: Float, y: Float, style: Style) {
        canvas.drawPoint(x, y, AndroidStyleMapper.getStrokePaint(style))
    }

    override fun drawLine(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        style: Style,
    ) {
        canvas.drawLine(startX, startY, endX, endY, AndroidStyleMapper.getStrokePaint(style))
    }

    override fun drawRect(x: Float, y: Float, width: Float, height: Float, style: Style) {
        drawShapeWithArea(style) {
            canvas.drawRect(x, y, width, height, it)
        }
    }

    override fun drawEllipse(x: Float, y: Float, width: Float, height: Float, style: Style) {
        drawShapeWithArea(style) {
            canvas.drawOval(x, y, width, height, it)
        }
    }

    override fun drawCircle(centerX: Float, centerY: Float, radius: Float, style: Style) {
        drawShapeWithArea(style) {
            canvas.drawCircle(centerX, centerY, radius, it)
        }
    }

    private inline fun drawShapeWithArea(
        style: Style,
        drawShape: (Paint) -> Unit,
    ) {
        if (!style.isAbsoluteTransparent) {
            drawShape(AndroidStyleMapper.getFillPaint(style))
        }
        if (!style.isStrokeless) {
            drawShape(AndroidStyleMapper.getStrokePaint(style))
        }
    }
}
