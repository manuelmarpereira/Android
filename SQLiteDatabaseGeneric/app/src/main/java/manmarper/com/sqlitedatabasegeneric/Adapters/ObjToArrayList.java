package manmarper.com.sqlitedatabasegeneric.Adapters;



import java.util.ArrayList;

import manmarper.com.sqlitedatabasegeneric.Entities.UserEntity;


public class ObjToArrayList {


	//convert UserEntity to ArrayList and return the arraylist
	public static ArrayList<String> convertUserToArray (UserEntity[] user){
			
			ArrayList<String> str = new  ArrayList<String>();
	        //filling the array of strings with the countries list names
	        for (int i=0;i<user.length;i++){
	        	str.add(user[i].getName());
	        }
	        return str;
		}

}

