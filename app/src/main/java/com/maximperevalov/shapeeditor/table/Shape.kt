package com.maximperevalov.shapeeditor.table

import org.json.JSONObject

data class Shape(
    val shapeType: String,
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int
) {

    fun toJson(): JSONObject = JSONObject().apply {
        put("shapeType", shapeType)
        put("x1", x1)
        put("y1", y1)
        put("x2", x2)
        put("y2", y2)
    }
}
