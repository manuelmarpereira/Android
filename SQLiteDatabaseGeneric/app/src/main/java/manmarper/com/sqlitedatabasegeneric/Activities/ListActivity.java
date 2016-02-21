package manmarper.com.sqlitedatabasegeneric.Activities;

import java.util.ArrayList;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import manmarper.com.sqlitedatabasegeneric.Adapters.ObjToArrayList;
import manmarper.com.sqlitedatabasegeneric.DAL.UserDAL;
import manmarper.com.sqlitedatabasegeneric.Entities.UserEntity;
import manmarper.com.sqlitedatabasegeneric.Generic.DBMain;

public class ListActivity extends android.app.ListActivity {

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		//getting the items from the database, in this case the users list
		Cursor c2 = DBMain.getAll(this, UserDAL.TABLE_NAME, UserDAL.columns);
		//converting from cursor to the object entity
		UserEntity[] user = UserDAL.convert(c2);

		ArrayList<String> str2;
		str2 = ObjToArrayList.convertUserToArray(user);

		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, str2);
		setListAdapter(adapter);

	}


}