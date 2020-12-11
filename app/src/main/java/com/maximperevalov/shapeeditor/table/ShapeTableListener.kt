package com.maximperevalov.shapeeditor.table

interface ShapeTableListener {
    fun onSelectShape(shapeId: String, selectEvent: SelectEvent)
    fun onDeleteShape(shapeId: String)
}