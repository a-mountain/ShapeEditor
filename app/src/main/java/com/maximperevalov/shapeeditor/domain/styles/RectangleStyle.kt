package com.maximperevalov.shapeeditor.domain.styles

import com.maximperevalov.shapeeditor.domain.Color

open class RectangleStyle(
    color: Color = DEFAULT_SHAPE_COLOR, isFill: Boolean = true,
    strokeWith: Float = 5F,
    strokeColor: Color = Color.BLACK,
) : ShapeWithAreaStyle(color, isFill, strokeWith, strokeColor)