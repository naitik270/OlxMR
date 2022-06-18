package developer.com.mr.olx.global;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class AnimationAutoTextScroller {


    Animation animator;
    TextView scrollingTextView;
    int       duration = 15000;  // default value

    public AnimationAutoTextScroller(TextView tv, float screenwidth) {

        this.scrollingTextView = tv;
        this.animator = new TranslateAnimation(
                Animation.ABSOLUTE, screenwidth,
                Animation.RELATIVE_TO_SELF, -1f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f
        );
        this.animator.setInterpolator(new LinearInterpolator());
        this.animator.setDuration(this.duration);
        this.animator.setFillAfter(true);
        this.animator.setRepeatMode(Animation.RESTART);
        this.animator.setRepeatCount(Animation.INFINITE);

        // setAnimationListener();
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setScrollingText(String text) {
        this.scrollingTextView.setText(text);
    }

    public void start() {
        this.scrollingTextView.setSelected(true);
        this.scrollingTextView.startAnimation(this.animator);
    }

    public void setAnimationListener() {

        animator.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }
            public void onAnimationEnd(Animation animation) {
                // This callback function can be used to perform any task at the end of the Animation
            }
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }


}
