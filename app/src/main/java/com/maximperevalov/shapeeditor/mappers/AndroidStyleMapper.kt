package com.maximperevalov.shapeeditor.mappers

import android.graphics.Paint
import com.maximperevalov.shapeeditor.domain.styles.*
/**
 * Трансформує, стилі фігуо в їхню реалізацію  на Android
 */

object AndroidStyleMapper {

    private fun getShapePaint(shapeStyle: ShapeStyle) = Paint().apply {
        color = AndroidColorMapper.getAndroidColor(shapeStyle.color)
        strokeWidth = shapeStyle.strokeWidth
    }

    private fun getShapeWithAreaStyle(shapeWithAreaStyle: ShapeWithAreaStyle) =
        getShapePaint(shapeWithAreaStyle).apply {
            style = if (shapeWithAreaStyle.isFill) Paint.Style.FILL_AND_STROKE else Paint.Style.STROKE

        }

    fun getRectPaint(rectangleStyle: RectangleStyle) = getShapeWithAreaStyle(rectangleStyle)

    fun getEllipsePaint(ellipseStyle: EllipseStyle) = getShapeWithAreaStyle(ellipseStyle)

    fun getLinePaint(lineStyle: LineStyle) = getShapePaint(lineStyle)

    fun getPointPaint(pointStyle: PointStyle) = getShapePaint(pointStyle)
}