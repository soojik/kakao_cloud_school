package kakao.soo.java.util;

class ShareData implements Runnable {
    private int result;
    private int idx;

    public int getResult() {
        return result;
    }

    private void sum() {
        for (int i = 0; i < 1000; i++) {
            // 이 영역 내에서는 다른 작업이 this할 수 없다
            synchronized (this) {
                idx++;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    result++;
                }
            }
        }
    }

    @Override
    public void run() {
        sum();
    }
}

public class MutexMain {
    public static void main(String[] args) {
        ShareData data = new ShareData();

        Thread thread1 = new Thread(data);
        thread1.start();

        Thread thread2 = new Thread(data);
        thread2.start();

        try {
            Thread.sleep(5000);
            System.out.println(data.getResult());
        } catch (InterruptedException e) {

        }
    }
}
