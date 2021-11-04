package Threads;

public class MyRunnableClass extends Thread {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;

    public void massiveOneThread() {
        float[] arr = new float[SIZE];
        for (float element : arr) {
            element = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.currentTimeMillis();
        System.out.println("arr[9 999 999]- " + arr[SIZE-1]);
        System.out.println("Один поток:" + (System.currentTimeMillis() - a));
    }

    public void massiveTwoThread() throws InterruptedException {
        float[] arr = new float[SIZE];
        for (float element : arr) {
            element = 1;
        }
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Первый поток start");
                for (int i = 0; i < HALF; i++) {
                    a1[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                System.out.println("Первый поток finish");
            }
        });
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Второй поток start");
                for (int i = 0; i < HALF; i++) {
                    a2[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                System.out.println("Второй поток finish");
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.arraycopy(a1, 0, arr, 0, HALF);
        System.arraycopy(a2, 0, arr, HALF, HALF);
        System.currentTimeMillis();
        System.out.println("arr[9 999 999]- " + arr[SIZE-1]);
        System.out.println("Два потока:" + (System.currentTimeMillis() - a));
    }

}




