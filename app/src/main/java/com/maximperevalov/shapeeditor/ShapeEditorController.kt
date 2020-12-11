package com.maximperevalov.shapeeditor

import com.maximperevalov.shapeeditor.domain.Color
import com.maximperevalov.shapeeditor.domain.SelectedShape
import com.maximperevalov.shapeeditor.domain.events.EditorEvent
import com.maximperevalov.shapeeditor.domain.events.EditorEventHandler
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style
import com.maximperevalov.shapeeditor.table.ShapeTable
import com.maximperevalov.shapeeditor.views.ShapeEditorView
import com.maximperevalov.shapeeditor.views.ShapeInfoButtonView

class ShapeEditorController(
    private val shapeEditorView: ShapeEditorView,
    private val shapeInfoButtonView: ShapeInfoButtonView,
    private val shapeTable: ShapeTable,
) {

    var selectedShape: SelectedShape
        get() = shapeEditor.selectedShape
        set(value) {
            shapeInfoButtonView.setSelectedShape(value)
            shapeEditor.selectedShape = value
            shapeEditorView.invalidate()
        }

    private var drawStyle: Style
        get() = shapeEditor.currentShapeStyle
        set(value) {
            shapeInfoButtonView.style = value
            shapeEditor.currentShapeStyle = value
        }

    private val shapeEditor = shapeEditorView.shapeEditor

    fun setShapesFromStorage() {
        shapeEditor.setShapesFromStorage()
        shapeEditorView.invalidate()
    }

    fun saveShapesToStorage() {
        shapeEditor.saveShapes()
    }

    fun addEventHandler(event: EditorEvent, eventHandler: EditorEventHandler) {
        shapeEditorView.addEventHandler(event, eventHandler)
    }

    fun selectShape(hashCode: Int) {
        shapeEditor.getShapeByHashCode(hashCode)?.let { shapeEditor.selectShape(it) }
    }

    fun removeSelection() {
        shapeEditor.removeSelection()
    }

    fun removeShapeByHashCode(hashCode: Int) {
        shapeEditor.getShapeByHashCode(hashCode)?.let { shapeEditor.clearShape(it) }
    }

    fun setStrokeColor(strokeColor: Color) {
        drawStyle = drawStyle.withStrokeColor(strokeColor)
    }

    fun setFillColor(fillColor: Color) {
        drawStyle = drawStyle.withFillColor(fillColor)
    }

    fun isStrokeless(isStrokeless: Boolean) {
        drawStyle = drawStyle.withIsStrokeless(isStrokeless)
    }

    fun isAbsoluteTransparent(isAbsoluteTransparent: Boolean) {
        drawStyle = drawStyle.withIsAbsoluteTransparent(isAbsoluteTransparent)
    }

    fun clearAllShapes() {
        shapeEditor.clearAllShapes()
        shapeEditorView.invalidate()
    }

    fun clearLastShape() {
        shapeEditor.clearLastShape()
        shapeEditorView.invalidate()
    }

    fun requestRender() {
        shapeEditorView.invalidate()
    }
}
