package com.malash.shopapp.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class MSPTextViewBoldItalic(context: Context, attrs: AttributeSet) :
    AppCompatTextView(context, attrs) {
    init {
        typeface = createTypeface(context, "BoldItalic")
    }
}