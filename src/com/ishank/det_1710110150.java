package com.ishank;

import java.util.*;

public class det_1710110150 {

    private static int rowLength = 0;
    private static int finalMedian = 0;
    private static int testCount = 0;
    private static List<Integer> leftList = new ArrayList<>();
    private static List<Integer> rightList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner inputTest = new Scanner(System.in);
        int testCases = inputTest.nextInt();            //input for test cases

        for (testCount = 0; testCount < testCases; testCount++){
            Scanner input2 = new Scanner(System.in);
            String arrayLength = input2.nextLine();         //input for length and rank to be found

            Scanner input = new Scanner(System.in);
            String sequence = input.nextLine();         //array input

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
        if (array.length%5 == 0)
            rowLength = array.length/5;
        else
            rowLength = (array.length/5) + 1;

        Integer[][] arrayStore = new Integer[rowLength][5];

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < 5; j++) {
                arrayStore[i][j] = null;
            }
        }           //assigning all the elements of the 2D array as null

        int count = 0;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j <5 ; j++) {
                if (count == array.length)
                    break;
                arrayStore[i][j] = array[count];
                count++;
            }
        }           //converting 1D input array into 2D array

        int[] median = new int[rowLength];
        int[] medianArray = new int[array.length%5];

        for (int i = 0; i < rowLength; i++) {

            if (!(arrayStore[i][1] == null || arrayStore[i][2] == null || arrayStore[i][3] == null || arrayStore[i][4] == null)){
                Arrays.sort(arrayStore[i]);
                median[i] = arrayStore[i][2];
            }
            else {
                for (int j = 0; j < array.length%5 ; j++) {
                    medianArray[j] = arrayStore[i][j];
                }
                Arrays.sort(medianArray);
                if (array.length%5 == 1)
                    median[i] = medianArray[0];
                else if (array.length%5 == 2)
                    median[i] = medianArray[0];
                else if (array.length%5 == 3)
                    median[i] = medianArray[1];
                else
                    median[i] = medianArray[1];
            }
        }

        if (median.length > 5)
            startRecursion(median,rank);

        else {
            Arrays.sort(median);
            //System.out.println(Arrays.toString(median));

            if (median.length == 1 || median.length ==2)
                finalMedian = median[0];
            else if (median.length == 3 || median.length == 4)
                finalMedian = median[1];

            int medianCount = 0;
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

            int medianPosition = leftList.size();

            if (rank < medianPosition){
                if (leftList.size()-1 > 5){
                    int[] leftArray = new int[leftList.size()-1];
                    for (int i = 0; i < leftList.size()-1; i++) {
                        leftArray[i] = leftList.get(i);
                    }
                    leftList.clear();
                    rightList.clear();
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
    }
}
