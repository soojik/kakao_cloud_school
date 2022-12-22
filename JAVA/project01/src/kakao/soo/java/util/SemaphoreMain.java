package kakao.soo.java.util;

import java.util.concurrent.Semaphore;

class SemaphoreThread implements Runnable {

    private Semaphore semaphore;
    private String msg;

    public SemaphoreThread(Semaphore semaphore, String msg) {
        this.semaphore = semaphore;
        this.msg = msg;
    }

    @Override
    public void run() {
        try {
            // 공유 자원 사용 전에는 acquire 호출 필요
            semaphore.acquire();
            System.out.println(msg);
        } catch (InterruptedException e) {
            semaphore.release();
        }
    }
}

public class SemaphoreMain {
    public static void main(String[] args) {

        // 동시에 실행될 수 있는 스레드의 개수를 설정하는 클래스
        // 동시에 여러 개의 스레드가 수행될 상황에서 성능 저하를 막기 위해 사용
        Semaphore semaphore = new Semaphore(3);
        new Thread(new SemaphoreThread(semaphore, "1")).start();
        new Thread(new SemaphoreThread(semaphore, "2")).start();
        new Thread(new SemaphoreThread(semaphore, "3")).start();
        new Thread(new SemaphoreThread(semaphore, "4")).start();
    }
}
