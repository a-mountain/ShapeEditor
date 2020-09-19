package com.maximperevalov.shapeeditor.domain.styles

import com.maximperevalov.shapeeditor.domain.Color

val DEFAULT_SHAPE_COLOR = Color.BLACK

/**
 * Базовий набір стилів для всіх фігур
 */
open class ShapeStyle(var color: Color = DEFAULT_SHAPE_COLOR, var strokeWidth: Float = 4F)