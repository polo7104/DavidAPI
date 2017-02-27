package com.api.chat.Repository;

import com.api.chat.Domain.Friend;
import com.api.chat.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by David on 2016-08-26.
 */
@Repository
public interface FriendRepository extends JpaRepository<Friend, Long>{

    List<Friend> findByHost(User host);
}
