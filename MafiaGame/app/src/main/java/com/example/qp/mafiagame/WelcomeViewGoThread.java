package com.example.qp.mafiagame;

/**
 * Created by vader on 2016/4/23.
 */
public class WelcomeViewGoThread extends Thread{
    int sleepSpan = 50;//睡眠的毫秒数
    private boolean flag = true;
    int status = 0;
    MainActivity mainActivity;
    public WelcomeViewGoThread(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }
    public void setFlag(boolean flag){
        this.flag = flag;
    }
    public void run() {
        while(flag){
            switch(status){
                case 0:
                    mainActivity.welcomeView.zuoX -= 15;
                    mainActivity.welcomeView.youX += 15;
                    if(mainActivity.welcomeView.zuoX<-540){
                        status = 1;
                    }
                    break;
                case 1:
                    this.flag = false;
                    mainActivity.myHandler.sendEmptyMessage(1);
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
