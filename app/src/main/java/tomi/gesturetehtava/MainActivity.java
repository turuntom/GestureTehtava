package tomi.gesturetehtava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private GestureDetectorCompat mDetector;
    private TextView teksti;
    private ConstraintLayout cleiska;
    private boolean onoff;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GestureDetector myGestureListener = new GestureDetector(getApplicationContext(), new OmaGestureListener());
        onoff = true;
        teksti = (TextView)findViewById(R.id.textView);
        cleiska = (ConstraintLayout)findViewById(R.id.cleiska);

        cleiska.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return myGestureListener.onTouchEvent(event);
            }
        });

        cleiska.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    public class OmaGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            Log.d("onflinx", "onFling: velX: "+velocityX+" velY: "+velocityY);


            int redness = (int)Math.abs(velocityX);
            int blueness = (int)Math.abs(velocityY);

            cleiska.setBackgroundColor(Color.rgb(redness,0,blueness));

            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {

                if(onoff){
                    onoff = false;
                    cleiska.setBackgroundColor(Color.BLACK);
                }else{
                    onoff = true;
                    cleiska.setBackgroundColor(Color.WHITE);
                }


            return super.onDoubleTap(e);
        }
    }


}