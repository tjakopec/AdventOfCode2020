/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakopec.adventofcode2020.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author tjakopec
 */
public class Start {

    private final List<String> data;
    private final String fileName;
    private final int MAX_ROW=128;
    private final char ROW_LOWER_LETTER = 'F';
    private final int MAX_COLUMN=8;
    private final char COLUMN_LOWER_LETTER = 'L';

    public Start(String fileName) {
        this.fileName = fileName;
        data = new ArrayList<>();
        readData();
        System.out.println("Step 1: " + findResultStep1());
        System.out.println("Step 2: " + findResultStep2());
    }
    
    private int findResultStep2() {
        List<Integer> seats = new ArrayList<>();
        for (String s : data) {
           seats.add(valueStep1(s));
        }
        Collections.sort(seats);
        
        //for (Integer seat : seats) {
        //    System.out.println(seat);
        //}
        
        for(int i=0;i<seats.size();i++){
            if(i+2>=seats.size()){
                break;
            }
            if(seats.get(i)+1 != seats.get(i+2)-1){
                return seats.get(i)+2;
            }
        }
        
        return 0;
    }

 

    private int findResultStep1() {
        int max = Integer.MIN_VALUE;
        for (String s : data) {
           // System.out.println(s);
            var value = valueStep1(s);
            if(value>max){
                max=value;
            }
        }
        return max;
    }
    
    private int valueStep1(String s){
         return rowStep1(s.substring(0,7)) * 8 + columnStep1(s.substring(7)) ;
    }
    
    private int rowStep1(String s){
        return algorithmStep1(s, MAX_ROW, ROW_LOWER_LETTER);
    }
    
   
    
    private int columnStep1(String s){
        return algorithmStep1(s, MAX_COLUMN, COLUMN_LOWER_LETTER);
    }
    
     private int algorithmStep1(String s, int max, char lowerLetter){
         //System.out.println(s);
        int array[] = new int[max];
        for(int i=0;i<max;i++){
            array[i]=i;
        }
        
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==lowerLetter){
                array = Arrays.copyOfRange(array, 0, array.length/2);
            }else{
                array = Arrays.copyOfRange(array, (array.length/2), array.length);
            }
         //   System.out.println(Arrays.toString(array));
        }
        // System.out.println(Arrays.toString(array));
        return array[0];
    }

 

    private void readData() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    fileName));
            String line = reader.readLine();
            while (line != null) {
                data.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Start("day5.txt");
    }

}
