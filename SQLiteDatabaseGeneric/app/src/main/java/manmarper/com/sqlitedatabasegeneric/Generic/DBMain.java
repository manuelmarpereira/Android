package manmarper.com.sqlitedatabasegeneric.Generic;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DBMain {
	
	/* Using a generic adapter for the operations on the tables */
	private static GenAdapter adapter;

	public static Cursor getAll(Context CtxObj, String table, String[] columns) {

		//get all items on the table and return cursor
		adapter =  GenAdapter.getInstance(CtxObj);
		Cursor c =  adapter.get(table, columns, null, null, -1) ;
		close();
		return c;
	  }
	
	public static Cursor getById(Context CtxObj, String table, String[] columns, long id) {

		//get item by id
		adapter =  GenAdapter.getInstance(CtxObj);
		Cursor c =  adapter.get(table, columns, null, null, id) ;
		close();
		return c;
	  }

	public static Cursor get(Context CtxObj, String table, String[] columns, String where, String orderBy){

		//get items with filters
		adapter =  GenAdapter.getInstance(CtxObj);
		Cursor c =  adapter.get(table, columns, where, orderBy, -1) ;
		close();
		return c;
	}

	
	public static int deleteById(Context CtxObj, String table, long id) {

		//delete item from table by id
		adapter =  GenAdapter.getInstance(CtxObj);
		int i=  adapter.delete(table, null, id);
		close();
		return i;
	}

	public static int deleteWhere(Context CtxObj, String table, String where){

		//delete item from table with where clause
		adapter =  GenAdapter.getInstance(CtxObj);
		int i=  adapter.delete(table, where, -1);
		close();
		return i;
	}

	public static int deleteAll(Context CtxObj, String table){

		//delete all items on table
		adapter =  GenAdapter.getInstance(CtxObj);
		int i=  adapter.delete(table, null, -1);
		close();
		return i;
	}

	public static int update(Context CtxObj, String table, long id, ContentValues values) {

		//update item by id
		adapter =  GenAdapter.getInstance(CtxObj);
		int i=  adapter.update(table, id, values, null);
		close();
		return i;
	}
	

	public static int updateWhere(Context CtxObj, String table, ContentValues values, String where) {

		//update item with where clause
		adapter =  GenAdapter.getInstance(CtxObj);
		int i=  adapter.update(table, -1, values, where);
		close();
		return i;
	}


	public static double getAggregate(Context CtxObj, String table, String[] columnsAggregate, String where){

		//update aggregate items
		adapter =  GenAdapter.getInstance(CtxObj);
		Cursor c = adapter.get(table, columnsAggregate, where, null, -1);
		c.moveToFirst();
		double d = c.getInt(0);
		close();
		return d;
	}
	
	public static double getCount(Context CtxObj, String table, String where){

		//get items count
		adapter =  GenAdapter.getInstance(CtxObj);
		Cursor c = adapter.get(table, GenAdapter.columnsCount, where, null, -1);
		c.moveToFirst();
		double d = c.getInt(0);
		close();
		return d;
	}
	
	public static double getSum(Context CtxObj, String table, String where){

		adapter =  GenAdapter.getInstance(CtxObj);
		Cursor c = adapter.get(table, GenAdapter.columnsSum, where, null, -1);
		c.moveToFirst();
		double d = c.getInt(0);
		close();
		return d;
	}

	public static long insert(Context CtxObj, String table, ContentValues values){

		//insert item on table
		adapter =  GenAdapter.getInstance(CtxObj);
        return adapter.insert(table, values);
    }
	
	private static void close() {
		adapter.close();
	}

}
