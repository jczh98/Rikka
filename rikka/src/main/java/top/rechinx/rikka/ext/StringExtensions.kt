package top.rechinx.rikka.ext

fun String.chop(count: Int, replacement: String = "..."): String {
    return if (length > count)
        take(count - replacement.length) + replacement
    else
        this

}