package com.solutis.ewerton.booksonthetable.ui.util

import java.util.regex.Pattern

fun String.isValidAsEmail() = this.matches(Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).toRegex())

fun String.isValidAsPassword() = this.isNotBlank() && this.length >= 8

fun String.isValidAsName() = this.isNotBlank()