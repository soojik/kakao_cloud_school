package kakao.soo.exceptionHandling;

public class ExceptionHandling02 {
    // 이 메서드를 다른 곳에서 호출할 때는 ArithmeticException 핸들링이 필요하다.
    // 실제로는 이 메서드 안에서 ArithmeticException이 발생할 만한 코드가 있어야 한다.
    public static void exception() throws ArithmeticException {
        int kor = 10;
        int eng = 120;

        if (kor > 100 || eng > 100) {
            throw new ArithmeticException("점수는 100보다 작거나 같아야 한다.");
        }

        double avg = (kor + eng) / 2;
        System.out.println(avg);
    }

    public static void main(String[] args) {
        exception();

        // Thread 클래스의 sleep이라는 메서드는 지정한 시간 동안 현재 스레드의 수행을 중지하는 역할을 해주는 메서드
        /* Thread.sleep()의 원형
        public static void sleep(long millis) throws InterruptedException
         */
        try {
            Thread.sleep(3000); // 3000 means 3 seconds
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
