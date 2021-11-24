package ca.senecacollege.quizapp;

import android.app.Application;

public class myApp extends Application {

    private QuestionBank questionBank = new QuestionBank();
    private StorageManager storageManager = new StorageManager();

    public QuestionBank getQuestionBank() {
        return questionBank;
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }

}
