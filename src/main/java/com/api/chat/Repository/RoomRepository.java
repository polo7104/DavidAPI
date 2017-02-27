package com.api.chat.Repository;

import com.api.chat.Domain.Room;
import com.api.chat.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by David on 2016-08-26.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
    List<Room> findByUserList(User user);
}
