package com.malash.shopapp.utils
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView

//Function to create Typeface ('Montserrat')
fun createTypeface(context: Context, fontStyle: String): Typeface =
    Typeface.createFromAsset(context.assets, "Montserrat-$fontStyle.ttf")

//Apply the font to TextView
class MSPTextView(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    init {
        typeface = createTypeface(context, "Regular")
    }
}
class MSPTextViewBold(context: Context, attrs: AttributeSet) : AppCompatTextView(context, attrs) {
    init {
        typeface = createTypeface(context, "Bold")
    }
}
class MSPTextViewBoldItalic(context: Context, attrs: AttributeSet) :
    AppCompatTextView(context, attrs) {
    init {
        typeface = createTypeface(context, "BoldItalic")
    }
}

//Apply the font to EditText
class MSPEditText(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    init {
        typeface = createTypeface(context, "Regular")
    }
}

//Apply the font to Button
class MSPButton(context: Context, attrs: AttributeSet) : AppCompatButton(context, attrs) {
    init {
        typeface = createTypeface(context, "Bold")
    }
}
