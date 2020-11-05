import util.ArrayUtils;

public class Main {

    public static void main(String[] args) {
        test();
        int[] array = ArrayUtils.readIntArrayFromConsole();
        System.out.println(solution(array));
     //   System.out.println(JayKadaneAlgorithm(array));

    }

    public static long solution(int[] array) {
        if (array.length < 1) {
            //      System.out.println("Incorrect array");
            return 0;
        }
        long sum = 0;
        long max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (sum > max) max = sum;
            if (sum <= 0) sum = 0;     // если подмножество меньше нуля, то мы про него забываем
        }                              // и нам нет смысла рассматривать это подмножество дальше
        if (max < 0) return maxNegativeNumber(array);
        if (sum > max) return sum;
        return max;
    }

    public static long JayKadaneAlgorithm(int[] a) {      // честно взятый из интернета алгоритм
        long ans = 0,                                     // я не совсем понимаю, почему он работает,
                sum = 0;                                  // но он был взят для проверки
        for (int r = 0; r < a.length; ++r) {              // не работает, если все числа отрицательные
            sum += a[r];
            sum = Math.max(sum, 0);
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public static long maxNegativeNumber(int[] array) {
        long max = Long.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max && array[i] < 0) max = array[i];
        }
        return max;
    }

    public static void test() {

        // Возможно это невероятно глупо, но так как количество элементов массива нельзя изменять, я не знаю,
        // как сделать лучше. Но я думаю, что сборщик мусора умнее меня и лишние 10 массивов в памяти некритичны.

        int[] a = {};
        printArray(a);
        System.out.println("Ответ: " + solution(a));
        System.out.println("-----------------------------------------------------------------------------------------");

        int[] b = {1, 2, 3, 4};
        printArray(b);
        System.out.println("Ответ: " + solution(b));
        System.out.println("-----------------------------------------------------------------------------------------");

        int[] c = {0, 0, 0, 0, 0, 0, 0};
        printArray(c);
        System.out.println("Ответ: " + solution(c));
        System.out.println("-----------------------------------------------------------------------------------------");

        int[] d = {1000000000, 1000000000, 1000000000, 1000000000, 1000000000};
        printArray(d);
        System.out.println("Ответ: " + solution(d));
        System.out.println("-----------------------------------------------------------------------------------------");

        int[] e = {-1, 1, 2, -20, 30, 40, -70, 1};
        printArray(e);
        System.out.println("Ответ: " + solution(e));
        System.out.println("-----------------------------------------------------------------------------------------");

        int[] f = {-1, 2, -3, 4, -5, 6, -7, 8, 8, -10, 11};
        printArray(f);
        System.out.println("Ответ: " + solution(f));
        System.out.println("-----------------------------------------------------------------------------------------");

        int[] g = {-1};
        printArray(g);
        System.out.println("Ответ: " + solution(g));
        System.out.println("-----------------------------------------------------------------------------------------");

        int[] h = {-1, -2, -3, -4, 0, -12};
        printArray(h);
        System.out.println("Ответ: " + solution(h));
        System.out.println("-----------------------------------------------------------------------------------------");

        int[] i = {1, 2, 3, 4, -5, -4, 1, 2, 8};
        printArray(i);
        System.out.println("Ответ: " + solution(i));
        System.out.println("-----------------------------------------------------------------------------------------");

        int[] j = {-1, -2, -31234, -1235123};
        printArray(j);
        System.out.println("Ответ: " + solution(j));
        System.out.println("-----------------------------------------------------------------------------------------");


    }

    public static void printArray(int[] array) {
        System.out.println("Введенный массив:");
        if (array.length < 1) System.out.println("null");
        else {
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }


    /*
    метод, который находит максимальную сумму подряд идущих элементов
    */

    public static int wrongSolution(int[] array) {
    /*
        фактически это решение в один проход,
        так как в цикле while просто обходится последовательность подряд идущих одинаковых чисел,
        которая не включается в основной цикл
*/
        if (array.length < 2) return -1; // считаем, что сумма даже двух целых чисел не может быть равна -1
        if (isNumbersDifferent(array)) return -1;   // эта проверка осуществляется за N^2, но я не знаю, как лучше
        int temp = 0;
        boolean zeroException = false;  // есть ли последовательность нулей
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < array.length; i++) {
            while (array[i] == array[i - 1]) {
                if (array[i] == 0) zeroException = true;
                temp += array[i - 1];
                if (i == array.length - 1 && temp + array[i] >= max) {   // проверка на последнее число и
                    return temp + array[i];                              // максимальность последней последовательности
                } else if (i == array.length - 1 && temp + array[i] <= max) {
                    return max;
                }
                i++;
            }
            if ((temp != 0 || zeroException) && temp + array[i - 1] >= max) {
                max = temp + array[i - 1];
            }
            temp = 0;
            zeroException = false;
        }
        if (max == Integer.MIN_VALUE) return -1;
        return max;
    }

    public static boolean isNumbersDifferent(int[] array) {   // проверка на различие всех чисел
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) return false;
            }
        }
        return true;
    }


}
