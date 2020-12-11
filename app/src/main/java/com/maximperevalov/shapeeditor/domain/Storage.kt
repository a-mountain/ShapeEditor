package com.maximperevalov.shapeeditor.domain

interface Storage {
    fun saveShapes(shapes: List<Shape>)
    fun getAllSavedShapes(): List<Shape>
}