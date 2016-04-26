package com.mafiagame.csci3308.mafiagame;

/**
 * This is the running thread for the welcome page.The purpose of this thread is making movement of the pictures.
 * @author Qi Pei
 */
public class OpeningRunThread extends Thread{
    int sleepSpan = 150;
    private boolean flag = true;
    int status = 0;
    OpeningActivity openingActivity;

    /**
     * this is the constructor for this Thread. It accept an openingactivity and making changes about the drawing thread.
     * @param openingActivity
     */
    public OpeningRunThread(OpeningActivity openingActivity){
        this.openingActivity = openingActivity;
    }

    /**
     * This is the function setting up the flag. If flag is changed from true to false then the Thread will be stoped.
     * @param flag
     */
    public void setFlag(boolean flag){
        this.flag = flag;
    }

    /**
     * This an override function from Thread. The purpose of this function is to change the coordinates of the pictures. So the pictures can move arround.
     */
    public void run() {
        while(flag){
            switch(status){
                case 0:
                    openingActivity.openingView.riseUpY -=150;
                    if(openingActivity.openingView.riseUpY < -4000){
                        status = 1;
                    }
                    break;

                case 1:
                    openingActivity.openingView.fallDownY +=150;
                    if(openingActivity.openingView.fallDownY > 1920){
                        status = 2;
                    }
                    break;

                case 2:
                    openingActivity.openingView.zuoX -= 30;
                    openingActivity.openingView.youX += 30;
                    if(openingActivity.openingView.zuoX<-1000){
                        status = 3;
                    }
                    break;

                case 3:
                    this.flag = false;
                    openingActivity.myHandler.sendEmptyMessage(1);
                    break;
            }
            try{
                Thread.sleep(sleepSpan);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
