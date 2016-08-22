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
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_name")
    private User user;

    private String friend;

    @Enumerated(EnumType.STRING)
    private Relationship status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date create;

    @Temporal(TemporalType.TIMESTAMP)
    private Date update;

}


enum Relationship {
        SINGLE, //  0
        COUPLE, //  1
        BLOCK,  //  2
        DEL     //  3
}
