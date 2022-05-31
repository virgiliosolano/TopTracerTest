package com.toptracer.virgiliomagalhaes.shared

import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener

fun Fragment.showDialog(title:String, message: String, buttonText: String) {
    AlertDialog.Builder(requireContext())
        .apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(
                buttonText
            ) { dialog, i -> dialog.dismiss() }
        }
        .create()
        .show()
}

fun Fragment.loadImage(imageUrl: String, imageView: ImageView,
                       listener: RequestListener<GifDrawable>) {
    Glide.with(requireContext())
        .asGif()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .load(imageUrl)
        .addListener(listener)
        .into(imageView)
}