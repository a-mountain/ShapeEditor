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

    private val ellipseDrawer = EllipseDrawer(canvas)
    private val rectangleDrawer = RectangleDrawer(canvas)

    override fun drawPoint(x: Float, y: Float, style: Style) {
        canvas.drawPoint(x, y, AndroidStyleHelper.getStrokePaint(style))
    }

    override fun drawLine(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        style: Style
    ) {
        if (style.fillColor != null) {
            canvas.drawLine(startX, startY, endX, endY, AndroidStyleHelper.getFillPaint(style))
        }
        if (style.stroke != null) {
            canvas.drawLine(startX, startY, endX, endY, AndroidStyleHelper.getStrokePaint(style))
        }
        canvas.drawLine(startX, startY, endX, endY, AndroidStyleHelper.getStrokePaint(style))
    }

    override fun drawRect(x: Float, y: Float, width: Float, height: Float, style: Style) {
        rectangleDrawer.drawRect(x, y, width, height, style)
    }

    override fun drawEllipse(x: Float, y: Float, width: Float, height: Float, style: Style) {
        ellipseDrawer.drawEllipse(x, y, width, height, style)
    }
}
