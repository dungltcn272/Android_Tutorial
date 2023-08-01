package com.example.addtocartanimetion

import android.graphics.Bitmap
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView

@Suppress("DEPRECATION")
class AnimationUtil {
    private val ANIMATION_DURUTION :Int=1000
    private var isAnimationStart :Boolean = false

    fun translateAnimation(viewAnimation: ImageView, startView: View, endView: View, animationListener: AnimationListener){
        startView.isDrawingCacheEnabled =true
        val cache =startView.drawingCache
        if(cache ==null){
            return
        }
        val bitmap=Bitmap.createBitmap(cache)
        startView.isDrawingCacheEnabled=false

        viewAnimation.setImageBitmap(bitmap)

        val startViewWidthCenter = startView.width.toFloat() /2
        val startViewHeightCenter =startView.height.toFloat() /2

        val endViewWidthCenter =endView.width * 0.75f
        val endViewHeightCenter =endView.height / 2f

        val startPos =IntArray(2)
        endView.getLocationOnScreen(startPos)

        val endPos =IntArray(2)
        endView.getLocationOnScreen(endPos)

        val fromX =startPos[0]
        val toX =endPos[0]+ endViewWidthCenter -startViewWidthCenter
        val fromY =startPos[1] -startViewHeightCenter
        val toY=endPos[1] -endViewHeightCenter+startViewHeightCenter
        val animationSet = AnimationSet(true)
        animationSet.interpolator = AccelerateInterpolator()

        val animationDuration =200

        val startScaleAnimation =ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, startViewWidthCenter, startViewHeightCenter)
        startScaleAnimation.duration=animationDuration.toLong()

        val translateAnimation =TranslateAnimation(fromX.toFloat(), toX, fromY.toFloat(), toY)
        translateAnimation.startOffset= animationDuration.toLong()
        translateAnimation.duration=ANIMATION_DURUTION.toLong()

        val translateScaleAnimation =ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, toX, toY)
        translateScaleAnimation.startOffset=animationDuration.toLong()
        translateScaleAnimation.duration=ANIMATION_DURUTION.toLong()

        animationSet.addAnimation(startScaleAnimation)
        animationSet.addAnimation(translateAnimation)
        animationSet.addAnimation(translateScaleAnimation)

        if(isAnimationStart){
            viewAnimation.clearAnimation()
            if(animationListener !=null){
                animationListener.onAnimationEnd(null)
            }
        }

        viewAnimation.startAnimation(animationSet)

        animationSet.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                isAnimationStart=true

                viewAnimation.visibility=View.VISIBLE
                startView.visibility=View.INVISIBLE

                if(animationListener !=null){
                    animationListener.onAnimationStart(animation)
                }
            }

            override fun onAnimationEnd(animation: Animation?) {
                viewAnimation.visibility=View.GONE
                startView.visibility=View.VISIBLE

                if(animationListener !=null){
                    animationListener.onAnimationEnd(animation)
                }

                isAnimationStart=false
            }

            override fun onAnimationRepeat(animation: Animation?) {
                if(animationListener !=null){
                    animationListener.onAnimationRepeat(animation)
                }
            }
        })
    }
}