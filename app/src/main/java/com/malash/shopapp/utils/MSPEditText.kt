package com.malash.shopapp.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class MSPEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    init {
        typeface = createTypeface(context, "Regular")
    }
}