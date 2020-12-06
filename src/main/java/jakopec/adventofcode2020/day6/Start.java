/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakopec.adventofcode2020.day6;

import jakopec.adventofcode2020.InputDataMultipleRow;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author tjakopec
 */
public class Start extends InputDataMultipleRow {

    public Start(String fileName) {
        super(fileName);
    }

    public void getResults() {
        System.out.println("Step 1: " + getResultStep1());
        System.out.println("Step 2: " + getResultStep2());
    }

    private int getResultStep1() {
        int total = 0;
        for (String string : data) {
            total += countStep1(string);
        }
        return total;
    }

    private int countStep1(String string) {
        return uniqueQuestions(string).size();
    }

    private int getResultStep2() {
        int total = 0;
        for (String string : data) {
            total += countStep2(string);
        }
        return total;
    }

    private Set<Character> uniqueQuestions(String string) {
        Set<Character> set = new HashSet<>();
        string = string.replace(" ", "");
        for (int i = 0; i < string.length(); i++) {
            set.add(string.charAt(i));
        }
        return set;
    }

    private int countStep2(String group) {
        int total = 0;
        boolean questionExist, exist;
        for (char c : uniqueQuestions(group)) {
            questionExist = true;
            for (String person : group.split(" ")) {
                exist = false;
                for (int i = 0; i < person.length(); i++) {
                    if (person.charAt(i) == c) {
                        exist = true;
                        break;
                    }
                }
                questionExist &= exist;
            }
            if (questionExist) {
                total++;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Start day6 = new Start("day6.txt");
        day6.getResults();
    }

}
