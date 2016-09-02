package com.api.chat.Domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
//
//    @Setter(AccessLevel.NONE)
//    @ManyToMany(mappedBy = "friend")
//    private List<User> user = new ArrayList<>();
//
//    public void addUser(User user){
//        this.user.add(user);
//    }


}
