package com.appyhigh.in_app_review

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.testing.FakeReviewManager

class InAppReviewFlow(private val context: Activity) {
    fun startInAppReviewFlow(rating: Float, threshold: Float) {
        if (rating > threshold) {
//            val manager = ReviewManagerFactory.create(context)
        val manager = FakeReviewManager(context)
            val request = manager.requestReviewFlow()
            request.addOnCompleteListener {
                if (it.isSuccessful) {
                    val reviewInfo = it.result
                    val flow = manager.launchReviewFlow(context, reviewInfo)
                    flow.addOnCompleteListener { _ ->
                        Log.d("999___", "review complete")
                        Toast.makeText(
                            context,
                            "Rating submitted successfully.",
                            Toast.LENGTH_SHORT
                        ).show()
                        // The flow has finished. The API does not indicate whether the user
                        // reviewed or not, or even whether the review dialog was shown. Thus, no
                        // matter the result, we continue our app flow.
                    }
                } else {
                    // redirect to playstore
                    Log.d("999___", "review redirected")
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=" + context.packageName)
                        )
                    )
                }
            }
        } else {
            Log.d("999___", "review in-complete")
            Toast.makeText(
                context,
                "Successful.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun changeBackgroundOfView(view: View, color: Int) {
        view.setBackgroundColor(color)
    }
    fun changeTextOfView(title: View, titleText: String) {
        (title as TextView).text = titleText
    }
}