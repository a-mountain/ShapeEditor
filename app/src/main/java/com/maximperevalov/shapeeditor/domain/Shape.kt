package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

abstract class Shape(var style: Style) {
    abstract fun draw(drawer: Drawer)
}
