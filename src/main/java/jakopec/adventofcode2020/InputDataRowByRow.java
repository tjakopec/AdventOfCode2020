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
public class InputDataRowByRow extends InputData{

    public InputDataRowByRow(String fileName) {
        super(fileName);
    }

    @Override
    void readData() {
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
    
}
