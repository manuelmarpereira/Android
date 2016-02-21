package manmarper.com.sqlitedatabasegeneric.Activities;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import manmarper.com.sqlitedatabasegeneric.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button to create new user
        Button create = (Button) findViewById(R.id.btn_create);
        create.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent createActivity = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(createActivity);

            }
        });

        //button to list all saved users
        Button list = (Button) findViewById(R.id.btn_list);
        list.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent listActivity = new Intent(MainActivity.this, ListActivity.class);
                startActivity(listActivity);

            }
        });


        //button to delete user
        Button delete = (Button) findViewById(R.id.btn_delete);
        delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent deleteActivity = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(deleteActivity);

            }
        });

        //button to edit user
        Button edit = (Button) findViewById(R.id.btn_edit);
        edit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent editActivity = new Intent(MainActivity.this, EditActivity.class);
                startActivity(editActivity);

            }
        });

    }

}
