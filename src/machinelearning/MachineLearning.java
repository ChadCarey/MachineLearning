/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinelearning;

import java.util.ArrayList;

/**
 *
 * @author chad
 */
public class MachineLearning {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<IrisDataPoint> learn = IrisData.getLearnSet();
        System.out.println(learn.size());
        ArrayList<IrisDataPoint> test = IrisData.getTestSet();
        System.out.println(test.size() + learn.size());
    }
    
}
