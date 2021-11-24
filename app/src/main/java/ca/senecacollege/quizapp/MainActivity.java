package ca.senecacollege.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.app.FragmentTransaction;
import android.app.FragmentManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int i = 0;
    int result = 0;

    ArrayList<Integer> allResultsFromFile = new ArrayList<Integer>();

    QuestionBank qb;
    StorageManager storageManager;
    ProgressBar progressBar;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qb = ((myApp) getApplication()).getQuestionBank();
        storageManager = ((myApp) getApplication()).getStorageManager();
        progressBar = findViewById(R.id.progressBar);
        getNewFragment();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.getTheAverage:
                Toast.makeText(MainActivity.this, "GET THE AVERAGE", Toast.LENGTH_LONG).show();

                // Get all Attempts from File
                allResultsFromFile = storageManager.getResultFromFIle(this);

                // calculate total from all attempts
                int totalScore = allResultsFromFile.stream().mapToInt(i -> i.intValue()).sum();


                builder = new AlertDialog.Builder(this);
                builder.setMessage( getString(R.string.s_yourCorrect) + " " + totalScore + " " + getString(R.string.s_in) + " " + allResultsFromFile.size() + " " +  getString(R.string.s_attempts));

                //Button : Save
                builder.setPositiveButton(R.string.s_save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });


                //Button : Ok
                builder.setNegativeButton(R.string.s_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Toast.makeText(MainActivity.this, "ok button Clicked!", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                break;

            case R.id.selectQuestion:
                Toast.makeText(MainActivity.this, "Feature Coming Soon", Toast.LENGTH_LONG).show();
                break;

            case R.id.reset:

                storageManager.resetTheStorage(this);
                break;

        }
        return true;
    }

    public void onTrueBtnCLicked(View view) {

        updateProgressBar();

        checkResult(true);

        if(!(qb.listOfQuestions.size()>i )) {
            QuizFinished();
        }else{
            getNewFragment();
        }

    }

    public void onFalseBtnClicked(View view) {

        updateProgressBar();

        checkResult(false);

        if(!(qb.listOfQuestions.size()>i )) {
            QuizFinished();
        }else{
            getNewFragment();
        }

    }

    public void getNewFragment(){

            FragmentManager fm = getFragmentManager();

            // Bundle
            Bundle bundle = new Bundle();
            int s = qb.listOfQuestions.get(i).getM_question();
            String c = getString(qb.listOfQuestions.get(i).getM_colour());
            bundle.putInt("question", s);
            bundle.putString("colour", c);

            FragmentTransaction transaction = fm.beginTransaction();
            FragmentL fragmentL = new FragmentL();
            fragmentL.setArguments(bundle);

            transaction.replace(R.id.frameLayout, fragmentL);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            transaction.commit();

            if (qb.listOfQuestions.size() > i)
                i++;

    }

    public void QuizFinished(){

        if(!(qb.listOfQuestions.size()>i )){

            builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.s_result);
            builder.setMessage( getString(R.string.s_yourscoreis) + " " + result + " " + getString(R.string.s_outof) + " " + qb.listOfQuestions.size());

            //Button : Save
            builder.setPositiveButton(R.string.s_save, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Save result in file
                    storageManager.saveResultInFile(MainActivity.this, result);

                    newAttempt();
                }
            });


            //Button : Ignore
            builder.setNegativeButton(R.string.s_ignore, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    newAttempt();

                    dialog.cancel();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    void newAttempt(){
        i = 0;
        result = 0;

        updateProgressBar();
        qb.shuffleQuesNColour();
        getNewFragment();

    }

    void checkResult(Boolean clickedButton){

        Boolean correctAnswer = qb.listOfQuestions.get(i-1).getM_answer();

        if(!(correctAnswer ^ clickedButton)){
            Toast.makeText(MainActivity.this, R.string.s_correct, Toast.LENGTH_LONG).show();
            result++;
        }else{
            Toast.makeText(MainActivity.this, R.string.s_incorrect, Toast.LENGTH_LONG).show();
        }

    }

    void updateProgressBar(){
        progressBar.setProgress(i);
    }


}