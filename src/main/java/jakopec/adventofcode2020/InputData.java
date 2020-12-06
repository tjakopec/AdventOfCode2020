
package jakopec.adventofcode2020;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tjakopec
 */
public abstract class InputData {
    
    protected final String fileName;
    protected List<String> data;
    
    abstract void readData();

    public InputData(String fileName) {
        this.fileName=fileName;
        data=new ArrayList<>();
        doStuff();
    }

    private void doStuff() {
       readData();
    }

}
