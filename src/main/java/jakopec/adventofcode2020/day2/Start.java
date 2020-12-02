/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakopec.adventofcode2020.day2;

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

    private final List<String> data;
    private int min, max;
    private char letter;
    private String password;

    public Start() {
        data = new ArrayList<>();
        readData();
        System.out.println("Step 1: " + findResultStep1());
        System.out.println("Step 2: " + findResultStep2());
    }

    private int findResultStep1() {
        int total = 0;
        for (String s : data) {
            parse(s);
            if (validateStep1()) {
                total++;
            }
        }
        return total;
    }

    private boolean validateStep1() {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == letter) {
                count++;
            }
        }
        return count >= min && count <= max;
    }

    private void parse(String s) {
        min = Integer.parseInt(s.split(":")[0].split(" ")[0].split("-")[0]);
        max = Integer.parseInt(s.split(":")[0].split(" ")[0].split("-")[1]);
        letter = s.split(":")[0].split(" ")[1].charAt(0);
        password = s.split(":")[1].trim();
    }

    private int findResultStep2() {
        int total = 0;
        for (String s : data) {
            parse(s);
            if (validateStep2()) {
                total++;
            }
        }
        return total;
    }
    
    private boolean validateStep2() {
   
        return password.length()<max ? (password.charAt(min-1)==letter) : ((password.charAt(min-1)==letter || password.charAt(max-1)==letter) && 
                    (password.charAt(min-1)!=password.charAt(max-1)));
      
    }

    private void readData() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "day2.txt"));
            String line = reader.readLine();
            while (line != null) {
                data.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Collections.sort(data);
    }

    public static void main(String[] args) {
        new Start();
    }

}
