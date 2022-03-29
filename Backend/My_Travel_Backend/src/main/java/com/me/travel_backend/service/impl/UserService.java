package com.me.travel_backend.service.impl;

import java.util.UUID;

import com.me.travel_backend.entity.User;
import com.me.travel_backend.entity.enumerate.UserStatus;
import com.me.travel_backend.entity.login.RegistrationUserToken;
import com.me.travel_backend.entity.login.ResetPasswordToken;
import com.me.travel_backend.event.OnResetPasswordViaEmailEvent;
import com.me.travel_backend.event.OnSendRegistrationUserConfirmViaEmailEvent;
import com.me.travel_backend.repository.IUserRepository;
import com.me.travel_backend.repository.login.RegistrationUserTokenRepository;
import com.me.travel_backend.repository.login.ResetPasswordTokenRepository;
import com.me.travel_backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private RegistrationUserTokenRepository registrationUserTokenRepository;
	@Autowired
	private ResetPasswordTokenRepository resetPasswordTokenRepository;
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	@Override
	public boolean existsUserByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public boolean existsUserByUserName(String userName) {
		return userRepository.existsByUserName(userName);
	}

	@Override
	public void createUser(User user) {
		// end code password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// save user
		userRepository.save(user);
		// create new user registration token
		createNewRegistrationUserToken(user);
		// send email to confirm
		sendConfirmUserRegistrationViaEmail(user.getEmail());
	}

	private void createNewRegistrationUserToken(User user) {
		// create new token for confirm Registration
		final String newToken = UUID.randomUUID().toString();
		RegistrationUserToken token = new RegistrationUserToken(newToken, user);
		registrationUserTokenRepository.save(token);
	}

	@Override
	public void sendConfirmUserRegistrationViaEmail(String email) {
		eventPublisher.publishEvent(new OnSendRegistrationUserConfirmViaEmailEvent(email));
	}

	@Override
	public void activeUser(String token) {
		RegistrationUserToken registrationUserToken = registrationUserTokenRepository.findByToken(token);
		//Update User
		User user = registrationUserToken.getUser();
		user.setStatus(UserStatus.ACTIVE);
		userRepository.save(user);
		//remove Registration User Token
		registrationUserTokenRepository.deleteByUserId(user.getId());
	}

	@Override
	public void resetPasswordViaEmail(String email) {
		// find user by email
		User user = findUserByEmail(email);
		// remove token token if exists
		resetPasswordTokenRepository.deleteByUserId(user.getId());
		// create new reset password token
		createNewResetPasswordToken(user);
		// send email
		sendResetPasswordViaEmail(email);
	}

	private void createNewResetPasswordToken(User user) {
		// create new token for Reseting password
		final String newToken = UUID.randomUUID().toString();
		ResetPasswordToken token = new ResetPasswordToken(newToken, user);
		resetPasswordTokenRepository.save(token);
	}

	@Override
	public void sendResetPasswordViaEmail(String email) {
		eventPublisher.publishEvent(new OnResetPasswordViaEmailEvent(email));
	}

	@Override
	public void resetPassword(String token, String newPassword) {
		// get token
		ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByResetToken(token);
		// change password
		User user = resetPasswordToken.getUser();
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
		// remove Reset Password
		resetPasswordTokenRepository.deleteById(resetPasswordToken.getId());
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Check user exists by username
		User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(
				user.getUserName(),
				user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRole().toString()));
	}

}
