package com.me.travel_backend.repository.login;

import com.me.travel_backend.entity.login.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Integer> {
	
	public ResetPasswordToken findByResetToken(String token);

	public boolean existsByResetToken(String token);

	@Query("	SELECT 	resetToken	"
			+ "	FROM 	ResetPasswordToken "
			+ " WHERE 	user.id = :userId")
	public String findByUserId(@Param("userId") int userId);
	
	@Transactional
	@Modifying
	@Query("	DELETE 						" 
			+ "	FROM 	ResetPasswordToken 	" 
			+ " WHERE 	user.id = :userId")
	public void deleteByUserId(@Param("userId") int userId);

}
