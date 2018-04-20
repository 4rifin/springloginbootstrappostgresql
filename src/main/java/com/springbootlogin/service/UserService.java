package com.springbootlogin.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootlogin.bean.UserBean;
import com.springbootlogin.dao.UserDao;
import com.springbootlogin.modal.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public List<User> findAll() {
		List<User> listUser = (List<User>) userDao.findAllByOrderByIdAsc();
		return listUser;
	}

	public User findId(long id) {

		return userDao.findByUserId(id);
	}
	
	public User findUserName(String userName) {

		return userDao.findByUserName(userName);
	}

	public void save(User User) {
			userDao.save(User);
	}

	public void deleteById(long id) {

		userDao.deleteById(id);
	}
	
	public User saveUser(UserBean param) {
		User user = new User();
		user.setUserName(param.getUserName());
		user.setEmail(param.getEmail());
		user.setPassword(param.getPassword());
		save(user);
		return user;
	}
	
	public User updateUser(long id,UserBean param){
		User user = findId(id);
		user.setPassword(param.getPassword());
		save(user);
		return user;
	}
	public Boolean isRecordFull(){
		List<User>listUser = (List<User>) userDao.findAll();
		if(listUser.size() >= 5){
			return true;
		}
		return false;
	}
	
	public boolean isUserNameExists(String userName) {
		User user = userDao.findByUserName(userName);
		if(user != null){
			return true;
		}
		return false;
	}
	
	public boolean isEmailExists(String email) {
		User user = findEmail(email);
		if(user != null){
			return true;
		}
		return false;
	}
	
	public User findEmail(String email) {

		return userDao.findByEmail(email);
	}
	
	public User findUserNameOrEmail(String userName) {
		User user = findUserName(userName);
		if(user == null){
			user = findEmail(userName);
		}
		return user;
	}
	
	public boolean isEmailUser(String email) {
		String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			return true;
		}
		return false;
	}
}
