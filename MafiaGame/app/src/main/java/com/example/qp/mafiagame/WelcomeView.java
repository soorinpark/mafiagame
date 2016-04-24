package com.example.qp.mafiagame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class WelcomeView extends SurfaceView implements SurfaceHolder.Callback, View.OnClickListener {
    MainActivity mainActivity;
    WelcomeViewDrawThread welcomeViewDrawThread = null;

    Bitmap zuo;
    Bitmap you;

    int zuoX= 0;
    int zuoY = 0;
    int youX = 800;
    int youY = 0;

    public WelcomeView(MainActivity mainActivity) {
        super(mainActivity);
        this.mainActivity = mainActivity;
        getHolder().addCallback(this);
        welcomeViewDrawThread = new WelcomeViewDrawThread(this,getHolder());
        you = BitmapFactory.decodeResource(getResources(), R.drawable.you);
        zuo = BitmapFactory.decodeResource(getResources(), R.drawable.zuo);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(zuo, zuoX, zuoY, new Paint());
        canvas.drawBitmap(you, youX, youY,new Paint());
        this.setOnClickListener(this);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }
    public void surfaceCreated(SurfaceHolder holder) {
        welcomeViewDrawThread.setFlag(true);
        welcomeViewDrawThread.start();
    }
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        welcomeViewDrawThread.setFlag(false);
        while (retry) {
            try {
                welcomeViewDrawThread.join();
                retry = false;
            }
            catch (InterruptedException e) {
            }
        }
    }
    public void onClick(View v) {
        mainActivity.myHandler.sendEmptyMessage(1);
    }
}
