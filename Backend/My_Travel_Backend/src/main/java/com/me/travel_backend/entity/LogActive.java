package com.me.travel_backend.entity;

import com.me.travel_backend.entity.enumerate.LevelActive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "logActive")
@NoArgsConstructor
public class LogActive implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private  int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "messenger", nullable = false)
    private String messenger;

    @Enumerated(EnumType.ORDINAL)
    private LevelActive levelActive;

    @Temporal(TemporalType.TIMESTAMP)
    //@CreationTimestamp
    @Column(name = "`dateActive`", nullable = false)
    private Date dateActive;

}
