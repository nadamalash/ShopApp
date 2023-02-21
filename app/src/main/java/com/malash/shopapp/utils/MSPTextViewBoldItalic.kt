package com.malash.shopapp.utils
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * Custom TextView with TypeFace 'Montserrat'
 **/
class MSPTextViewBoldItalic(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    init {
        //Call the function to apply the font to the component
        applyFont()
    }

    private fun applyFont() {
        //This is used to get the file from assets and set it to the textView
        val typeFace: Typeface = Typeface.createFromAsset(context.assets, "Montserrat-BoldItalic.ttf")
        typeface = typeFace
    }
}