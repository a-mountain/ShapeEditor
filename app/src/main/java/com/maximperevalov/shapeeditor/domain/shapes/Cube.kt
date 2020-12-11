package com.maximperevalov.shapeeditor.domain.shapes

import com.maximperevalov.shapeeditor.domain.Drawer
import com.maximperevalov.shapeeditor.domain.Shape
import com.maximperevalov.shapeeditor.domain.helpers.PointMath
import com.maximperevalov.shapeeditor.domain.shapes.styles.Style
import kotlin.math.sqrt

class Cube(x1: Float, y1: Float, x2: Float, y2: Float, style: Style) :
    Shape(Style.createAbsoluteTransparentStyle(style.stroke)) {

    var y1 = y1
        set(value) {
            field = value
            isChanged = true
        }

    var x1 = x1
        set(value) {
            field = value
            isChanged = true
        }
    var x2 = x2
        set(value) {
            field = value
            isChanged = true
        }
    var y2 = y2
        set(value) {
            field = value
            isChanged = true
        }

    var side = calcSide()

    private var isChanged = true

    private val frontRect = Rectangle(0F, 0F, 0F, 0F, this.style)

    private fun updatePositionFrontRect() {
        frontRect.x = x2 - side
        frontRect.y = y2 - side
        frontRect.width = x2
        frontRect.height = y2
    }

    private val backRect = Rectangle(0F, 0F, 0F, 0F, this.style)

    private fun updatePositionBackRect() {
        backRect.x = x1
        backRect.y = y1
        backRect.width = x1 + side
        backRect.height = y1 + side
    }

    private val leftBotLine: Line = Line(0F, 0F, 0F, 0F, this.style)

    private fun updatePositionLeftBotLine() {
        leftBotLine.startX = x2 - side
        leftBotLine.startY = y2
        leftBotLine.endX = x1
        leftBotLine.endY = y1 + side
    }


    private val leftTopLine = Line(0F, 0F, 0F, 0F, this.style)

    private fun updatePositionLeftTopLine() {
        leftTopLine.startX = x2 - side
        leftTopLine.startY = y2 - side
        leftTopLine.endX = x1
        leftTopLine.endY = y1
    }

    private val rightBotLine = Line(0F, 0F, 0F, 0F, this.style)

    private fun updatePositionRightBotLine() {
        rightBotLine.startX = x2
        rightBotLine.startY = y2
        rightBotLine.endX = x1 + side
        rightBotLine.endY = y1 + side
    }

    private val rightTopLine = Line(0F, 0F, 0F, 0F, this.style)

    private fun updatePositionRightTopLine() {
        rightTopLine.startX = x2
        rightTopLine.startY = y2 - side
        rightTopLine.endX = x1 + side
        rightTopLine.endY = y1
    }

    private fun calcSide(): Float {
        val p1 = PointMath(x1, y1)
        val p2 = PointMath(x2, y2)
        val distance = p1.distance(p2)
        return distance / sqrt(3.0F)
    }

    private fun updatePosition() {
        side = calcSide()
        updatePositionBackRect()
        updatePositionFrontRect()
        updatePositionLeftBotLine()
        updatePositionLeftTopLine()
        updatePositionRightBotLine()
        updatePositionRightTopLine()
    }

    override var style: Style
        get() = super.style
        set(value) {
            super.style = super.style.withStroke(value.stroke)
            frontRect.style = frontRect.style.withStroke(value.stroke)
            backRect.style = backRect.style.withStroke(value.stroke)
            leftBotLine.style = leftBotLine.style.withStroke(value.stroke)
            leftTopLine.style = leftTopLine.style.withStroke(value.stroke)
            rightTopLine.style = rightTopLine.style.withStroke(value.stroke)
            rightBotLine.style = rightBotLine.style.withStroke(value.stroke)
        }

    override fun draw(drawer: Drawer) {
        if (isChanged) {
            updatePosition()
            isChanged = false
        }
        frontRect.draw(drawer)
        backRect.draw(drawer)
        leftTopLine.draw(drawer)
        leftBotLine.draw(drawer)
        rightBotLine.draw(drawer)
        rightTopLine.draw(drawer)
    }
}
