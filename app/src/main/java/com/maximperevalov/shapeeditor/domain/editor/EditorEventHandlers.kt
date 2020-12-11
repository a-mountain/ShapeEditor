package com.maximperevalov.shapeeditor.domain.editor

import com.maximperevalov.shapeeditor.domain.events.EditorEvent
import com.maximperevalov.shapeeditor.domain.events.EditorEventHandler
import java.util.*
import kotlin.collections.ArrayList

class EditorEventHandlers {

    private val handlers: MutableMap<EditorEvent, ArrayList<EditorEventHandler>> =
        EnumMap(EditorEvent::class.java)

    fun addEventHandler(event: EditorEvent, eventHandler: EditorEventHandler) {
        if (!handlers.containsKey(event)) {
            handlers[event] = ArrayList()
        }
        handlers[event]!!.add(eventHandler)
    }

    fun removeEventHandler(event: EditorEvent, eventHandler: EditorEventHandler) {
        handlers[event]?.remove(eventHandler)
    }

    fun getEventHandlers(event: EditorEvent) = handlers[event]
}