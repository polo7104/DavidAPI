package com.api.chat.Domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by David on 2016-08-22.
 */
@ToString(exclude = "password")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User implements Serializable{

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String username;

    @Size(max = 10, min = 4)
    private String password;

    private String reg_id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_create")
    private Date create;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_update")
    private Date update;

}
