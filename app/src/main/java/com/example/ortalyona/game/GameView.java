package com.example.ortalyona.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    Character character;
    private MainThread thread;
    int cx;
    int cy;
    Random r = new Random();


    public GameView(Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(),this);
        setFocusable(true);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        character = new Character();
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry){
            try{
                thread.setRunning(false);
                thread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            retry=false;
        }
    }
    public void update(){
        character.update();

    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if (canvas != null) {
            character.draw(canvas);

//            canvas.drawColor(Color.WHITE);
//            Paint paint = new Paint();
//            paint.setColor(Color.rgb(250, 0, 0));
//           // canvas.drawRect(100, 100, 200, 200, paint);
////            canvas.drawPoint(100,100,paint);
//            cx = r.nextInt(900)+100;
//            cy = r.nextInt(900);
//            canvas.drawCircle(cx,cy,20,paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        character.onTouch(x, y);
        return true;
    }
}
