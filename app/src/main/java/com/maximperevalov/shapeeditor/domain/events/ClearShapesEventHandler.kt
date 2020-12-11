package com.maximperevalov.shapeeditor.domain.events

interface ClearShapesEventHandler : EditorEventHandler {
    fun onClearShapes()
}