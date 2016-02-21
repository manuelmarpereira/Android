package manmarper.com.sqlitedatabasegeneric.Activities;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import manmarper.com.sqlitedatabasegeneric.Adapters.ObjToArrayList;
import manmarper.com.sqlitedatabasegeneric.DAL.UserDAL;
import manmarper.com.sqlitedatabasegeneric.Entities.UserEntity;
import manmarper.com.sqlitedatabasegeneric.Generic.DBMain;
import manmarper.com.sqlitedatabasegeneric.R;

public class EditActivity extends ListActivity {

    private UserEntity[] user;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        //load users saved on db
        LoadListView();

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        final String item = (String) getListAdapter().getItem(position);

        LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.dialog_edit, null);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this); //AlertDialog creation
        dialog.setView(alertDialogView); //create a custom alertDialog
        dialog.setTitle("Update User"); //add alertDialog title
        dialog.setIcon(android.R.drawable.ic_menu_edit); //add alertDialog icon

        dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {

            //ok button
            public void onClick(DialogInterface dialog, int which) {

                EditText text_newName = (EditText) alertDialogView.findViewById(R.id.text_newName);

                for (UserEntity users : user) {
                    if (item.compareTo(users.getName()) == 0) {

                        //update item, in this case, the user name

                        UserEntity new_user = new UserEntity(text_newName.getText().toString());
                        DBMain.update(EditActivity.this, UserDAL.TABLE_NAME, users.getId(), UserDAL.getContentValuesUpdate(EditActivity.this, new_user));
                        LoadListView();// reload list
                        Toast.makeText(EditActivity.this, "User " + item + " Successefully Updated", Toast.LENGTH_LONG).show();// show success info
                    }
                }

            }
        });


        //cancel operation button
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                finish();   //finish activity exit
            }
        });


        dialog.show();
    }


    private void LoadListView() {

        //getting the items from the database, in this case the users list
        //converting from cursor to the object entity

        Cursor c2 = DBMain.getAll(this, UserDAL.TABLE_NAME, UserDAL.columns);
        user = UserDAL.convert(c2);

        ArrayList<String> str2;
        str2 = ObjToArrayList.convertUserToArray(user);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, str2);
        setListAdapter(adapter);
    }
}