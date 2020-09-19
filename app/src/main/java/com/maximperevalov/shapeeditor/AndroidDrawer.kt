package com.maximperevalov.shapeeditor

import android.graphics.Canvas
import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.DEFAULT_SELECTION_COLOR
import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.styles.EllipseStyle
import com.maximperevalov.shapeeditor.domain.styles.LineStyle
import com.maximperevalov.shapeeditor.domain.styles.PointStyle
import com.maximperevalov.shapeeditor.domain.styles.RectangleStyle
import com.maximperevalov.shapeeditor.mappers.AndroidStyleMapper

/**
 * Реалізує малювання базових фігур на Android
 */
class AndroidDrawer(private val canvas: Canvas) : Drawer {

    override fun drawPoint(x: Float, y: Float, style: PointStyle) {
        canvas.drawPoint(x, y, AndroidStyleMapper.getPointPaint(style))
    }

    override fun drawRect(x: Float, y: Float, width: Float, height: Float, style: RectangleStyle) {
        canvas.drawRect(x, y, width, height, AndroidStyleMapper.getRectPaint(style))
        if (style.isFill) {
            drawRectStroke(x, y, width, height, style.strokeColor, style.strokeWidth)
        }
    }

    override fun drawLine(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float,
        style: LineStyle
    ) {
        canvas.drawLine(startX, startY, endX, endY, AndroidStyleMapper.getLinePaint(style))
    }

    override fun drawEllipse(x: Float, y: Float, width: Float, height: Float, style: EllipseStyle) {
        canvas.drawOval(x, y, width, height, AndroidStyleMapper.getEllipsePaint(style))
        if (style.isFill) {
            drawEllipseStroke(x, y, width, height, style.strokeColor, style.strokeWidth)
        }
    }

    private fun drawEllipseStroke(
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        strokeColor: Color,
        strokeWith: Float,
    ) {
        canvas.drawOval(
            x, y, width, height, AndroidStyleMapper.getEllipsePaint(
                EllipseStyle(
                    color = DEFAULT_SELECTION_COLOR,
                    isFill = false,
                    strokeWith = strokeWith,
                    strokeColor = strokeColor
                )
            )
        )
    }

    private fun drawRectStroke(
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        strokeColor: Color,
        strokeWith: Float,
    ) {
        canvas.drawRect(
            x, y, width, height, AndroidStyleMapper.getRectPaint(
                RectangleStyle(
                    color = DEFAULT_SELECTION_COLOR,
                    isFill = false,
                    strokeWith = strokeWith,
                    strokeColor = strokeColor
                )
            )
        )
    }
}
