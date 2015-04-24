/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinelearning;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author chad
 */
public class IrisDataPoint {
	private String dataClass;
	private ArrayList<Float> values;

	public IrisDataPoint(String dataLine) {
            values = new ArrayList<Float>();
            String[] items = dataLine.split(",");
            int i;
            for(i = 0; i < items.length-1; ++i) {
                values.add(Float.parseFloat(items[i]));
            }
            dataClass = items[i];
	}

	public String getDataClass(){
		return dataClass;
	}

	public ArrayList<Float> getValues() {
            return values;
	}

	public float getValue(int index) {
            return values.get(index);
	}
        
        public String getString() {
            String str = "";
            for(int i = 0; i < values.size(); ++i){
                str += values.get(i) + ",";
            }
            str += dataClass;
            return str;
        }

}
