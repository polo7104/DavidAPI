package com.api.chat;

import com.api.chat.Domain.*;
import com.api.chat.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @Autowired RoomRepository       roomRepository;
    @Autowired FriendRepository     friendRepository;



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


        User user = userRepository.findOne(id);

        if(user == null){
            return new ResponseEntity<>("user is null", HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity(user, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/get", method = GET)
    public ResponseEntity<List<User>> getAll(){
        List<User> userList= userRepository.findAll();

        return new ResponseEntity<List<User>>(userList, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/room", method = GET)
    public ResponseEntity<List<Room>> getAllRoom(){
        List<Room> userRoomListList= roomRepository.findAll();

        return new ResponseEntity<List<Room>>(userRoomListList, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/store")
    public ResponseEntity storeOne(){

        User david = new User();

			david.setUsername("david");
			david.setPassword("4860ss");
			david.setCreate(new Date());
			david.setUpdate(new Date());
			david.setReg_id("dddsseeessdfdcsdfasds");
			User davidStored = userRepository.save(david);

        User lee = new User();

            lee.setUsername("lee");
            lee.setPassword("4860ss");
            lee.setCreate(new Date());
            lee.setUpdate(new Date());
            lee.setReg_id("dddsseeessdfdcsdfasds");
            User leeStored = userRepository.save(lee);

        User jin = new User();

            jin.setUsername("jin");
            jin.setPassword("4860ss");
            jin.setCreate(new Date());
            jin.setUpdate(new Date());
            jin.setReg_id("dddsseeessdfdcsdfasds");
            User jinStored = userRepository.save(jin);

        davidStored.addFriend(jinStored);
        davidStored.addFriend(leeStored);

        davidStored = userRepository.save(davidStored);

        Room room1 = new Room();
        Room savedRoom1 = new Room();

        room1.setRoomname("david and jin");
        room1.addUser(david);
        room1.addUser(jinStored);

        savedRoom1 = roomRepository.save(room1);

        davidStored.addRoom(savedRoom1);

        davidStored = userRepository.save(davidStored);



//        Friend friend1 = new Friend();
//
//            friend1.addUser(jinStored);
//            friend1.addUser(leeStored);
//
//        System.out.println(friend1.toString());
//
//            friendRepository.save(friend1);
//
//
//        davidStored.addFriend(friend1);
//        userRepository.save(davidStored);


        return new ResponseEntity<> (davidStored, HttpStatus.OK);
    }
}
