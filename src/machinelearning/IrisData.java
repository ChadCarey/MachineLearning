/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinelearning;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * handles the retrival and parsing of Iris machine learning data
 * into learning sets and test sets
 * @author chad
 */
public class IrisData {

    final private static String TEST_FILE = "testset.csv";
    final private static String LEARN_FILE = "learnset.csv";
    private ArrayList<IrisDataPoint> testSet;
    private ArrayList<IrisDataPoint> learnSet;
    
    /**
     * IrisData constructor
     * loads the Iris data from files. If the files don't exists we create them
     */
    public IrisData() {
        testSet = new ArrayList<IrisDataPoint>();
        learnSet = new ArrayList<IrisDataPoint>();
        if(filesExist())
            loadDataFiles();
        else {
            createDataFiles();
            loadDataFiles();
        }
    }

    /**
     * getTestSet
     * returns an ArrayList of the testSet
     */
    public ArrayList<IrisDataPoint> getTestSet() {
        return testSet;
    }
    
    /**
     * getTestSet
     * returns an ArrayList of the testSet
     */
    public ArrayList<IrisDataPoint> getLearnSet() {
        return learnSet;
    }

    /**
     * createDataFiles
     * creates the LEARN_FILE and TEST_FILE, 70% of the data does into the LEARN_FILE
     */
    private void createDataFiles() {
        // get the iris data from the database
        File data = null;
        try {
            data = getIrisData();
        } catch (MalformedURLException ex) {
            Logger.getLogger(IrisData.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(2);
        } catch (IOException ex) {
            Logger.getLogger(IrisData.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(3);
        }

        // read the data into an ArrayList, dataArray
        try {
            BufferedReader reader = new BufferedReader(new FileReader(data));
            ArrayList<String> dataArray = new ArrayList<String>();
            String line = "";
            while ((line = reader.readLine()) != null) {
                if(!line.isEmpty())
                    dataArray.add(line);
            }
            // remove a random 30% from dataArray and put it into the TEST_FILE
            File file = new File(TEST_FILE);
            if(file.exists())
                file.delete();
            file.createNewFile(); 
            FileWriter writer = new FileWriter(file);
            int count = 3*(dataArray.size()/ 10);
            for(int i = 0; i < count; ++i) {
                int randIndex = (int)(Math.random()*dataArray.size());
                writer.append(dataArray.get(randIndex)+"\n");
                dataArray.remove(randIndex);
            }
            writer.close();
            //  randomize the rest and put into the LEARN_FILE
            file = new File(LEARN_FILE);
            if(file.exists())
                file.delete();
            file.createNewFile(); 
            writer = new FileWriter(file);
            while(!dataArray.isEmpty()) {
                int randIndex = (int)(Math.random()*dataArray.size());
                
                writer.append(dataArray.get(randIndex)+"\n");
                dataArray.remove(randIndex);
            }
            writer.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(IrisData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IrisData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * getIrisData
     * This will get the main iris data file. It will be download the iris data
     * from the database if the file is not found locally
     * @return
     * @throws FileNotFoundException
     * @throws MalformedURLException
     * @throws IOException 
     */
    private File getIrisData() throws FileNotFoundException, MalformedURLException, IOException {
        File file = new File("irisData.csv");
        if (!file.exists()) {
            URL website = new URL("http://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("irisData.csv");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            file = new File("irisData.csv");
        }
        return file;
    }

    /**
     * filesExist
     * checks to see that both the LEARN_FILE and the TEST_FILE exist
     * @return 
     */
    private boolean filesExist(){
        File file = new File(TEST_FILE);
        boolean exists = true;
        if(!file.exists())
            return false;
        
        file = new File(LEARN_FILE);
        if(!file.exists())
            return false;
        return true;
    }
    
    private void loadDataFiles() {
        // TEST_FILE
        try {
            File file = new File(TEST_FILE);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = reader.readLine()) != null) {
                if(!line.isEmpty())
                    testSet.add(new IrisDataPoint(line));
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IrisData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IrisData.class.getName()).log(Level.SEVERE, null, ex);
        }
        // LEARN_FILE
        try {
            File file = new File(LEARN_FILE);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = reader.readLine()) != null) {
                if(!line.isEmpty())
                    testSet.add(new IrisDataPoint(line));
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IrisData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IrisData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}