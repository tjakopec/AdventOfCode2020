/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakopec.adventofcode2020.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tjakopec
 */
public class Start {

    private static final int MAX_RIGHT_NUMBER = 7;
    private char[][] grid;

    public Start() {
        populateGrid(readData());
        System.out.println("Step 1: " + findResultStep1());
        System.out.println("Step 2: " + findResultStep2());
    }

    private BigInteger findResultStep2() {
        BigInteger result = BigInteger.ONE;
        result=result.multiply(BigInteger.valueOf(sumTrees(1, 1)));
        result=result.multiply(BigInteger.valueOf(sumTrees(3, 1)));
        result=result.multiply(BigInteger.valueOf(sumTrees(5, 1)));
        result=result.multiply(BigInteger.valueOf(sumTrees(7, 1)));
        result=result.multiply(BigInteger.valueOf(sumTrees(1, 2)));
        return result;
    }

    private int findResultStep1() {
        return sumTrees(3, 1);
    }

    private int sumTrees(int rightNumber, int downNumber) {
        //System.out.println(rightNumber + "," + downNumber);
        int totalTrees = 0;
        int column = rightNumber + 1;
        for (int i = 0; i < grid.length; i = i + downNumber) {
            if (i + downNumber >= grid.length) {
                continue;
            }
            if (grid[i + downNumber][column - 1] == '#') {
                totalTrees++;
                //grid[i + downNumber][column - 1] = 'X';
            } else {
                //grid[i + downNumber][column - 1] = 'O';
            }
            column += rightNumber;
        }
        //printGrid();
         // System.out.println(totalTrees);  
        return totalTrees;
    }

    private void populateGrid(List<String> data) {
        int numberOfColumns = data.size() * MAX_RIGHT_NUMBER;
        int multiplyData = numberOfColumns / data.get(0).length();
        grid = new char[data.size()][data.get(0).length() * ++multiplyData];

        int row = 0;
        int column;
        for (String s : data) {
            column = 0;
            for (int i = 1; i <= multiplyData; i++) {
                for (int n = 0; n < s.length(); n++) {
                    if (column >= grid[0].length) {
                        continue;
                    }
                    grid[row][column++] = s.charAt(n);
                }
            }
            row++;
        }

        // printGrid();
    }

    private void printGrid() {
        for (char[] row : grid) {
            for (char element : row) {
                System.out.print(element);
            }
            System.out.println();
        }
        System.out.println("=================");
    }

    private List<String> readData() {
        List<String> data = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "day3.txt"));
            String line = reader.readLine();
            while (line != null) {
                data.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }

    public static void main(String[] args) {
        new Start();
    }

}
