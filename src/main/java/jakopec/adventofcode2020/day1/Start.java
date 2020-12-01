/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakopec.adventofcode2020.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author tjakopec
 */
public class Start {

    private List<Integer> data;
    private static final int FIND=2020;

    public Start() {
        data = new ArrayList<>();
        readData();
        System.out.println("Step 1: " + findResultStep1());
        System.out.println("Step 2: " + findResultStep2());
        
    }
    
     private Integer findResultStep1() {
       for(int m: data){
           for(int n: data){
               if(m+n==FIND){
                   return m*n;
               }
           }
       }
       return 0;
    }
    
    private Integer findResultStep2() {
       for(int m: data){
           for(int n: data){
               for(int k: data){
               if(m+n+k==FIND){
                   return m*n*k;
               }
           }
               
           }
       }
       return 0;
    }

    private void readData() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "day1.txt"));
            String line = reader.readLine();
            while (line != null) {
                data.add(Integer.parseInt(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(data);
    }

    public static void main(String[] args) {
        new Start();
    }

    

}
