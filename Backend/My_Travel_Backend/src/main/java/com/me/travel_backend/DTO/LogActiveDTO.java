package com.me.travel_backend.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.me.travel_backend.entity.LogActive;
import com.me.travel_backend.entity.User;
import com.me.travel_backend.entity.enumerate.LevelActive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogActiveDTO {

    private String userName;

    private String messenger;

    @Enumerated(EnumType.ORDINAL)
    private LevelActive levelActive;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dayActive;
}
