package com.ishank;

import java.util.*;

public class rand_1710110150 {

    private static int rowLength = 0;
    private static int finalMedian = 0;
    private static int testCount = 0;
    private static List<Integer> leftList = new ArrayList<>();
    private static List<Integer> rightList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner inputTest = new Scanner(System.in);
        int testCases = inputTest.nextInt();

        for (testCount = 0; testCount < testCases; testCount++){
            Scanner input2 = new Scanner(System.in);
            String arrayLength = input2.nextLine();

            Scanner input = new Scanner(System.in);
            String sequence = input.nextLine();

            String[] splitArray2 = arrayLength.split(" ");
            int[] integerLength = Arrays.stream(splitArray2).mapToInt(Integer::parseInt).toArray();

            String[] splitArray = sequence.split(" ");
            int[] integerSequence = Arrays.stream(splitArray).mapToInt(Integer::parseInt).toArray();

            startRecursion(integerSequence,integerLength[1]);
            leftList.clear();
            rightList.clear();
        }
    }

    private static void startRecursion(int[] array,int rank) {
        int random = getRandom(array.length);
        while (true) {
            if ((random >= array.length/4) && (random < (3*array.length)/4)){
                //System.out.println(random);
                break;
            }
            else {
                random = getRandom(array.length);
            }
        }

        //System.out.println("random is " + random);

        int medianCount = 0;
        finalMedian = array[random];
        for (int value : array) {
            if (value != finalMedian) {
                if (value < finalMedian)
                    leftList.add(value);
                else
                    rightList.add(value);
            }
            else
                medianCount++;
        }
        for (int i = 0; i < medianCount; i++) {
            leftList.add(finalMedian);
        }

//        System.out.println("lists are ");
//        System.out.println(leftList);
//        System.out.println(rightList);

        int medianPosition = leftList.size();

        if (rank < medianPosition){
            if (leftList.size()-1 > 5){
                int[] leftArray = new int[leftList.size()-1];
                for (int i = 0; i < leftList.size()-1; i++) {
                    leftArray[i] = leftList.get(i);
                }
                leftList.clear();
                rightList.clear();
//                System.out.println("left Array is ");
//                System.out.println(Arrays.toString(leftArray));
                startRecursion(leftArray,rank);
            }
            else {
                Collections.sort(leftList);
                System.out.println(leftList.get(rank - 1));
            }
        }
        else if (rank > medianPosition){
            if (rightList.size() > 5){
                int[] rightArray = new int[rightList.size()];
                for (int i = 0; i < rightList.size(); i++) {
                    rightArray[i] = rightList.get(i);
                }
                leftList.clear();
                rightList.clear();
//                System.out.println("right Array is ");
//                System.out.println(Arrays.toString(rightArray));
                startRecursion(rightArray,rank-medianPosition);
            }
            else {
                Collections.sort(rightList);
                System.out.println(rightList.get(rank - medianPosition - 1));
            }
        }
        else
            System.out.println(finalMedian);
    }

    private static int getRandom(int max){
        return (int) (Math.random()*max);
    }
}
