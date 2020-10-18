package com.maximperevalov.shapeeditor.domain

import com.maximperevalov.shapeeditor.domain.shapes.styles.Style

/**
 *
 * Інкапсулує додаткову логіку для взаємодіїї с стилями фігур.
 *
 * Не дозволяє відключати обводку і заповенння одночасно.
 * Збергігає значення обводки і заповнення, коли вони відключенні.
 */
class StyleManager(private val shapeStyle: Style) {

    /**
     * Збергігає значення обводки і заповнення, коли вони відключенні.
     */
    private val savedStyle = shapeStyle.copy()

    var hasStroke: Boolean = true
        set(value) {
            if (!hasFill && !value) {
                throw RuntimeException("hasStroke can't be equal false. Style must have stroke or fill.")
            }
            if (value) {
                applyStroke()
            } else {
                removeStroke()
            }
            field = value
        }

    var hasFill: Boolean = true
        set(value) {
            if (!hasStroke && !value) {
                throw RuntimeException("hasFill can't be equal false. Style must have stroke or fill.")
            }
            if (value) {
                applyFillColor()
            } else {
                removeFill()
            }
            field = value
        }

    var fillColor: Color
        get() = savedStyle.fillColor ?: DEFAULT_SELECTION_COLOR
        set(value) {
            saveFillColor(value)
            if (hasFill) {
                applyFillColor()
            }
        }

    var strokeColor: Color
        get() = savedStyle.stroke?.color ?: DEFAULT_SELECTION_COLOR
        set(value) {
            saveStrokeColor(value)
            if (hasStroke) {
                applyStrokeColor()
            }
        }

    var strokeWidth: Float
        get() = savedStyle.stroke?.width ?: DEFAULT_STROKE_WIDTH
        set(value) {
            saveStrokeWidth(value)
            if (hasStroke) {
                applyStrokeWidth()
            }
        }

    private fun saveStrokeWidth(width: Float) {
        savedStyle.stroke?.width = width
    }

    private fun saveStrokeColor(color: Color) {
        savedStyle.stroke?.color = color
    }

    private fun saveFillColor(color: Color) {
        savedStyle.fillColor = color
    }

    private fun applyFillColor() {
        shapeStyle.fillColor = savedStyle.fillColor
    }

    private fun applyStrokeColor() {
        shapeStyle.stroke?.color = savedStyle.stroke!!.color
    }

    private fun applyStrokeWidth() {
        shapeStyle.stroke?.width = savedStyle.stroke!!.width
    }

    private fun applyStroke() {
        shapeStyle.stroke = savedStyle.stroke
    }

    private fun removeStroke() {
        shapeStyle.stroke = null
    }

    private fun removeFill() {
        shapeStyle.fillColor = null
    }
}
