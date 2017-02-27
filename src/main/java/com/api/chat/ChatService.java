package com.api.chat;

import com.api.chat.Domain.*;
import com.api.chat.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by David on 2016-09-07.
 */

@Service
public class ChatService {

    @Autowired UserRepository       userRepository;
    @Autowired RoomRepository       roomRepository;
    @Autowired FriendRepository     friendRepository;
    @Autowired MessageRepository    messageRepository;

    public User saveUser(User user){

        user.setCreate(new Date());
        user.setUpdate(new Date());

        User userResult = userRepository.save(user);

        return user;
    }

    public User findByUserName(String username) {

        User user = userRepository.findByUsername(username);

        return user;
    }

    public List<Friend> getFriends(User user){

        List<Friend> friendList = friendRepository.findByHost(user);

        return friendList;
    }

    public List<Room> getRooms(User user){

        List<Room> roomList = roomRepository.findByUserList(user);

        return roomList;
    }

    public List<String> getRegIds(){

        List<User> users = userRepository.findAll();
        List<String> ids = new ArrayList<>();

        for(User i : users){
            if(!i.getReg_id().isEmpty()){
                ids.add(i.getReg_id());
            }
        }

        return ids;
    }

    public String getSingleId(String username){

        User user = userRepository.findByUsername(username);

        String id = "";

        if(!user.getReg_id().isEmpty()){
            id = user.getReg_id();
        }
        return id;
    }


}
