package com.lodestar.inappreviewmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.appyhigh.in_app_review.InAppReviewFlow
import me.zhanghai.android.materialratingbar.MaterialRatingBar

class MainActivity : AppCompatActivity() {
    lateinit var inapp: InAppReviewFlow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inapp = InAppReviewFlow(this)

        val rateNow = findViewById<TextView>(R.id.rate_now)
        val rateLater = findViewById<TextView>(R.id.rate_later)
        val ratingValue = findViewById<MaterialRatingBar>(R.id.rating_bar)
        val ratingTitle = findViewById<TextView>(R.id.ratingTitleTv)
        val ratingSubtitle1 = findViewById<TextView>(R.id.ratingMessageTv1)
        val ratingSubtitle2 = findViewById<TextView>(R.id.ratingMessageTv2)

        inapp.changeBackgroundOfView(rateNow, resources.getColor(R.color.colorPrimary))
        inapp.changeTextOfView(ratingTitle, "Enjoying ${getString(R.string.app_name)}?")
        inapp.changeTextOfView(ratingSubtitle1, "Enjoying ${getString(R.string.app_name)}? 111")
        inapp.changeTextOfView(ratingSubtitle2, "Enjoying ${getString(R.string.app_name)}? 222")

        rateNow.setOnClickListener {
            inapp.startInAppReviewFlow(ratingValue.rating, 4.0f)
        }
        rateLater.setOnClickListener {
            Toast.makeText(this, "Thanks for the feedback", Toast.LENGTH_SHORT).show()
        }


    }
}