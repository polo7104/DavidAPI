package com.api.chat.Domain;

import lombok.*;

import javax.persistence.*;

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

    @ManyToMany(mappedBy = "friends")
    private User friend;
}
