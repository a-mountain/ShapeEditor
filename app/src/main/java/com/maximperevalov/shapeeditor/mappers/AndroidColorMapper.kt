package com.maximperevalov.shapeeditor.mappers

import com.maximperevalov.shapeeditor.domain.Color

/**
 * Трансформує, кросплатформенний клас Сolor в реалізацію цього класу на Android
 */

object AndroidColorMapper {

    fun getAndroidColor(color: Color) = when (color) {
        Color.RED -> android.graphics.Color.RED
        Color.BLACK -> android.graphics.Color.BLACK
        Color.WHITE -> android.graphics.Color.WHITE
        Color.YELLOW -> android.graphics.Color.YELLOW
        Color.BLUE -> android.graphics.Color.BLUE
        Color.ORANGE -> android.graphics.Color.rgb(255, 128, 0)
        Color.GRAY -> android.graphics.Color.GRAY
        Color.GREEN -> android.graphics.Color.GREEN
    }
}
