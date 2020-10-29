package com.maximperevalov.shapeeditor

import android.graphics.Canvas
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
        val drawFill: () -> Unit = {
            canvas.drawRect(x, y, width, height, AndroidStyleMapper.getFillPaint(style))
        }
        val drawStroke: () -> Unit = {
            canvas.drawRect(x, y, width, height, AndroidStyleMapper.getStrokePaint(style))
        }
        drawShapeWithArea(style, drawFill, drawStroke)
    }

    override fun drawEllipse(x: Float, y: Float, width: Float, height: Float, style: Style) {
        val drawFill: () -> Unit = {
            canvas.drawOval(x, y, width, height, AndroidStyleMapper.getFillPaint(style))
        }
        val drawStroke: () -> Unit = {
            canvas.drawOval(x, y, width, height, AndroidStyleMapper.getStrokePaint(style))
        }
        drawShapeWithArea(style, drawFill, drawStroke)
    }

    private inline fun drawShapeWithArea(
        style: Style,
        drawFill: () -> Unit,
        drawStroke: () -> Unit,
    ) {
        if (!style.isAbsoluteTransparent) {
            drawFill()
        }
        if (!style.isStrokeless) {
            drawStroke()
        }
    }
}
