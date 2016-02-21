package manmarper.com.sqlitedatabasegeneric.Activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import manmarper.com.sqlitedatabasegeneric.DAL.UserDAL;
import manmarper.com.sqlitedatabasegeneric.Entities.UserEntity;
import manmarper.com.sqlitedatabasegeneric.Generic.DBMain;
import manmarper.com.sqlitedatabasegeneric.R;

public class CreateActivity extends AppCompatActivity {
    private static final String TAG = "New user TAG: ";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Save").setIcon(android.R.drawable.ic_menu_save);
        menu.add(0, 2, 0, "Cancel").setIcon(android.R.drawable.ic_delete);
        return true;
    }

    // adding the options to the menu
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1://
                EditText name_user = (EditText) findViewById(R.id.txt_name);
                int flag = confirmNew(name_user);
                if (flag == 0) {
                    this.finish();
                } else {
                    name_user.setText("");
                }
                return true;
            case 2:
                Toast.makeText(this, "Canceled!", Toast.LENGTH_LONG).show();
                this.finish();
                return true;
        }

        return false;
    }

    private int confirmNew(EditText userName) {
        // TODO Auto-generated method stub
        String name = userName.getText().toString();
        // flag to see if the name is already on the database
        int flag = 0;

        if (name.trim().length() > 0) {

            Cursor c = DBMain.getAll(this, UserDAL.TABLE_NAME, UserDAL.columns);
            UserEntity[] users = UserDAL.convert(c);

            for (UserEntity user : users) {

                if ((name.compareTo(user.getName()) == 0)) {

                    //user name already exists on database
                    Toast.makeText(this, "The User '" + name + "' already exists in DB", Toast.LENGTH_LONG).show();
                    flag = 1;
                }
            }

            if (flag == 0) {

                // if the name doesn't exists on the DB, it creates a new user

                UserEntity new_user = new UserEntity(name);
                Log.i(TAG, "Creating or opening database [ " + new_user.getName() + " -  ]."); //create or open db
                DBMain.insert(this, UserDAL.TABLE_NAME, UserDAL.getContentValues(this, new_user)); // insert new user
                Toast.makeText(this, "User: " + name + " successfully saved!", Toast.LENGTH_LONG).show(); //show info
            }

        } else {
            Toast.makeText(this, "Insert the user name", Toast.LENGTH_LONG).show(); //show info
            flag = 1;
        }

        return flag;

    }
}
