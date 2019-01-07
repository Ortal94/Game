package com.example.ortalyona.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import java.util.Random;

public class Character {
        Canvas canvas=new Canvas();
        private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        int cx;
        int cy;
        Random r = new Random();

        public Character() {
            draw(canvas);
        }

        public void draw(Canvas canvas) {
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 0, 0));
            cx = r.nextInt(screenWidth);
            cy = r.nextInt(screenHeight);
            canvas.drawCircle(cx,cy,20,paint);

        }

        public void update() {


    }

    public void onTouch(float x, float y){
            if(x==cx && y==cy){
                System.out.println("touch");

            }
//            System.out.println("touch");

    }
}
