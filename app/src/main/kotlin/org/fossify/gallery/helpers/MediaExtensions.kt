package org.fossify.gallery.helpers

import org.fossify.commons.extensions.isPortrait
import org.fossify.commons.helpers.photoExtensions
import org.fossify.commons.helpers.rawExtensions
import org.fossify.commons.helpers.videoExtensions
import java.io.File

val imageExtensions1: List<String> = photoExtensions.toList().map { "${it}1" }

val videoExtensions1: List<String> = videoExtensions.toList().map { "${it}1" }

// rawExtensions has no .raw entry, add it explicitly here so .raw1 is recognised too
val rawExtensions1: List<String> = rawExtensions.toList().map { "${it}1" } + ".raw1"

fun String.isImage1() = imageExtensions1.any { endsWith(it, true) }

fun String.isVideo1() = videoExtensions1.any { endsWith(it, true) }

fun String.isGif1() = endsWith(".gif1", true)

fun String.isRaw1() = rawExtensions1.any { endsWith(it, true) }

fun String.isSvg1() = endsWith(".svg1", true)

fun File.isImage1() = absolutePath.isImage1()

fun File.isVideo1() = absolutePath.isVideo1()

fun File.isGif1() = absolutePath.isGif1()

fun File.isRaw1() = absolutePath.isRaw1()

fun File.isSvg1() = absolutePath.isSvg1()

// keep the isPortrait() call to stay consistent with the commons isMediaFile() check
fun String.isMedia1() = isImage1() || isVideo1() || isGif1() || isRaw1() || isSvg1() || isPortrait()

fun File.isMedia1() = absolutePath.isMedia1()

fun String.isWebP1() = endsWith(".webp1", true)

fun String.isApng1() = endsWith(".apng1", true)

fun String.isAvif1() = endsWith(".avif1", true)

fun String.isJpg1() = endsWith(".jpg1", true) || endsWith(".jpeg1", true)

fun String.isPng1() = endsWith(".png1", true)

fun getSelectionArgsQuery1(filterMedia: Int): ArrayList<String> {
    val args = ArrayList<String>()
    if (filterMedia and TYPE_IMAGES != 0) {
        imageExtensions1.forEach {
            args.add("%$it")
        }
    }

    if (filterMedia and TYPE_PORTRAITS != 0) {
        args.add("%.jpg1")
        args.add("%.jpeg1")
    }

    if (filterMedia and TYPE_VIDEOS != 0) {
        videoExtensions1.forEach {
            args.add("%$it")
        }
    }

    if (filterMedia and TYPE_GIFS != 0) {
        args.add("%.gif1")
    }

    if (filterMedia and TYPE_RAWS != 0) {
        rawExtensions1.forEach {
            args.add("%$it")
        }
    }

    if (filterMedia and TYPE_SVGS != 0) {
        args.add("%.svg1")
    }

    return args
}