package com.example.springexample.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springexample.global.Globals;


/**
 * The Class UserDAO.
 */
@Service
public class UserDAO{

	/** The Constant logger. */
	private static final Logger logger = LogManager.getLogger(UserDAO.class);
	


	/** The user repository. */
	@Autowired
	UserRepository userRepository;
	
	/**
	 * Add new user in database.
	 *
	 * @param User
	 */
	public int addUser(User user)
	{
		if(Globals.userMap.isEmpty())
		{
			populateMapFromDatabase();
		}
		
		String upperCaseUsername = user.getUserName().toUpperCase();
		user.setUserName(upperCaseUsername);
		
		try
		{
			userRepository.save(user);
			Globals.userMap.put(user.getUserName(), user.getUserId());
			
			logger.info("User with userId "+ user.getUserId()+" is registered successfully into the database");
			return 1;
			
		}
		catch(Exception ex)
		{
			logger.error("Not able to save user "+user.getUserName()+" in database", ex);
			return 0;
		}
		
	}
	
	/**
	 * Populate map from database.
	 */
	public void populateMapFromDatabase()
	{
		List<User> allUsersList = getAllUser();
		if(null != allUsersList)
		{
			for(User user: allUsersList)
			{
				Globals.userMap.put(user.getUserName(), user.getUserId());
			}
		}
		
	}
	
	
	/**
	 * get list of all users.
	 *
	 * @return the all user
	 */
	public List<User> getAllUser() {
		try 
		{
			List<User> userList = new ArrayList<>();
			userRepository.findAll().forEach(userList::add);
			return userList;
		} 
		catch (Exception ex) 
		{
			logger.error("already registered users not found", ex);
			return null;
		}
	}

	
	/**
	 * find user from data base based on it's user id
	 *
	 * @param userId 
	 * @return the user by id
	 */
	public User getUserById(int userId) {
		
		try 
		{	
			
			Optional<User> userContainer = userRepository.findById(userId);
			return userContainer.get();
		} 
		catch (Exception ex) 
		{
			logger.error("no user found corressponding to userId: " + userId+" due to some error in database connectivity", ex);
			return null;
		}
	}

	/**
	 * Gets the user by user name.
	 *
	 * @param userName 
	 * @return the user 
	 */
	public User getUserByUserName(String userName)
	{
		System.out.println(" userMap = "+Globals.userMap);
		if(Globals.userMap.isEmpty())
		{
			populateMapFromDatabase();
		}
		
		Integer userId=Globals.userMap.get(userName);
		if(null != userId)
		{
			User user = getUserById(userId);
			if(null != user)
			{
				return user;
			}
			
			logger.error("no user found corressponding to userId: " + userId+" due to some error in database connectivity");
			return null;
			
		}
		
		logger.error("no user found corressponding to userId: " + userId);
		return null;
	}
	
	
	/**
	 * update user informations like password, name, age, email.....
	 *
	 * @param userName 
	 * @param updatedUser 
	 * @return the user
	 */
	@Transactional
	public User updateUser(String userName, User updatedUser) {

		if (Globals.userMap.isEmpty()) {
			populateMapFromDatabase();
		}
		
		try 
		{
			Integer userId = Globals.userMap.get(userName);
			if (null != userId) 
			{
				userRepository.updateUser(userId, updatedUser.getName(), updatedUser.getPassword(),
						updatedUser.getEmail(), updatedUser.getGender(), updatedUser.getAge());
				
				logger.info("User with userId "+ updatedUser.getUserId()+" is successfully updated into the database");
				
				return getUserByUserName(userName);
			}
			
			logger.error("unable to update user with userId "+userId+" as user doesn't exist in the database");
			return null;

		} 
		catch (Exception ex) 
		{
			logger.error("unable to update user with userId "+ updatedUser.getUserId() +" due to some issues with database connectivity", ex);
			
			return null;
		}

	}
	
	/**
	 * Delete user by id.
	 *
	 * @param userId 
	 */
	public int deleteUserById(int userId)
	{
		try
		{
			userRepository.deleteById(userId);
			return 1;
		}
		catch(Exception ex)
		{
			logger.error("unable to delete user with userId "+ userId +" due to some issues with database connectivity", ex);
			return 0;
		}
	}
	
	/**
	 * Delete user by name.
	 *
	 * @param userName 
	 * @return the int
	 */
	public int deleteUserByName(String userName)
	{
		if(Globals.userMap.isEmpty())
		{
			populateMapFromDatabase();
		}
		
		Integer userId = Globals.userMap.get(userName);
		if(null != userId)
		{
			int success = deleteUserById(userId);
			if(success == 1)
			{
				Globals.userMap.remove(userName);
				return 1;
			}
			else
			{
				return 0;
			}
			
		}
		
		logger.error("unable to delete user with username: "+userName+" as user doesn't exist in database");
		return 0;
		
		
	}

	
	
	/**
	 * Authenticate user for login
	 *
	 * @param user credentials
	 * @return the user
	 */
	public User authenticate( User user) {
		
		String upperCaseUsername = user.getUserName().toUpperCase();
		user.setUserName(upperCaseUsername);
		User DBUser = getUserByUserName(user.getUserName());
		if(DBUser != null)
		{
			if(DBUser.getPassword().equals(user.getPassword()))
			{
				//setStatusBitInDB(DBUser.getUserId()); 
				return DBUser;
			}
			else
			{
				logger.error("unable to authenticate user with userId "+user.getUserId()+" due to wrong userName/password is provieded by user");
				return null;
			}
		}
		
		logger.error("unable to authenticate user with userId "+user.getUserId()+" due to wrong userName/password is provieded by user");
		return null;
	}
		
	/*
	public void updateName(User updatedUser)
	{
		Integer userId = Globals.userMap.get(updatedUser.getUserName());
		userRepository.updateName(userId, updatedUser.getName());
	}
	public void updatePassword(User updatedUser)
	{
		Integer userId = Globals.userMap.get(updatedUser.getUserName());
		userRepository.updatePassword(userId, updatedUser.getPassword());
	}
	
	public void updateEmail(User updatedUser)
	{
		Integer userId = Globals.userMap.get(updatedUser.getUserName());
		userRepository.updateEmail(userId, updatedUser.getEmail());
	}
	
	public void updateAge(User updatedUser)
	{
		Integer userId = Globals.userMap.get(updatedUser.getUserName());
		userRepository.updateAge(userId, updatedUser.getAge());
	}
	
	public void updateGender(User updatedUser)
	{
		Integer userId = Globals.userMap.get(updatedUser.getUserName());
		userRepository.updateGender(userId, updatedUser.getGender());
	}
*/
	
	
}
