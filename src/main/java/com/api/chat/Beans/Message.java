package com.api.chat.Beans;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

//    @ManyToOne
//    @JoinColumn(name="username")
    private User sender;

//    @ManyToOne
//    @JoinColumn(name="username")
    private List<User> inRoomUsers = new ArrayList<>();

    private String message;



}
