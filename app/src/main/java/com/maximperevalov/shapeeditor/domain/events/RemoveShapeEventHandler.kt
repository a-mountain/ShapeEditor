package com.maximperevalov.shapeeditor.domain.events

import com.maximperevalov.shapeeditor.domain.Shape

interface RemoveShapeEventHandler : EditorEventHandler {
    fun onRemoveShape(shape: Shape)
}