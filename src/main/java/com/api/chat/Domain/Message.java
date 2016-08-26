package com.api.chat.Domain;

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

    @Id @GeneratedValue @Column(name = "messge_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "room_id")
//    private Room room;

    private String message;

    @Column(name = "message_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date create;

}
