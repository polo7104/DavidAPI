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

    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date message_create;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;


}
