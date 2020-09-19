package com.maximperevalov.shapeeditor.domain.styles

import com.maximperevalov.shapeeditor.domain.Color

class PointStyle(color: Color = DEFAULT_SHAPE_COLOR, strokeWith: Float = 5F) : ShapeStyle(color,
    strokeWith
)