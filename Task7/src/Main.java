import util.ArrayUtils;

public class Main {

    public static void main(String[] args) {
        int[] array = ArrayUtils.readIntArrayFromConsole();
        System.out.println(solution(array));
        // System.out.println(JayKadaneAlgorithm(array));
        // можно написать System.out.println(solution(ArrayUtils.readIntArrayFromConsole()))
        // но я думаю это некрасиво (возможно я не прав)
    }

    public static int solution(int[] array) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (sum > max) max = sum;
            if (sum <= 0) sum = 0;     // если подмножество меньше нуля, то мы про него забываем
        }                              // и нам нет смысла рассматривать это подмножество дальше
        if (max < 0) return maxNegativeNumber(array);
        if (sum > max) return sum;
        return max;
    }

    public static int JayKadaneAlgorithm(int[] a) {      // честно взятый из интернета алгоритм
        int ans = a[0],                                  // я не совсем понимаю, почему он работает,
                sum = 0;                                 // но он был взят для проверки
        for (int r = 0; r < a.length; ++r) {             // не работает, если все числа отрицательные
            sum += a[r];
            sum = Math.max(sum, 0);
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    public static int maxNegativeNumber(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max && array[i] < 0) max = array[i];
        }
        return max;
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
