package com.api.chat.Beans;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

/**
 * Created by David on 2016-08-22.
 */
@ToString(exclude = "password")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    @Size(max = 10, min = 4)
    private String password;

    private String reg_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create;

    @Temporal(TemporalType.TIMESTAMP)
    private Date update;

//    @OneToMany(mappedBy = "user", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Friends> friends = new ArrayList<>();


}
