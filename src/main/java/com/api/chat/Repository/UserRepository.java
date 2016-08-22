package com.api.chat.Repository;

import com.api.chat.Beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by David on 2016-08-22.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
