package com.example.ortalyona.game;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

public class MainThread extends Thread{

    MotionEvent event;

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;
//    private int targetFPS = 30;
//    private double averageFPS;


    public MainThread(SurfaceHolder surfaceHolder, GameView gameView){

        super();
        this.surfaceHolder=surfaceHolder;
        this.gameView=gameView;

    }

    public void setRunning(boolean isRunning){
        running=isRunning;
    }

    @Override
    public void run() {

        long waitTime = 3000;

        while(running){
            canvas=null;
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                this.gameView.update();
                this.gameView.draw(canvas);
                if (this.gameView.onTouchEvent(event)){
                    System.out.println("111");

                }
                }

            }catch (Exception e){
            }
            finally {
                if(canvas!=null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            waitTime = (waitTime*9500)/10000;

            try {
                this.sleep(waitTime);
            } catch (Exception e) {}

        }

    }



}
