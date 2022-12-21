package kakao.soo.java.util;

import java.util.Arrays;

public class QuickSortMain {
    static int[] data = {35, 77, 62, 14, 6, 99, 25, 83};
    // quick sort를 위한 메서드
    // left - 비교의 시작 위치
    // right - 비교의 반대편 끝 위치
    // data - 정렬할 배열
    public static void quickSort(int left, int right) {
        System.out.println(Arrays.toString(data));

        // 기준점 설정
        // 알고리즘 설명 할 때는 임의의 위치나 중앙이라고 하는데
        // 구현 시에는 제일 왼쪽을 기준으로 설정
        int pivot = left;

        // 큰 데이터 찾기 위한 인덱스
        int i = left + 1;
        // 작은 데이터 찾기 위한 인덱스는 right
        // 나중에 데이터를 교체하기 때문에 pivot의 위치를 저장
        int j = pivot;

        // 배열의 데이터가 2개 이상인 경우만 수행
        // 배열의 데이터가 1개이면 left와 right가 같아진다.
        if (left < right) {
            // 1회전 수행
            for (; i <= right; i++) {
                if (data[i] < data[pivot]) {
                    j++;
                    // i와 j의 데이터를 swap하는 코드
                    int temp = data[j];
                    data[j] = data[i];
                    data[i] = temp;
                }
            }
            // pivot 위치의 데이터를 자신의 위치로 이동
            int temp = data[left];
            data[left] = data[j];
            data[j] = temp;

            // pivot의 위치를 비교가 끝난 자리로 수정
            pivot = j;
            // pivot 기준 왼쪽
            quickSort(left, pivot - 1);
            // pivot 기준 오른쪽
            quickSort(pivot + 1, right);
        }
    }

    public static void main(String[] args) {
        quickSort(0, data.length-1);
        System.out.println("QuickSort 이용해 오름차순 정렬");
        System.out.println(Arrays.toString(data));
    }

}
