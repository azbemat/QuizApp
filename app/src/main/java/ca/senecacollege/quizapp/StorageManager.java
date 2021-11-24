package ca.senecacollege.quizapp;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StorageManager {

    String filename = "results.txt";

    public void saveResultInFile(Activity activity, int result){

        FileOutputStream fos = null;

        try{
            fos = activity.openFileOutput(filename, Context.MODE_APPEND);
            fos.write(result);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                fos.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }

    }

    public ArrayList<Integer> getResultFromFIle(Activity activity){

        FileInputStream fis = null;
        int read;
        ArrayList<Integer> resultFromFile = new ArrayList<>();

        try{
            fis = activity.openFileInput(filename);
            while((read = fis.read()) != -1){
                resultFromFile.add(read);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                fis.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }

        return resultFromFile;
    }

    public void resetTheStorage(Activity activity){
        FileOutputStream fos = null;

        try{
            fos = activity.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(null);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                fos.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

}
