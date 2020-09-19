package com.maximperevalov.shapeeditor.domain.styles

import com.maximperevalov.shapeeditor.domain.Color

class LineStyle(color: Color = DEFAULT_SHAPE_COLOR, strokeWith: Float = 4F) : ShapeStyle(
    color,
    strokeWith,
)