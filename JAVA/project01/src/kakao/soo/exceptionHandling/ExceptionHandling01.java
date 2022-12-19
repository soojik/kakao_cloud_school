package kakao.soo.exceptionHandling;

public class ExceptionHandling01 {
    public static void main(String[] args) {
        // 0으로 나누기
        int i = 0;
        int j = 0;

        try {
            System.out.println(i / j);
            /*
            주의 : 상위 클래스의 예외처리 구문을 먼저 만들고
            아래에 하위 클래스의 예외처리 구문을 만들면 컴파일 에러
            아래 예외 처리 구문이 Dead Code가 된다.
             */
        } catch (ArithmeticException | NullPointerException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("예외 발생 여부 상관 없이 출력");
        }

        // 배열 인덱스 초과
        int[] arr = {0, 4, 2, 5};

        try {
            System.out.println(arr[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getLocalizedMessage());
        }

        // null 접근
    }
}
