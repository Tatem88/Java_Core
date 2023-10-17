package Lesson_2;

public class Main {

    public static boolean hasZeroNeighbors(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0 && arr[i - 1] == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 0, 0, 3, 3};
        int[] arr2 = {2, 0, 2, 0, 2};

        System.out.println(hasZeroNeighbors(arr1));
        System.out.println(hasZeroNeighbors(arr2));
    }
}
