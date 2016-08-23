package com.api.chat.Beans;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by David on 2016-08-22.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Friends {

    @Id @GeneratedValue
    private Long friends_id;

    @Getter(AccessLevel.NONE)
    @ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private String friend;

    private String status;

    @Column(name = "friends_create")
    @Temporal(TemporalType.TIMESTAMP)
    private Date create;

    @Column(name = "friends_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date update;

}

