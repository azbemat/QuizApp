package ca.senecacollege.quizapp;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class FragmentL extends Fragment {

    TextView questionView;
    ConstraintLayout fragmentColor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_l, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        questionView = getView().findViewById(R.id.questionView);
        fragmentColor = getView().findViewById(R.id.fragment_background);

        Bundle bundle = this.getArguments();
        int question = bundle.getInt("question");
        String colour = bundle.getString("colour");

        questionView.setText(question);
        fragmentColor.setBackgroundColor(Color.parseColor(colour));
    }
}
