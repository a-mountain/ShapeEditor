package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

abstract class Shape(open var style: Style) {
    abstract fun draw(drawer: Drawer)
}
