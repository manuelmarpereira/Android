package manmarper.com.sqlitedatabasegeneric.DAL;

import manmarper.com.sqlitedatabasegeneric.Entities.UserEntity;
import manmarper.com.sqlitedatabasegeneric.Generic.DBMain;
import manmarper.com.sqlitedatabasegeneric.Generic.GenAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class UserDAL {

    public static final String TABLE_NAME = "users";
    public static final String NAME = "name";
    public static final String[] columns = new String[]{GenAdapter.KEY_ID, NAME};
    private Context myCtx;

    public UserDAL(Context context) {
        this.myCtx = context;
    }

    public static ContentValues getContentValues(Context mCtx, Object obj) {

        ContentValues values = new ContentValues();
        values.put(GenAdapter.KEY_ID, DBMain.getAggregate(mCtx, TABLE_NAME, GenAdapter.columnsCount, null) + 1);
        values.put(NAME, ((UserEntity) obj).getName());

        //returning the values
        return values;

    }

    public static ContentValues getContentValuesUpdate(Context mCtx, Object obj) {

        ContentValues values = new ContentValues();
        values.put(NAME, ((UserEntity) obj).getName());

        //returning the values
        return values;
    }

    public static UserEntity[] convert(Cursor c) {
        int count = c.getCount();
        int i = 0;
        UserEntity[] arr = new UserEntity[count];
        UserEntity single;

        c.moveToFirst();
        while (c.isAfterLast() == false) {
            single = new UserEntity(c.getInt(0), c.getString(1));
            arr[i] = single;
            i++;
            c.moveToNext();
        }
        c.close();

        return arr;
    }
}
