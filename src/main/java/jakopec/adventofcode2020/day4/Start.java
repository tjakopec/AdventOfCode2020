/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakopec.adventofcode2020.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tjakopec
 */
public class Start {

    private final List<String> data;
    private final String fileName;
    private static final String[] REQUIRED = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    private static final Pattern HEXADECIMAL_PATTERN = Pattern.compile("\\p{XDigit}+");

    public Start(String fileName) {
        this.fileName = fileName;
        data = new ArrayList<>();
        readData();
        System.out.println("Step 1: " + findResultStep1());
        System.out.println("Step 2: " + findResultStep2());
    }

    private int findResultStep2() {
        int total = 0;
        for (String s : data) {
            if (validStep1(s) && validStep2(s)) {
                total++;
            }
        }
        return total;
    }

    private boolean validStep2(String pass) {
        boolean valid = true;
        String[] array = pass.split(" ");
        for (String r : REQUIRED) {
            for (String string : array) {
                if (!string.split(":")[0].trim().equals(r)) {
                    continue;
                }
                var value = string.split(":")[1].trim();
                switch (r) {
                    case "byr" ->
                        valid = valid && validByr(value);
                    case "iyr" ->
                        valid = valid && validIyr(value);
                    case "eyr" ->
                        valid = valid && validEyr(value);
                    case "hgt" ->
                        valid = valid && validHgt(value);
                    case "hcl" ->
                        valid = valid && validHcl(value);
                    case "ecl" ->
                        valid = valid && validEcl(value);
                    case "pid" ->
                        valid = valid && validPid(value);
                }
            }
        }
        return valid;
    }

    private boolean validByr(String value) {
        //byr (Birth Year) - four digits; at least 1920 and at most 2002.
        return validNumber(value, 1920, 2002);
    }

    private boolean validIyr(String value) {
        //iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        return validNumber(value, 2010, 2020);
    }

    private boolean validEyr(String value) {
        //eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
        return validNumber(value, 2020, 2030);
    }

    private boolean validNumber(String value, int min, int max) {
        try {
            int i = Integer.parseInt(value);
            if (i < min || i > max) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean validHgt(String value) {
        //hgt (Height) - a number followed by either cm or in:
        //If cm, the number must be at least 150 and at most 193.
        //If in, the number must be at least 59 and at most 76.
        if (value.contains("cm")) {
            return validNumber(value.replace("cm", ""), 150, 193);
        }
        if (value.contains("in")) {
            return validNumber(value.replace("in", ""), 59, 76);
        }
        return false;
    }

    private boolean validHcl(String value) {
        //hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
        if (value.charAt(0) != '#') {
            return false;
        }
        final Matcher matcher = HEXADECIMAL_PATTERN.matcher(value.substring(1));
        return matcher.matches();
    }

    private boolean validEcl(String value) {
        //ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
        boolean valid = false;
        switch (value) {
            case "amb", "blu", "brn", "gry", "grn", "hzl", "oth" ->
                valid = true;
        }
        return valid;
    }

    private boolean validPid(String value) {
        //pid (Passport ID) - a nine-digit number, including leading zeroes.
        if (value.length() != 9) {
            return false;
        }
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private int findResultStep1() {
        int total = 0;
        for (String s : data) {
            if (validStep1(s)) {
                total++;
            }
        }
        return total;
    }

    private boolean validStep1(String pass) {
        boolean valid;
        String[] array = pass.split(" ");
        for (String r : REQUIRED) {
            valid = false;
            for (String string : array) {
                if (string.split(":")[0].trim().equals(r)) {
                    valid = true;
                }
            }
            if (!valid) {
                return false;
            }
        }
        return true;
    }

    private void readData() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    fileName));
            String line = reader.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                if (line.trim().length() > 0) {
                    sb.append(line);
                    sb.append(" ");
                } else {
                    data.add(sb.toString().trim());
                    sb = new StringBuilder();
                }

                line = reader.readLine();
            }
            reader.close();
            if (sb.toString().trim().length() > 0) {
                data.add(sb.toString().trim());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Start("day4.txt");
    }

}
