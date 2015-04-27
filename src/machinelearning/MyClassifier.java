/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinelearning;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author chad
 */
public class MyClassifier extends Classifier {

    private Instances learntData;
    
    @Override
    public void buildClassifier(Instances i) throws Exception {
        learntData = i;
    }
    
   @Override
   public double classifyInstance(Instance inst) throws Exception {
       System.out.println(learntData.firstInstance().attribute(4).name());
       return inst.numAttributes();
   }
   
   public void classifyInstances(Instances inst) throws Exception {
       for(int i = 0; i < inst.numInstances(); ++i) {
           classifyInstance(inst.instance(i));
       }
   }
}
