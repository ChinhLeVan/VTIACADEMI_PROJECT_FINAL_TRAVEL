package com.me.travel_backend.mapper;

import com.me.travel_backend.DTO.*;
import com.me.travel_backend.entity.LogActive;
import com.me.travel_backend.repository.IUserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor

public class LogActiveMapper {

    @Autowired
    IUserRepository userRepository;

    public LogActiveDTO toDTO(LogActive logActive){
        return new LogActiveDTO(
                logActive.getUser().getUserName(),
                logActive.getMessenger(),
                logActive.getLevelActive(),
                logActive.getDateActive());

    }

    public LogActive toEntity(LogActiveDTO logActiveDTO){
        LogActive logActive = new LogActive();
        logActive.setUser(userRepository.findByUserName(logActiveDTO.getUserName()));
        logActive.setMessenger(logActiveDTO.getMessenger());
        logActive.setLevelActive(logActiveDTO.getLevelActive());
        return  logActive;
    }
}
