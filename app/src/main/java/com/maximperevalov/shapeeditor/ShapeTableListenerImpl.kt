package com.maximperevalov.shapeeditor

import com.maximperevalov.shapeeditor.table.SelectEvent
import com.maximperevalov.shapeeditor.table.ShapeTable
import com.maximperevalov.shapeeditor.table.ShapeTableListener

class ShapeTableListenerImpl(
    private val controller: ShapeEditorController,
    private val table: ShapeTable
) : ShapeTableListener {

    override fun onSelectShape(shapeId: String, selectEvent: SelectEvent) {
        when (selectEvent) {
            SelectEvent.SelectNew -> {
                controller.selectShape(shapeId.toInt())
            }
            SelectEvent.Unselect -> {
                controller.removeSelection()
            }
        }
        controller.requestRender()
    }

    override fun onDeleteShape(shapeId: String) {
        controller.removeShapeByHashCode(shapeId.toInt())
        table.removeShapeRow(shapeId)
        controller.requestRender()
    }
}