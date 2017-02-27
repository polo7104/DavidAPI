package com.api.chat;

import com.api.chat.Domain.*;
import com.api.chat.Domain.Message;
import com.api.chat.Repository.*;
import com.google.android.gcm.server.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired MessageRepository    messageRepository;
    @Autowired ChatService          chatService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        log.info("session id : {} ", request.getSession().getId());
        return request.getSession().getId();
    }

    @RequestMapping(value = "/signup", method = POST,  produces  = "application/json")
    public ResponseEntity signUp(@RequestBody @Valid User user, BindingResult result){

        if(result.hasErrors()){
            log.error("[ERROR]"+result.getAllErrors());
            return new ResponseEntity("[ERROR]"+result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        User userResult = chatService.saveUser(user);

        return new ResponseEntity<>(userResult, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/search/user/{username}", method = GET)
    public ResponseEntity searchOne(@PathVariable String username){

        if(username == null){
            return new ResponseEntity<>("Nothing", HttpStatus.BAD_REQUEST);
        }

        User user = chatService.findByUserName(username);

        return new ResponseEntity(user, HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/get/{username}", method = GET)
    public ResponseEntity getAll(@PathVariable String username){

        User user = chatService.findByUserName(username);

        List<Friend> friendList = chatService.getFriends(user);

        List<Room> roomList = chatService.getRooms(user);

        HashMap data = new ManagedMap();

        data.put("user", user);
        data.put("friends", friendList);
        data.put("rooms", roomList);


//        List data = Arrays.asList(user, friendList, roomList);


        return new ResponseEntity(data, HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/room", method = GET)
    public ResponseEntity<List<Room>> getAllRoom(){
        List<Room> userRoomListList= roomRepository.findAll();

        return new ResponseEntity<List<Room>>(userRoomListList, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/store")
    public ResponseEntity storeOne(){

        // add Users

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

        // add Friends

        Friend friend1 = new Friend();

            friend1.setHost(david);
            friend1.setGuest(lee);
            friend1.setCreate(new Date());
            friend1.setUpdate(new Date());

            friendRepository.save(friend1);

        Room room = new Room();

            room.setRoomname("Test");
            room.getUserList().add(david);
            room.getUserList().add(jin);
            room.getUserList().add(lee);

        roomRepository.save(room);

        Message message = new Message();

            message.setMessage_create(new Date());
            message.setSender(david);
            message.setMessage("TETssdfsdf messagewew!@#!#");

        messageRepository.save(message);

            room.getMessageList().add(message);

        roomRepository.save(room);


        return new ResponseEntity<> (davidStored, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method=GET)
    public ResponseEntity getAll(){
        List<User> users = userRepository.findAll();

        HashMap data = new ManagedMap();

        data.put("friends", users);

        return new ResponseEntity (data, HttpStatus.OK);
    }

    @RequestMapping(value = "/sendmessage")
    public String sendMessage(
//            @RequestBody @Valid Message msg, BindingResult result
    ){

        String api_key = "AIzaSyBiqILTT-Z-NRx1GtPZMOGpgiFbTZuc3yw";
        int retry_cnt = 3;

//        List<String> reg_ids = new ArrayList<>();
//        reg_ids.add("eAz3VDSJdzA:APA91bE7h2aygdqjWh4oZKZHlemUoOcis3U6gKtTRO59Rp5AzpgfuIoyDd4DNDcwG7_kQ3JIrGm1xBq1H9ssAhgCC3SiDLywPlBUTuMT9ssNPcuKkzwE39YUdhavfWoMVSmuwpM8fqeA");

        List<String> reg_ids = chatService.getRegIds();

        com.google.android.gcm.server.Message message = new com.google.android.gcm.server.Message.Builder().collapseKey("collapseKey"+System.currentTimeMillis()).timeToLive(3)
                .delayWhileIdle(true).addData("message", "Test Data Web Push. God always Loves David.").build();

        Sender sender = new Sender(api_key);
        try {
            sender.send(message, reg_ids, retry_cnt);
        }catch (Exception e){

        }
        return "Send Ok";
    }

    @RequestMapping(value = "/send/{username}")
    public String sendMessage(@PathVariable String username){

        String api_key = "AIzaSyBiqILTT-Z-NRx1GtPZMOGpgiFbTZuc3yw";
        int retry_cnt = 3;

//        List<String> reg_ids = new ArrayList<>();
//        reg_ids.add("eAz3VDSJdzA:APA91bE7h2aygdqjWh4oZKZHlemUoOcis3U6gKtTRO59Rp5AzpgfuIoyDd4DNDcwG7_kQ3JIrGm1xBq1H9ssAhgCC3SiDLywPlBUTuMT9ssNPcuKkzwE39YUdhavfWoMVSmuwpM8fqeA");

        String reg_id = chatService.getSingleId(username);

        com.google.android.gcm.server.Message message = new com.google.android.gcm.server.Message.Builder().collapseKey("collapseKey"+System.currentTimeMillis()).timeToLive(3)
                .delayWhileIdle(true).addData("message", "Test Data Web Push. God always Loves David.").build();

        Sender sender = new Sender(api_key);
        try {
            sender.send(message, reg_id, retry_cnt);
        }catch (Exception e){

        }
        return "Send Ok";

    }


}
