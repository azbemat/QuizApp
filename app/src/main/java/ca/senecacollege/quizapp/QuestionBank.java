package ca.senecacollege.quizapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionBank {

    public ArrayList<Question> listOfQuestions;

    public QuestionBank (){
        this.listOfQuestions = new ArrayList<>(8);

        this.listOfQuestions.add(new Question(R.string.s_que1,true,R.color.c_clr1));
        this.listOfQuestions.add(new Question(R.string.s_que2,false,R.color.c_clr2));
        this.listOfQuestions.add(new Question(R.string.s_que3,true,R.color.c_clr3));
        this.listOfQuestions.add(new Question(R.string.s_que4,false,R.color.c_clr4));
        this.listOfQuestions.add(new Question(R.string.s_que5,true,R.color.c_clr5));
        this.listOfQuestions.add(new Question(R.string.s_que6,false,R.color.c_clr6));
        this.listOfQuestions.add(new Question(R.string.s_que7,true,R.color.c_clr7));
        this.listOfQuestions.add(new Question(R.string.s_que8,false,R.color.c_clr8));

    }

    public void shuffleQuesNColour(){

        int[] colours = new int[listOfQuestions.size()];

        for(int i = 0; i < listOfQuestions.size(); i++)
            colours[i] = listOfQuestions.get(i).getM_colour();

        shuffleColour(colours, colours.length);

        Collections.shuffle(listOfQuestions);

        for(int i = 0; i < listOfQuestions.size(); i++){
            listOfQuestions.get(i).setM_colour(colours[i]);
        }


    }

    static void shuffleColour( int array[], int a)
    {
        Random rd = new Random();

        for (int i = a-1; i > 0; i--) {

            int j = rd.nextInt(i+1);

            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

}



