package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.shapes.Rectangle
import com.maximperevalov.shapeeditor.domain.styles.RectangleStyle

private val DEFAULT_BACKGROUND_COLOR = Color.WHITE

class Background(width: Int, height: Int) {

    private val backgroundRect = Rectangle(
        0F,
        0F,
        width.toFloat(),
        height.toFloat(),
        RectangleStyle(color = DEFAULT_BACKGROUND_COLOR)
    )

    var color: Color
        get() = backgroundRect.style.color
        set(value) {
            backgroundRect.style.color = value
        }

    val height
        get() = backgroundRect.height

    val width
        get() = backgroundRect.width

    fun draw(drawer: Drawer) {
        backgroundRect.draw(drawer)
    }
}
