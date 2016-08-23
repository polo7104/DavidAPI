package com.api.chat.Beans;

import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * Created by David on 2016-08-22.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Message {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User username;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> inRoomUsers;

    private String message;


    @Column(name = "message_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date create;

}
