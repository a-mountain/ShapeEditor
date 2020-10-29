package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.shapes.styles.Style
import com.maximperevalov.shapeeditor.views.ShapeEditorView
import com.maximperevalov.shapeeditor.views.ShapeInfoButtonView

class ShapeEditorController(
    private val shapeEditorView: ShapeEditorView,
    private val shapeInfoButtonView: ShapeInfoButtonView,
) {

    private var style: Style
        get() = shapeEditorView.shapeStyle
        set(value) {
            if (value.isStrokeless && value.isAbsoluteTransparent)
                throw RuntimeException("Style can't be strokeless and absolute transparent at the same time")

            shapeInfoButtonView.style = value
            shapeEditorView.shapeStyle = value
        }

    var selectedShape: SelectedShape
        get() = shapeEditorView.selectedShape
        set(value) {
            shapeInfoButtonView.setSelectedShape(value)
            shapeEditorView.selectedShape = value
        }

    fun setStrokeColor(strokeColor: Color) {
        style = style.withStrokeColor(strokeColor)
    }

    fun setFillColor(fillColor: Color) {
        style = style.withFillColor(fillColor)
    }

    fun isStrokeless(isStrokeless: Boolean) {
        style = style.withIsStrokeless(isStrokeless)
    }

    fun isAbsoluteTransparent(isAbsoluteTransparent: Boolean) {
        style = style.withIsAbsoluteTransparent(isAbsoluteTransparent)
    }

    fun clearAllShapes() {
        shapeEditorView.clearAllShapes()
    }

    fun clearLastShape() {
        shapeEditorView.clearLastShape()
    }
}
