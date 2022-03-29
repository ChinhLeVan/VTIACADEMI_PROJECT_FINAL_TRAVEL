package com.me.travel_backend.service;

public interface IEmailService {

	void sendRegistrationUserConfirm(String email);

	void sendResetPassword(String email);

}
