package com.maximperevalov.shapeeditor.domain.styles

import com.maximperevalov.shapeeditor.domain.Color

class EllipseStyle(
    color: Color = DEFAULT_SHAPE_COLOR,
    isFill: Boolean = true,
    strokeWith: Float = 4F,
    strokeColor: Color = Color.BLACK,
) : ShapeWithAreaStyle(color, isFill, strokeWith, strokeColor)