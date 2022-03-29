package com.me.travel_backend.service;

import com.me.travel_backend.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService extends UserDetailsService {

	User findUserByEmail(String email);
	User findUserByUserName(String username);

	boolean existsUserByEmail(String email);
	boolean existsUserByUserName(String userName);

	void createUser(User user);
	void activeUser(String token);
	void sendConfirmUserRegistrationViaEmail(String email);

	void resetPasswordViaEmail(String email);
	void resetPassword(String token, String newPassword);
	void sendResetPasswordViaEmail(String email);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	//void changeUserProfile(String username, ChangePublicProfileDTO dto);

}
