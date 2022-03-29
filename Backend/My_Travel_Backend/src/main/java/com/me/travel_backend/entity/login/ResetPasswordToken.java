package com.me.travel_backend.entity.login;

import com.me.travel_backend.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Getter
@Setter
@Table(name = "`Reset_Password_Token`")
public class ResetPasswordToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "`id`", unique = true, nullable = false)
	private int id;

	@Column(name = "`resetToken`", nullable = false, length = 36, unique = true)
	private String resetToken;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "`expiryDate`", nullable = false)
	private Date expiryDate;

	public ResetPasswordToken() {
	}

	public ResetPasswordToken(String resetToken, User user) {
		this.resetToken = resetToken;
		this.user = user;

		// 1h
		expiryDate = new Date(System.currentTimeMillis() + 360000);
	}


}
