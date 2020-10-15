package com.maximperevalov.shapeeditor.domain.shapes.styles

import com.maximperevalov.shapeeditor.domain.Color

/**
 * Містить, "космітичні" атрибути фігур, такі як: колір, обедення
 */
class Style(var fillColor: Color?, var stroke: Stroke?) {

    fun copy() = Style(fillColor, stroke?.copy())
}