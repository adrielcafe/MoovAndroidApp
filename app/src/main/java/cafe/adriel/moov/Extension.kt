package cafe.adriel.moov

import java.text.SimpleDateFormat
import java.util.*

private val DATE_FORMAT = SimpleDateFormat("dd MMM yyyy", Locale.US)

fun Date.toFormattedString(): String = DATE_FORMAT.format(this)
