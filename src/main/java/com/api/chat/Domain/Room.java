package com.api.chat.Domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2016-08-25.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Room {

    @Id @GeneratedValue @Column(name = "room_id")
    private Long id;

    private String roomname;

    @Enumerated(EnumType.STRING)
    private RelationShip status;

    @ManyToMany
    @JoinTable(name = "ROOM_USER", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userList = new ArrayList<>();

}
