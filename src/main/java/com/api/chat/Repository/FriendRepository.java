package com.api.chat.Repository;

import com.api.chat.Domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by David on 2016-08-26.
 */
@Repository
public interface FriendRepository extends JpaRepository<Friend, Long>{
}
