public class LoopPractice {
    public static void main(String[] args) {

        // ① 1〜10を表示
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }

        // ② 1〜100の合計
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println("1〜100の合計: " + sum);

        // ③ 2〜20の偶数だけ表示
        for (int i = 2; i <= 20; i += 2) {
            System.out.println("偶数: " + i);
        }
    }
}