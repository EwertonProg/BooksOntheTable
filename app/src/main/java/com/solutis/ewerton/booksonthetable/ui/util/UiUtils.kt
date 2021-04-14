package com.solutis.ewerton.booksonthetable.ui.util

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun TextView.setColorizedText(text: String, color: Int,
                              vararg faixa: Pair<Int, Int> = arrayOf(Pair(0, text.length))) {
    val spannable = SpannableString(text)
    faixa.forEach { pair ->
        spannable.setSpan(
            ForegroundColorSpan(color),
            pair.first,
            pair.second,
            Spannable.SPAN_COMPOSING
        )
    }
    this.text = spannable
}


fun TextInputEditText.validate(textInputLayout: TextInputLayout, rules: List<(String?) -> String?>){
    for (rule in rules) {
        textInputLayout.error = rule.invoke(this.text.toString())
        if(!textInputLayout.error.isNullOrBlank()){
            return
        }
    }
    textInputLayout.isErrorEnabled = false
}

fun TextInputEditText.validateOnLostFocus(textInputLayout: TextInputLayout, vararg rules: (String?) -> String?){
    this.setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) {
            this.validate(textInputLayout, rules.asList())
        }
    }
}
