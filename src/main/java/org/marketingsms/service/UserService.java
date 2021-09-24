package org.marketingsms.service;

import org.marketingsms.model.User;
import org.marketingsms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

	private UserRepository userRepository;
	
    @Autowired
    public UserService(UserRepository userRepository) { 
      this.userRepository = userRepository;
    }
    
	public User findByEmail(String email) {
	return userRepository.findByEmail(email);
	}
	
	public User findByConfirmationToken(String confirmationToken) {
	return userRepository.findByConfirmationToken(confirmationToken);
	}
	
	public void saveUser(User user) {
	userRepository.save(user);
	}


}
