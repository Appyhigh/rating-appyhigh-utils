package com.lodestar.inappreviewmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.appyhigh.in_app_review.EmojiRatingBar
import com.appyhigh.in_app_review.InAppReviewFlow

class MainActivity : AppCompatActivity() {
    lateinit var inapp: InAppReviewFlow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inapp = InAppReviewFlow(this)

        val rateNow = findViewById<TextView>(R.id.rate_now)
        val rateLater = findViewById<TextView>(R.id.rate_later)
        val ratingValue = findViewById<EmojiRatingBar>(R.id.rating_bar)
        val ratingTitle = findViewById<TextView>(R.id.ratingTitleTv)
        val ratingSubtitle1 = findViewById<TextView>(R.id.ratingMessageTv1)
        val imgDown = findViewById<ImageView>(R.id.imgDown)
        val imgUp = findViewById<ImageView>(R.id.imgUp)
        val ratingLike = findViewById<TextView>(R.id.ratingLike)
        val ratingDislike = findViewById<TextView>(R.id.ratingDislike)

        inapp.changeBackgroundOfView(rateNow, resources.getColor(R.color.colorPrimary))
        inapp.changeTextOfView(ratingTitle, "Enjoying ${getString(R.string.app_name)}?")
        inapp.changeTextOfView(ratingSubtitle1, "Rate us to share your feedback!")
        inapp.changeTextColorOfView(ratingLike, resources.getColor(R.color.colorPrimary))
        inapp.changeTextColorOfView(ratingDislike, resources.getColor(R.color.colorPrimary))
        inapp.changeTintOfArrow(imgDown,resources.getColor(R.color.colorPrimary))
        inapp.changeTintOfArrow(imgUp,resources.getColor(R.color.colorPrimary))

        rateNow.setOnClickListener {
            inapp.startInAppReviewFlow(ratingValue.rating, 4.0f)
        }
        rateLater.setOnClickListener {
            Toast.makeText(this, "Thanks for the feedback", Toast.LENGTH_SHORT).show()
        }
    }
}