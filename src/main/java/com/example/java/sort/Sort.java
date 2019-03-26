package com.example.java.sort;

import org.springframework.util.StopWatch;

public class Sort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7, 9, 88, 99, 2, 4, 6, 8, 10, 32, 321, 32, 3, 45, 65, 43, 1, 3241, 32, 14, 32, 5, 43, 11, 46, 65, 4, 24, 1, 32, 132, 143, 214, 3214};
        long l = System.nanoTime();
        BubbleSort(arr);
        long l1 = System.nanoTime();
        System.out.println(l1 - l + "纳秒");
        long l2 = System.nanoTime();
        QuicklySort(arr);
        long l3 = System.nanoTime();
        System.out.println(l3 - l2 + "纳秒");
    }



    private static void QuicklySort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    sawp(i, j, arr);
                }
            }
        }
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + ",");
//        }
    }

    private static void BubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    sawp(j, j + 1, arr);
                }
            }
        }
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + ",");
//        }
    }

    private static void sawp(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }
}
