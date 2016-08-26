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

//    @Column(unique = true)
    private String roomname;

    private List<String> inRoomUsers = new ArrayList<>();

//    @Setter(AccessLevel.NONE)
//    @OneToMany(mappedBy = "room")
//    private List<Message> messages = new ArrayList<Message>();

}
