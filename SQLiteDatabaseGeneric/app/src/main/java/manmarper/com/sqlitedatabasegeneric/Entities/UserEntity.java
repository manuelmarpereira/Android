package manmarper.com.sqlitedatabasegeneric.Entities;

public class UserEntity {

	private int id;
	private String name;
	
	
	public UserEntity(String name){
		super();
		this.name = name;
	}
	public UserEntity(int id, String name){
		super();
		this.id = id;
		this.name = name;
	}
	public int getId(){
		return this.id;
	}
	public String getName (){
		return this.name;
	}
	public void setId (int id){
		this.id=id;
	}
	public void setName(String name){
		this.name=name;
	}
	
}
