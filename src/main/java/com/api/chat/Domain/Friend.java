package com.api.chat.Domain;

import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * Created by David on 2016-08-26.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Friend {

    @Id @GeneratedValue
    @Column(name = "friend_id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_create")
    private Date create;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_update")
    private Date update;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private User guest;



}
