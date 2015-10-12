package com.limonn.actions;

import java.util.ArrayList;
import java.util.List;

import com.limonn.entities.User;
import com.limonn.entityManager.UserManager;
import com.limonn.settings.LimonnActionBean;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/SearchUser.action")
public class SearchUserActionBean extends LimonnActionBean{
	
	User user;
	List<User> userList = new ArrayList<User>();
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	
	public Resolution phone()
	{
		String phone = this.user.getPhone();
		List<User> usersListFromDb = UserManager.getUsersListByPhone(phone);
		
		this.userList.addAll(usersListFromDb);
		return new ForwardResolution("/Users.jsp");
	}
	
	public Resolution name()
	{
		String name = this.user.getName();
		List<User> userListFromDb = UserManager.getUsersListByName(name);
		this.userList.addAll(userListFromDb);
		return new ForwardResolution("/Users.jsp");
	}
	
	public Resolution lastName()
	{
		String lastName = this.user.getLastName();
		List<User> userListFromDb = UserManager.getUsersListByLastName(lastName);
		this.userList.addAll(userListFromDb);
		return new ForwardResolution("/Users.jsp");
	}
	
	public Resolution address()
	{
		String address = this.user.getAddress();
		List<User> userListFromDb = UserManager.getUsersListByAddress(address);
		this.userList.addAll(userListFromDb);
		return new ForwardResolution("/Users.jsp");
	}
	
	@DefaultHandler
	public Resolution getAllUsers()
	{
		this.userList.addAll(UserManager.getAllUsers());
		return new ForwardResolution("/Users.jsp");
	}
}
