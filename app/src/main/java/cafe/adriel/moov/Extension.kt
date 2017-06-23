package cafe.adriel.moov

import android.content.Context
import android.graphics.Color
import android.view.MenuItem
import android.widget.Toast
import com.joanzapata.iconify.Icon
import com.joanzapata.iconify.IconDrawable
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): Date? {
    try {
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(this)
    } catch (e: Exception){
        return null
    }
}

fun Date.toFormattedString(): String =
        DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US).format(this)

fun MenuItem.setFontIcon(icon: Icon) =
        setIcon(IconDrawable(App.context, icon)
                .color(Color.WHITE)
                .sizeDp(24))!!

fun Context.showToast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()