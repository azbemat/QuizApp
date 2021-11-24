package ca.senecacollege.quizapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable{

    int m_question;
    boolean m_answer;
    int m_colour;

    protected Question(Parcel in) {
        m_question = in.readInt();
        m_answer = in.readByte() != 0;
        m_colour = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getM_question() {
        return m_question;
    }

    public void setM_question(int m_question) {
        this.m_question = m_question;
    }

    public boolean getM_answer() {
        return m_answer;
    }

    public void setM_answer(boolean m_answer) {
        this.m_answer = m_answer;
    }

    public int getM_colour() {
        return m_colour;
    }

    public void setM_colour(int m_colour) {
        this.m_colour = m_colour;
    }

    public Question() {
        this.m_question = 0;
        this.m_answer = false;
        this.m_colour = 0;
    }

    public Question(int m_question, boolean m_answer, int m_colour) {
        this.m_question = m_question;
        this.m_answer = m_answer;
        this.m_colour = m_colour;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(m_question);
        parcel.writeByte((byte) (m_answer ? 1 : 0));
        parcel.writeInt(m_colour);
    }
}

