package beans;

import java.util.ArrayList;
import business.UserDTO;

public class AllUsersBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList <UserDTO> usuarios;
	private int numUsers;
	
	public ArrayList <UserDTO> getAllUsers()
	{
		return usuarios;
	}
	
	public void setAllUsers(ArrayList<UserDTO> users)
	{
		this.usuarios = users;
	}
	
	public int getNumUsers()
	{
		return numUsers;
	}
	
	public void setNumUsers(int num)
	{
		this.numUsers = num;
	}

}

