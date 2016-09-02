package com.api.chat.Repository;

import com.api.chat.Domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by David on 2016-09-02.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
}
