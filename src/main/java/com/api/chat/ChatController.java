package com.api.chat;

import com.api.chat.Beans.Friends;
import com.api.chat.Beans.User;
import com.api.chat.Repository.FriendsRepository;
import com.api.chat.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.lang.reflect.Array;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by David on 2016-08-22.
 */

@Slf4j
@RestController
@RequestMapping(value = "/chat")
@Transactional
@CrossOrigin
public class ChatController {

    @Autowired UserRepository       userRepository;
    @Autowired FriendsRepository    friendsRepository;



    @RequestMapping(value = "/signup", method = POST,  produces  = "application/json")
    public ResponseEntity signUp(@RequestBody @Valid User user, BindingResult result){

        if(result.hasErrors()){
            log.error("[ERROR]"+result.getAllErrors());
            return new ResponseEntity("[ERROR]"+result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        user.setCreate(new Date());
        user.setUpdate(new Date());

        User userResult = userRepository.save(user);


        return new ResponseEntity<>(userResult, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/search/{username}", method = GET)
    public ResponseEntity searchOne(@PathVariable String username){

        if(username == null){
            return new ResponseEntity<>("Nothing", HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByUsername(username);
        //


        return new ResponseEntity(user, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/add/{id}/friend/{friend}", method = GET)
    public ResponseEntity addFriend(@PathVariable("id") Long id, @PathVariable("friend") String str_friend){

        if(id == null || str_friend == null){
            return new ResponseEntity<>("Nothing", HttpStatus.BAD_REQUEST);
        }

        Friends friends = new Friends();

        User user = userRepository.findOne(id);

        if(user == null){
            return new ResponseEntity<>("user is null", HttpStatus.BAD_REQUEST);
        }

        friends.setUser(user);
        friends.setFriend(str_friend);
        friends.setCreate(new Date());
        friends.setUpdate(new Date());

        Friends result = friendsRepository.save(friends);

        return new ResponseEntity(result, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/get", method = GET)
    public ResponseEntity<List<User>> getAll(){
        List<User> userList= userRepository.findAll();

        return new ResponseEntity<List<User>>(userList, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/store")
    public ResponseEntity storeOne(){
        User user = new User();

			user.setUsername("david");
			user.setPassword("4860ss");
			user.setCreate(new Date());
			user.setUpdate(new Date());
			user.setReg_id("dddsseeessdfdcsdfasds");
			User stored = userRepository.save(user);


        Friends friends = new Friends();
            friends.setUser(stored);
            friends.setFriend("aa");
            friends.setCreate(new Date());
            friends.setUpdate(new Date());
            friendsRepository.save(friends);

        Friends friend2 = new Friends();
        friend2.setUser(stored);
        friend2.setFriend("bb");
        friend2.setCreate(new Date());
        friend2.setUpdate(new Date());
        friendsRepository.save(friend2);

//        Friends[] friends = new Friends[2];
//
//
//        for(int i=0; i<2; i++) {
//            friends[i].setUser(stored);
//            friends[i].setFriend("aa"+i);
//            friends[i].setFriends_create(new Date());
//            friends[i].setFriends_update(new Date());
//            friendsRepository.save(friends[i]);
//        }


        List saved = Arrays.asList(stored, friend2);


        return new ResponseEntity<> (saved, HttpStatus.OK);
    }
}
