package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        valodateCodeThreadTset v=new valodateCodeThreadTset();
        valodateCodeThreadTset.OnResultListener onResultListener=new valodateCodeThreadTset.OnResultListener() {
            @Override//匿名内部类
            public void onResult(String result) {
                Scanner input=new Scanner(System.in);
                System.out.println("请输入验证码");
                String userCode=input.nextLine();
                System.out.println(result.equals(userCode));
            }
        };

        v.setOnResultListener(onResultListener);
        Thread thread=new Thread(v);
        thread.start();
    }

}
//此线程用于生成验证码
class valodateCodeThreadTset implements Runnable{
    private OnResultListener onResultListener;

    public void setOnResultListener(OnResultListener onResultListener) {
        this.onResultListener = onResultListener;//润方法之前完成，不为空。
    }

    private String code="23456789abcdefgABCDEFG";
    Random r=new Random();
    int number=4;//验证码长度
    StringBuffer sb=new StringBuffer(4);//存储验证码
    public void run(){
        for (int i=0;i<number;i++){
            int index=r.nextInt(code.length());
            sb.append(code.charAt(index));
        }//随机生成四个放在StringBuffer里

        System.out.println("验证码"+sb.toString());
        if(onResultListener!=null){
            onResultListener.onResult(sb.toString());
        }//监听器使用方法
    }
    interface OnResultListener{
        public void onResult(String result);
    }
}
