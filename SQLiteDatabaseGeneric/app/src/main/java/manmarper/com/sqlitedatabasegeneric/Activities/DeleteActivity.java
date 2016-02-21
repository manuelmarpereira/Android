package manmarper.com.sqlitedatabasegeneric.Activities;

import java.util.ArrayList;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import manmarper.com.sqlitedatabasegeneric.Adapters.ObjToArrayList;
import manmarper.com.sqlitedatabasegeneric.DAL.UserDAL;
import manmarper.com.sqlitedatabasegeneric.Entities.UserEntity;
import manmarper.com.sqlitedatabasegeneric.Generic.DBMain;


public class DeleteActivity extends ListActivity {

    private UserEntity[] user;
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        LoadListView();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        //at click on item show dialog to delete it from database

        final String item = (String) getListAdapter().getItem(position);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Delete - '"+item+"'");
        alertDialog.setMessage("Confirm this operation?");// confirmations quest


        //yes
        alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
                // in this case delete user
                for (UserEntity aCat : user) {
                    if (item.compareTo(aCat.getName()) == 0) {
                        DBMain.deleteById(DeleteActivity.this, UserDAL.TABLE_NAME, aCat.getId());
                        LoadListView();
                        alertDialog.dismiss();
                        Toast.makeText(DeleteActivity.this, "User " + item + " successfully deleted", Toast.LENGTH_LONG).show();
                    }
                }
            }

        });

        //no
        alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //cancel and back
                alertDialog.dismiss();
            }

        });

        alertDialog.setIcon(android.R.drawable.ic_menu_delete);// dialog icon
        alertDialog.show(); // dialog show

    }


    private void LoadListView(){
        //---- getting the items from the database, in this case the users list -----//
        Cursor c2 = DBMain.getAll(this, UserDAL.TABLE_NAME, UserDAL.columns);
        // --- converting from cursor to the object entity! ----//
        user = UserDAL.convert(c2);

        ArrayList<String> str2;
        str2 = ObjToArrayList.convertUserToArray(user);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, str2);
        setListAdapter(adapter);
    }
}