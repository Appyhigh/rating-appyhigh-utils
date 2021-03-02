# InAppReviewModule
# Installation

1. To import this library, Add the following line to your project's *build.gradle* at the end of repositories.
```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
2. To import this library, Add the following line to your app level *build.gradle* file.
```groovy
implementation 'com.github.Appyhigh:InAppReviewModule:1.01'(#Recommended latest version)
implementation 'me.zhanghai.android.materialratingbar:library:1.3.2'(#Recommended latest version)
```
3. To add the rating dialog include the *inapp_rating_dialog.xml* file in your activity's xml
```groovy
<include
    android:id="@+id/rating_dialog"
    layout="@layout/inapp_rating_dialog"/>
```
4. Iniialize InAppReviewFlow class in your activity's onCreate method.
```groovy
inapp = InAppReviewFlow(this)
```
5. If the layout text/color needs to be changed, initialize the views and call the respective methods.

Example:
```groovy
val rateNow = findViewById<TextView>(R.id.rate_now)
val rateLater = findViewById<TextView>(R.id.rate_later)
val ratingValue = findViewById<MaterialRatingBar>(R.id.rating_bar)
val ratingTitle = findViewById<TextView>(R.id.ratingTitleTv)
val ratingSubtitle1 = findViewById<TextView>(R.id.ratingMessageTv1)
val ratingSubtitle2 = findViewById<TextView>(R.id.ratingMessageTv2) 

inapp.changeBackgroundOfView(rateNow, resources.getColor(R.color.colorPrimary))
inapp.changeTextOfView(ratingTitle, "Enjoying ${getString(R.string.app_name)}?")
inapp.changeTextOfView(ratingSubtitle1, "We would love to hear from you!")
inapp.changeTextOfView(ratingSubtitle2, "How is your experience using our App?")
```
6. Start inAppReview flow by calling *startInAppReviewFlow(ratingValue, threshold)* method.

If ratingValue > threshold then inAppReview flow starts. 
```groovy
rateNow.setOnClickListener {
    inapp.startInAppReviewFlow(ratingValue.rating, 4.0f)
}
```
