package com.example.qp.mafiagame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class WelcomeViewDrawThread extends Thread{
    private int sleepSpan = 50;
    private boolean flag = true;
    WelcomeView welcomeView;
    SurfaceHolder surfaceHolder = null;
    public WelcomeViewDrawThread(WelcomeView welcomeView,SurfaceHolder surfaceHolder){
        this.welcomeView = welcomeView;
        this.surfaceHolder = surfaceHolder;
    }

    public void run(){
        Canvas c;//画布
        while(flag){
            c = null;
            try {

                c = surfaceHolder.lockCanvas(null);
                synchronized (this.surfaceHolder) {
                    try{
                        welcomeView.onDraw(c);
                    }
                    catch(Exception e){}
                }
            } finally {
                if (c != null) {

                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
            try{
                Thread.sleep(sleepSpan);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void setFlag(boolean flag) {//设置循环标记
        this.flag = flag;
    }

}
