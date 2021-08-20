package com.appyhigh.in_app_review;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class EmojiRatingBar extends androidx.appcompat.widget.AppCompatRatingBar {

    private int[] iconArrayActive =  {
            R.drawable.ic_star1_on,
            R.drawable.ic_star2_on,
            R.drawable.ic_star3_on,
            R.drawable.ic_star4_on,
            R.drawable.ic_star5_on
    };

    private int[] iconArrayInactive =  {
            R.drawable.ic_star1_off,
            R.drawable.ic_star2_off,
            R.drawable.ic_star3_off,
            R.drawable.ic_star4_off,
            R.drawable.ic_star5_off
    };

    private TextView msgview = null;

    public EmojiRatingBar(Context context) {
        super(context);
        init();
    }

    public EmojiRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmojiRatingBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setMax(5);
        this.setNumStars(5);
        this.setStepSize(1.0f);
        this.setRating(0f);
    }

    private Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        int stars = getNumStars();
        float rating = getRating();
        float x = 0;

        Bitmap bitmap;
        Resources res = getResources();
        Paint paint = new Paint();

        int W = getWidth();
        int H = getHeight();
        int icon_size = (W/stars)-32;//21 //(H < W)?(H):(W); //72
        if (icon_size > H-16) {
            icon_size = H-16;
        }
        int emoji_y_pos = (H/2)-icon_size/2;

        int delta = ((H > W)?(H):(W))/(stars);
        int offset = (W-(icon_size+(stars-1)*delta))/2;

        for(int i = 0; i < stars; i++) {
            if ((int) rating-1 >= i) {
                bitmap = getBitmapFromVectorDrawable(getContext(), iconArrayActive[i]);
            } else {
                bitmap = getBitmapFromVectorDrawable(getContext(), iconArrayInactive[i]);
            }
            x = offset+(i*delta);
            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, icon_size, icon_size, true);
            canvas.drawBitmap(scaled, x, emoji_y_pos, paint);
            canvas.save();
        }
    }


}
