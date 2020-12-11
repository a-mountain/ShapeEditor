package com.maximperevalov.shapeeditor.domain.editor

import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.shapes.styles.Stroke

class Selector {

    private var selectedShape: Shape? = null
    private var savedStroke: Stroke? = null

    fun select(shape: Shape) {
        removeSelection()
        selectedShape = shape
        savedStroke = shape.style.stroke
        selectedShape?.style = shape.style.withStroke(Stroke(10F, Color.RED, hasDash = true))
    }

    fun removeSelection() {
        if (selectedShape != null) {
            selectedShape?.style = selectedShape!!.style.withStroke(savedStroke!!)
            selectedShape = null
        }
    }
}
