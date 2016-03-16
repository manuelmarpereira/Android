package com.manuelmarpereira.observer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GoalObserver psgObserver;
    GoalObserver chelseaObserver;

    TeamSubject psgSubject = new TeamSubject("PSG");
    TeamSubject chelseaSubject = new TeamSubject("Chelsea");

    Button btnGoalPsg;
    Button btnGoalChelsea;
    CheckBox checkPsg;
    CheckBox checkChelsea;
    TextView txtChelseaGoals;
    TextView txtPsgGoals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initialize text views for goals
        txtChelseaGoals = (TextView) findViewById(R.id.txt_chelsea_goals);
        txtPsgGoals = (TextView) findViewById(R.id.txt_psg_goals);


        //initialize  btn and events
        btnGoalChelsea = (Button) findViewById(R.id.btn_goal_chelsea);
        btnGoalChelsea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set goal chelsea
                chelseaSubject.setGoal();
                updateGoals(txtChelseaGoals);

            }
        });

        btnGoalPsg = (Button) findViewById(R.id.btn_goal_psg);
        btnGoalPsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set goal psg
                psgSubject.setGoal();
                updateGoals(txtPsgGoals);

            }
        });


        //initialize checkbox follow team and events
        checkChelsea = (CheckBox) findViewById(R.id.check_chelsea);
        checkChelsea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCheckedTeam(checkChelsea, chelseaObserver, chelseaSubject);
            }
        });

        checkPsg = (CheckBox) findViewById(R.id.check_psg);
        checkPsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCheckedTeam(checkPsg, psgObserver, psgSubject);
            }
        });

    }

    //verification if team is checked
    private void verifyCheckedTeam(CheckBox checkBoxTeam, Observer observer, TeamSubject teamSubject) {

        if (checkBoxTeam.isChecked()) {
            observer = new GoalObserver(teamSubject, MainActivity.this);
            Toast.makeText(MainActivity.this, "Follow " + teamSubject.getName(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Unfollow " + teamSubject.getName(), Toast.LENGTH_SHORT).show();
            teamSubject.remove(observer);
        }

    }

    private void updateGoals(TextView team) {

        int Goals = Integer.parseInt(team.getText().toString()) + 1;
        team.setText("" + Goals);

    }


}
