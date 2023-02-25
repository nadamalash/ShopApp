package com.malash.shopapp.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox

class MSPCheckBox (context: Context, attrs: AttributeSet) : AppCompatCheckBox(context, attrs) {
    init {
        typeface = createTypeface(context, "Regular")
    }}