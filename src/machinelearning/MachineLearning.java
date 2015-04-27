/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinelearning;

import weka.experiment.DatabaseUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.core.Instances;


/**
 *
 * @author chad
 */
public class MachineLearning {

    /**
     * starts the machine learning
     */
    public void run() {
        Instances learn = IrisData.getLearnSet();
        Instances test = IrisData.getTestSet();
        System.out.println("Number of instances in the learning set: " + learn.numInstances());
        System.out.println("Number of instances in the testing set: " + test.numInstances());
        MyClassifier classy = new MyClassifier();
        try {
            classy.buildClassifier(learn);
        } catch (Exception ex) {
            Logger.getLogger(MachineLearning.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(4);
        }
        
        try {
            classy.classifyInstances(test);
        } catch (Exception ex) {
            Logger.getLogger(MachineLearning.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MachineLearning machine = new MachineLearning();
        machine.run();
    }
    
}
