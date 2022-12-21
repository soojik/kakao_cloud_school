package kakao.soo.java.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SetClass {
    public static void main(String[] args) throws IOException {

        // 저장 순서 알 수 없는 Set
        Set<String> set = new HashSet<>();

        // 데이터 추가
        System.out.println(set.add("hanni"));
        System.out.println(set.add("ning ning"));
        System.out.println(set.add("jennifer"));
        // 동일한 데이터 삽입 - equals 메서드로 비교 후, true가 리턴되면 삽입하지 않는다.
        // Set은 데이터 삽입에 실패하면 false를 리턴하고, 성공하면 true를 리턴
        System.out.println(set.add("ning ning"));

        System.out.println(set);

        // 로또 번호 생성기처럼 1~45 숫자 중 6개 입력받아 저장 후, 출력
        // 입력을 받을 때 1 부터 45 사이의 숫자가 아니면 다시 입력하도록
        // 중복 숫자는 다시 입력하도록
        // 데이터 출력할 때는 정렬 후 출력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("로또 번호 입력받기 with TreeSet");

        Set<Integer> treeSet = new TreeSet<>();
        while (treeSet.size() < 6){
            try {
                System.out.print("로또 번호 입력: ");
                int num = Integer.parseInt(br.readLine());
                // 먼저 num이 1~45 범위에 들어가는지 검사 후, false라면 뒤 조건은 검사하지 않도록
                // num의 숫자 범위가 맞다면 treeSet에 데이터 add
                // 여기서 false가 리턴되면 중복된 데이터라는 뜻으로 에러 메세지 출력

            /*
            if (!(1 <= num && num <= 45) || !treeSet.add(num)) {
                System.out.println("다시 입력하세요.");
            }
             */

                if ((1 <= num && num <= 45) == false) {
                    System.out.println("1 ~ 45 숫자만 입력바람");
                    continue;
                }

                if (treeSet.add(num) == false) {
                    System.out.println("중복 숫자 확인 바람");
                }
            } catch (Exception e) {
                System.out.println("숫자 입력바람");
            }
        }

        System.out.println(treeSet);

        Scanner sc = new Scanner(System.in);

        System.out.println("로또 번호 입력받기 with array");
        int len = 6;
        int[] lotto = new int[len];

        for (int i = 0; i < len; i++) {
            try {
                System.out.print("로또 번호 입력: ");
                lotto[i] = sc.nextInt();

                if (!(1 <= lotto[i] && lotto[i] <= 45)) {
                    System.out.println("1 ~ 45 숫자만 입력바람");
                    i--;
                    // 숫자 범위 맞지 않으면 중복검사 하지 않도록
                    continue;
                }

                for (int j = 0; j < i; j++) {
                    if (lotto[j] == lotto[i]) {
                        System.out.println("중복 숫자 확인 바람");
                        i--;
                        break;
                    }
                }

            } catch (Exception e) {
                // 숫자가 아닌 데이터를 입력받아 생긴 예외에서는 해당 입력은 카운트하면 안되기 때문에
                i--;
                // nextInt 후 엔터
                sc.nextLine();
                System.out.println("숫자 입력바람");
            }

        }

        sc.close();

        Arrays.sort(lotto);
        System.out.println(Arrays.toString(lotto));

    }
}
