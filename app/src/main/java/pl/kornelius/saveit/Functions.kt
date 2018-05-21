package pl.kornelius.saveit

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by seeyIT.
 */
fun Long.toDate(): String {
    val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    val date = Date(this)
    return simpleDateFormat.format(date)
}