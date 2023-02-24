package com.malash.shopapp.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class MSPButton(context: Context, attrs: AttributeSet) : AppCompatButton(context, attrs) {
    init {
        typeface = createTypeface(context, "Bold")
    }
}