package Threads;

public class Main {

    public static void main(String[] args) {
        MyRunnableClass myRunnableClass=new MyRunnableClass();
        myRunnableClass.massiveOneThread();
        try{myRunnableClass.massiveTwoThread();}catch (InterruptedException e){ e.printStackTrace();}
    }
}
