import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> divideList = new ArrayList<Integer>();


        System.out.printf("Введите количество чисел в списке\n>>  ");
        int nums = input.nextInt();

        for (int i = 0; i < nums; i++) {
            System.out.printf("Введите значение элемента %d\n>>  ", i + 1);
            list.add(input.nextInt());
        }


        for (int i = 0; i < list.size(); i++) {
            try {
                divideList.add(100 / list.get(i));

                System.out.printf("100 / %d = %d\n", list.get(i), divideList.get(i));
            } catch (ArithmeticException e) {
                System.out.printf("Ошибка: %s", e);
            }
        }

        input.close();
    }
}