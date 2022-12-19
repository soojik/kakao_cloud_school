package kakao.soo.nestedclass;

public class Table {
    // 일련 번호
    private static int sequence;
    private static int step = 1;

    public static int getSequence() {
        return sequence;
    }

    public static void setSequence(int sequence) {
        Table.sequence = sequence;
    }

    public static int getStep() {
        return step;
    }

    public static void setStep(int step) {
        Table.step = step;
    }

    // 인스턴스별로 소유
    private int num;

    // auto_increment 적용
    public Table() {
        sequence += step;
        // set은 이미 설정해놨으니까 num에 대한 getter만 생성하면 될 것 같다.
        num = sequence;
    }

    public int getNum() {
        return num;
    }
}
