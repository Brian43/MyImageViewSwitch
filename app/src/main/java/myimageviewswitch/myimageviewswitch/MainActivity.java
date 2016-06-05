package myimageviewswitch.myimageviewswitch;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {
    private ImageView m_image1;
    private ImageView m_image2;
    private boolean isAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageView();
    }
    /*初始化*/
    public void initImageView(){
        m_image1 = (ImageView) findViewById(R.id.m_image1);
        m_image2 = (ImageView) findViewById(R.id.m_image2);
        m_image1.setImageResource(R.drawable.fun1);
        m_image2.setImageResource(R.drawable.fun3);
    }
    public void ChangeImageView(View view){
        change(m_image1,m_image2);
    }
    private void change(final ImageView target, final ImageView dest){
        /*使用buildDrawCache()方法建立DrawCache
        * 也可以使用setDeawingCacheEnable(true)*/
        target.buildDrawingCache();
        dest.buildDrawingCache();

        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(target.getWidth(),target.getHeight());
        target.setLayoutParams(ll);
        LinearLayout.LayoutParams ll2 = new LinearLayout.LayoutParams(dest.getWidth(),dest.getHeight());
        dest.setLayoutParams(ll2);

        TranslateAnimation targetts = new TranslateAnimation(0,dest.getLeft()-target.getLeft(),
                0,dest.getTop()-target.getTop());
        targetts.setDuration(300);
        targetts.setFillAfter(true);
        target.setAnimation(targetts);

        TranslateAnimation destts = new TranslateAnimation(0,target.getLeft()-dest.getLeft(),
                0,target.getTop()-dest.getTop());
        destts.setDuration(300);
        destts.setFillAfter(true);
        dest.setAnimation(destts);

        targetts.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimation = true;
                target.setVisibility(View.INVISIBLE);
                dest.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Bitmap iv1 = ((BitmapDrawable) dest.getDrawable()).getBitmap();
                Bitmap iv2 = ((BitmapDrawable) target.getDrawable()).getBitmap();
                dest.setVisibility(View.VISIBLE);
                target.setVisibility(View.VISIBLE);
                dest.setImageBitmap(iv2);
                target.setImageBitmap(iv1);

                isAnimation = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}
