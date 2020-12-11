package com.maximperevalov.shapeeditor.domain.editor

import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.events.*

class EditorEventEmitter(private val handlers: EditorEventHandlers) {

    fun emitDrawNewShapeEvent(shape: Shape) {
        emit<DrawNewShapeEventHandler>(EditorEvent.DrawNewShape) {
            onAddNewShape(shape)
        }
    }

    fun emitRemoveShapeEvent(shape: Shape) {
        emit<RemoveShapeEventHandler>(EditorEvent.RemoveShape) {
            onRemoveShape(shape)
        }
    }

    fun emitClearShapesEvent() {
        emit<ClearShapesEventHandler>(EditorEvent.ClearShapes) {
            onClearShapes()
        }
    }

    private fun <T : EditorEventHandler> emit(event: EditorEvent, init: T.() -> Unit) {
        handlers.getEventHandlers(event)?.forEach {
            (it as T).init()
        }
    }
}
