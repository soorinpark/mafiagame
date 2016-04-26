package com.mafiagame.csci3308.mafiagame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * This is the Opening class. It implements SurfaceHolder and OnClickListener to hold the picture position and end the welcome part.
 * @author Qi Pei
 */
public class OpeningView extends SurfaceView implements SurfaceHolder.Callback, View.OnClickListener {
    OpeningActivity openingActivity;
    OpeningDrawThread openingDrawThread = null;

    Bitmap zuo;
    Bitmap you;
    Bitmap riseUp;
    Bitmap fallDown;
    Bitmap backimg;

    int zuoX= 0;
    int zuoY = 0;
    int youX = 540;
    int youY = 0;

    int riseUpY = 0;
    int fallDownY = 0;

    /**
     * This is the constructor of this class. It can initialize all the attributes.
     * @param openingActivity
     */
    public OpeningView(OpeningActivity openingActivity) {
        super(openingActivity);
        this.openingActivity = openingActivity;
        getHolder().addCallback(this);
        openingDrawThread = new OpeningDrawThread(this,getHolder());
        you = BitmapFactory.decodeResource(getResources(), R.drawable.you);
        zuo = BitmapFactory.decodeResource(getResources(), R.drawable.zuo);
        riseUp = BitmapFactory.decodeResource(getResources(),R.drawable.bak);
        fallDown = BitmapFactory.decodeResource(getResources(),R.drawable.freedom);
        backimg = BitmapFactory.decodeResource(getResources(),R.drawable.god_father);
    }

    /**
     * This is the drawing function. It initializes the positions of each picture.
     * @param canvas
     */
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(backimg, 0, 0, new Paint());
        canvas.drawBitmap(zuo, zuoX, zuoY, new Paint());
        canvas.drawBitmap(you, youX, youY,new Paint());
        canvas.drawBitmap(fallDown,-300,fallDownY,new Paint());
        canvas.drawBitmap(riseUp,-300,riseUpY,new Paint());

        this.setOnClickListener(this);
    }

    /**
     * This function create the surface and star the drawing thread.
     * @param holder
     */
    public void surfaceCreated(SurfaceHolder holder) {
        openingDrawThread.setFlag(true);
        openingDrawThread.start();
    }

    /**
     * This is an override function from surfaceHolder. Did use it in my code
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    /**
     * This is an override function from surfaceHolder. Did use it in my code
     * @param holder
     */
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        openingDrawThread.setFlag(false);
        while (retry) {
            try {
                openingDrawThread.join();
                retry = false;
            }
            catch (InterruptedException e) {
            }
        }
    }

    /**
     * If the user click the screen then the welcome part will stop.
     * @param v
     */
    public void onClick(View v) {
        openingActivity.myHandler.sendEmptyMessage(1);
    }
}
