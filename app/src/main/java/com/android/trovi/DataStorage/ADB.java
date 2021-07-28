package com.android.trovi.DataStorage;

import android.os.Environment;
import android.util.Log;

import com.android.trovi.Utils.Globals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//ADB		(Locate/TimeDB)				(3)
public class ADB {
    public void createADB(){

        String completePath = "adb."+Globals.LINE_LOCATION+"."+Globals.LINE_TIME+"/";

        String directory_path = Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Logs/ADB/"+completePath;

        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }

        File filea = new File(directory_path, "volumeCollect.txt");

        //Write to file
        try (FileWriter afileWriter = new FileWriter(filea,true)) {
            afileWriter.append(Globals.LINE_VOLUME+"\n");
        } catch (IOException e) {
            //Handle exception
        }

        File fileb = new File(directory_path, "brightCollect.txt");

        //Write to file
        try (FileWriter bfileWriter = new FileWriter(fileb,true)) {
            bfileWriter.append(Globals.LINE_BRIGHT+"\n");
        } catch (IOException e) {
            //Handle exception
        }

        File filec = new File(directory_path, "ringCollect.txt");

        //Write to file
        try (FileWriter cfileWriter = new FileWriter(filec,true)) {
            cfileWriter.append(Globals.LINE_RING+"\n");
        } catch (IOException e) {
            //Handle exception
        }

        readADB();
    }

    public void readADB(){

        List<Integer> listA = new ArrayList<Integer>();
        String mediaA = "";
        String medianaA = "";
        String modaA = "";

        List<Integer> listB = new ArrayList<Integer>();
        String mediaB = "";
        String medianaB = "";
        String modaB = "";

        List<Integer> listC = new ArrayList<Integer>();
        String modaC = "";

        String completePathA = "adb."+Globals.LINE_LOCATION+"."+Globals.LINE_TIME+"/volumeCollect.txt";
        String completePathB = "adb."+Globals.LINE_LOCATION+"."+Globals.LINE_TIME+"/brightCollect.txt";
        String completePathC = "adb."+Globals.LINE_LOCATION+"."+Globals.LINE_TIME+"/ringCollect.txt";

        FileInputStream is;
        BufferedReader reader;

        final File filea = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Logs/ADB/"+completePathA);

        final File fileb = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Logs/ADB/"+completePathB);

        final File filec = new File(Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Logs/ADB/"+completePathC);

        try{
            int numLine = 0;
            if (filea.exists()) {
                is = new FileInputStream(filea);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while(line != null){

                    listA.add(Integer.parseInt(line));

                    numLine++;
                    line = reader.readLine();
                }

                //Média
                mediaA = getMean(listA);

                //Mediana
                Collections.sort(listA);
                medianaA = getMedian(listA);

                //Moda
                modaA = getModa(listA);

            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        }

        try{
            int numLine = 0;
            if (fileb.exists()) {
                is = new FileInputStream(fileb);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while(line != null){

                    listB.add(Integer.parseInt(line));

                    numLine++;
                    line = reader.readLine();
                }

                //Média
                mediaB = getMean(listB);

                //Mediana
                Collections.sort(listB);
                medianaB = getMedian(listB);

                //Moda
                modaB = getModa(listB);

            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        }

        try{
            int numLine = 0;
            if (filec.exists()) {
                is = new FileInputStream(filec);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while(line != null){

                    listC.add(Integer.parseInt(line));

                    numLine++;
                    line = reader.readLine();
                }

                //Moda
                modaC = getModa(listC);

            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        }

        finishADB(mediaA,medianaA,modaA,mediaB,medianaB,modaB,modaC);
    }

    public void finishADB(String mediaA, String medianaA, String modaA, String mediaB,String medianaB, String modaB, String modaC){

        String completePath = "adb."+Globals.LINE_LOCATION+"."+Globals.LINE_TIME+"/";

        String directory_path = Environment.getExternalStorageDirectory().getPath()
                + "/Documents/Trovi/Users/Logs/ADB/"+completePath;

        //Create new Files with this data
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }

        File filea1 = new File(directory_path, "volumeMedia.txt");

        //Write to file
        try (FileWriter afileWriter = new FileWriter(filea1)) {
            afileWriter.append(mediaA);
        } catch (IOException e) {
            //Handle exception
        }

        File fileb1 = new File(directory_path, "volumeMediana.txt");

        //Write to file
        try (FileWriter bfileWriter = new FileWriter(fileb1)) {
            bfileWriter.append((medianaA));
        } catch (IOException e) {
            //Handle exception
        }

        File filec1 = new File(directory_path, "volumeModa.txt");

        //Write to file
        try (FileWriter cfileWriter = new FileWriter(filec1)) {
            cfileWriter.append((modaA));
        } catch (IOException e) {
            //Handle exception
        }

        File filea2 = new File(directory_path, "brightMedia.txt");

        //Write to file
        try (FileWriter afileWriter = new FileWriter(filea2)) {
            afileWriter.append((mediaB));
        } catch (IOException e) {
            //Handle exception
        }

        File fileb2 = new File(directory_path, "brightMediana.txt");

        //Write to file
        try (FileWriter bfileWriter = new FileWriter(fileb2)) {
            bfileWriter.append((medianaB));
        } catch (IOException e) {
            //Handle exception
        }

        File filec2 = new File(directory_path, "brightModa.txt");

        //Write to file
        try (FileWriter cfileWriter = new FileWriter(filec2)) {
            cfileWriter.append((modaB));
        } catch (IOException e) {
            //Handle exception
        }

        File filea3 = new File(directory_path, "ringModa.txt");

        //Write to file
        try (FileWriter afileWriter = new FileWriter(filea3)) {
            afileWriter.append((modaC));
        } catch (IOException e) {
            //Handle exception
        }
    }

    public String getMean(List<Integer> m) {
        double sum = 0;
        for (int i = 0; i < m.size(); i++) {
            sum += m.get(i);
        }
        int mean = (int) sum / m.size();
        return Integer.toString(mean);
    }

    public String getMedian(List<Integer> m) {
        int middle = m.size()/2;
        if (m.size()%2 == 1) {
            return Integer.toString(m.get(middle));
        } else {
            int median = (m.get(middle-1) + m.get(middle)) / 2;
            return Integer.toString((int) median);
        }
    }

    public String getModa(List<Integer> listA) {

        int maxValue=0, maxCount=0;

        for (int i = 0; i < listA.size(); ++i) {

            int count = 0;
            for (int j = 0; j < listA.size(); ++j) {
                if (listA.get(j) == listA.get(i)) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = listA.get(i);
            }
        }

        return Integer.toString(maxValue);
    }
}
