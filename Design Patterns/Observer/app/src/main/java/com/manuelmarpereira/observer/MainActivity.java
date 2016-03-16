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
    CheckBox checkPsg ;
    CheckBox checkChelsea ;
    TextView txtChelseaGoals;
    TextView txtPsgGoals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtChelseaGoals = (TextView) findViewById(R.id.txt_chelsea_goals);
        txtPsgGoals = (TextView) findViewById(R.id.txt_psg_goals);

        btnGoalChelsea = (Button) findViewById(R.id.btn_goal_chelsea);
        btnGoalChelsea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chelseaSubject.setGoal();
                updateGoals(txtChelseaGoals);

            }
        });

        btnGoalPsg = (Button) findViewById(R.id.btn_goal_psg);
        btnGoalPsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                psgSubject.setGoal();
                updateGoals(txtPsgGoals);

            }
        });


        checkChelsea = (CheckBox) findViewById(R.id.check_chelsea);
        checkChelsea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCheckChelsea();
            }
        });

        checkPsg = (CheckBox) findViewById(R.id.check_psg);
        checkPsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCheckPsg();
            }
        });

    }

    private void verifyCheckPsg(){
        if (checkPsg.isChecked()){
           psgObserver= new GoalObserver(psgSubject, MainActivity.this);
            Toast.makeText(MainActivity.this, "Follow "+psgSubject.getName(), Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(MainActivity.this, "Unfollow "+psgSubject.getName(), Toast.LENGTH_SHORT).show();
            psgSubject.remove(psgObserver);
        }
    }

    private void verifyCheckChelsea(){
        if (checkChelsea.isChecked()){
            chelseaObserver= new GoalObserver(chelseaSubject, MainActivity.this);
            Toast.makeText(MainActivity.this, "Follow "+chelseaSubject.getName(), Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(MainActivity.this, "Unfollow "+chelseaSubject.getName(), Toast.LENGTH_SHORT).show();
            chelseaSubject.remove(chelseaObserver);
        }
    }

    private void updateGoals(TextView team){

        int Goals = Integer.parseInt(team.getText().toString())+1;
        team.setText(""+Goals);

    }


}
