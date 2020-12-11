package com.maximperevalov.shapeeditor.domain.events

import com.maximperevalov.shapeeditor.domain.Shape

interface DrawNewShapeEventHandler : EditorEventHandler {
    fun onAddNewShape(shape: Shape)
}