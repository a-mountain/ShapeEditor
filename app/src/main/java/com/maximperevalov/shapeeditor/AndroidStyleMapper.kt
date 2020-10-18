package com.maximperevalov.shapeeditor

import android.graphics.Paint
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 * Трансформує кроспалтформенні стилі фігур, в їхнї реалізації на Android
 */
object AndroidStyleMapper {

    fun getFillPaint(shapeStyle: Style) = Paint().apply {
        val fillColor = shapeStyle.fillColor ?: throw Exception("Fill color must be not Null")
        style = Paint.Style.FILL
        color = fillColor.toAndroidColor()
    }

    fun getStrokePaint(shapeStyle: Style) = Paint().apply {
        val stroke = shapeStyle.stroke ?: throw Exception("Stroke must be not Null")
        style = Paint.Style.STROKE
        color = stroke.color.toAndroidColor()
        strokeWidth = stroke.width
    }
}
