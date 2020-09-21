package com.maximperevalov.shapeeditor

import android.graphics.Paint
import com.maximperevalov.shapeeditor.domain.shapes.styles.*

/**
 * Допомагає, трансформувати кроспалтформенні стилі фігур, в їхнї реалізації на Android
 */
object AndroidStyleHelper {

    fun getFillPaint(shapeStyle: Style) = Paint().apply {
        style = Paint.Style.FILL
        color = AndroidColorMapper.getAndroidColor(shapeStyle.fillColor!!)
    }

    fun getStrokePaint(shapeStyle: Style) = Paint().apply {
        val stroke = shapeStyle.stroke!!
        style = Paint.Style.STROKE
        color = AndroidColorMapper.getAndroidColor(stroke.color)
        strokeWidth = stroke.width
    }

}
