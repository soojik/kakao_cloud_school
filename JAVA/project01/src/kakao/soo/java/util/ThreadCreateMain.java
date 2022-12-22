package kakao.soo.java.util;

// Thread 클래스로부터 사속받는 클래스
class ThreadEx extends Thread {

    // 오버라이딩 하는 함수는 되도록 어노테이션 붙여주는 것이 오탈자 잡기에 도움이 된다.
    @Override
    public void run() {
        // 1초마다 Thread 이름 출력
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(getName());
            } catch (InterruptedException e) {

            }
        }
    }
}

public class ThreadCreateMain {
    public static void main(String[] args) {

        // 클래스 상속받은 경우에 대부분, 변수 만들 때는 상위 클래스 이름을 사용
        // ThreadEx -> threadEx
        ThreadEx threadEx1 = new ThreadEx();
        threadEx1.start();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(i);
                    } catch (InterruptedException e) {

                    }
                }
            }
        };
        Thread th2 = new Thread(r);
        th2.start();

    }
}
