package kakao.soo.java.util;

public class ThreadMain {
    public static void main(String[] args) {
        /* 스레드를 사용하지 않은 경우
        new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {

                    try {
                        Thread.sleep(1000);
                        System.out.println(i);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }.run();
         */

        /* 스레드 사용한 경우
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    Thread.sleep(1000);
                    System.out.println(i);
                } catch (InterruptedException e) {

                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                try {
                    Thread.sleep(1000);
                    System.out.println(i);
                } catch (InterruptedException e) {

                }
            }
        }).start();
         */

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(getName());
                    } catch (InterruptedException e) {

                    }
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(getName());
                    } catch (InterruptedException e) {

                    }
                }
            }
        };

        Thread thread3 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(getName());
                    } catch (InterruptedException e) {

                    }
                }
            }
        };

        thread2.setPriority(Thread.MIN_PRIORITY);
        thread3.setPriority(Thread.MAX_PRIORITY);

        thread1.setDaemon(true);
        thread1.start();
        thread2.start();
        thread3.start();


//        NullPointerException 발생 -> main 중단
//        String str = null;
//        System.out.println(str.trim());

    }
}
