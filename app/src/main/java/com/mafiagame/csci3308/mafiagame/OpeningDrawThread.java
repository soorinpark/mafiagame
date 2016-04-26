package com.mafiagame.csci3308.mafiagame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * This is the drawing thread for the welcome page.The purpose of this thread is calling openingView's onDraw function in every 150 ms.
 * @author Qi Pei
 */
public class OpeningDrawThread extends Thread{
    private int sleepSpan = 150;
    private boolean flag = true;
    OpeningView openingView;
    SurfaceHolder surfaceHolder = null;
    public OpeningDrawThread(OpeningView openingView, SurfaceHolder surfaceHolder){
        this.openingView = openingView;
        this.surfaceHolder = surfaceHolder;
    }

    /**
     * This an override function from Thread. It calls the onDraw and keep the surface not changed.
     */
    public void run(){
        Canvas c;
        while(flag){
            c = null;
            try {
                c = surfaceHolder.lockCanvas(null);
                synchronized (this.surfaceHolder) {
                    try{
                        openingView.onDraw(c);
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

    /**
     * This is the function setting up the flag. If flag is changed from true to false then the Thread will be stoped.
     * @param flag
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
