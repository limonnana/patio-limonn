package com.limonn.entityManager;

import java.util.List;

import com.limonn.entities.Settings;
import com.limonn.entities.User;
import com.limonn.services.Services;

public class UserManager {
	
	public static void saveUserAdmin(User u)
	{
		Services.saveUserAdmin(u);
	}
	
	public static User saveUser(User u)
	{
		return Services.saveUser(u);
	}
	
	public static void updateUser(User u)
	{
		Services.updateUser(u);
	}
	
	public static User getUserById(long id)
	{
		return Services.getUserById(id);
	}
	
	public static List<User> getUsersListByName(String name)
	{
		return Services.getUsersListByName(name);
	}
	public static List<User> getUsersListByLastName(String lastName)
	{
		return Services.getUsersListByLastName(lastName);
	}
	
	public static List getUsersListByPhone(String phone)
	{
		return Services.getUsersListByPhone(phone);
	}
	
	public static List<User> getUsersListByAddress(String address)
	{
		return Services.getUsersListByAddress(address);
	}
	
	public static User getUserByUsername(String username)
	{
		return Services.getUserByUsername(username);
	}
	
	public static List<User> getAllUsers()
	{
		return Services.getUsers();
	}
	
	public static void deleteUserById(long id)
	{
		Services.deleteUserById(id);
	}
    public Settings getSettings()
    {
    	return Services.getSettings();
    }
}
