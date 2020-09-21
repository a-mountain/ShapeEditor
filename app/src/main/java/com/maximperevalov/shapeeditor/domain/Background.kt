package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.shapes.Rectangle
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

private val DEFAULT_BACKGROUND_COLOR = Color.WHITE

/**
 * Репрезентує - задній фон редактора
 */
class Background(width: Int, height: Int) {

    private val backgroundRect = Rectangle(
        0F,
        0F,
        width.toFloat(),
        height.toFloat(),
        Style(fillColor = DEFAULT_BACKGROUND_COLOR, stroke = null)
    )

    var color: Color
        get() = backgroundRect.style.fillColor!!
        set(value) {
            backgroundRect.style.fillColor = value
        }

    val height
        get() = backgroundRect.height

    val width
        get() = backgroundRect.width

    fun draw(drawer: Drawer) {
        backgroundRect.draw(drawer)
    }
}
