/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakopec.adventofcode2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author tjakopec
 */
public class InputDataMultipleRow extends InputData{

    public InputDataMultipleRow(String fileName) {
        super(fileName);
    }

    @Override
    void readData() {
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
    
}
