package com.maximperevalov.shapeeditor.domain.styles

import com.maximperevalov.shapeeditor.domain.Color

open class ShapeWithAreaStyle(
    color: Color = DEFAULT_SHAPE_COLOR,
    var isFill: Boolean = true,
    strokeWith: Float = 4F,
    var strokeColor: Color
) : ShapeStyle(color, strokeWith)