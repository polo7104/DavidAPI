package com.api.chat;

import com.api.chat.Beans.User;
import com.api.chat.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Date;
import java.util.List;

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

    @Autowired UserRepository userRepository;



    @RequestMapping(value = "/singup", method = POST)
    public ResponseEntity signUp(@RequestBody @Valid User user, BindingResult result){

        if(result.hasErrors()){
            log.error("[ERROR]"+result.getAllErrors());
            return new ResponseEntity("[ERROR]"+result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        user.setCreate(new Date());
        user.setUpdate(new Date());
//        user.setFriends(null);

        User userResult = userRepository.save(user);


        return new ResponseEntity<>(userResult, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/get", method = GET)
    public ResponseEntity<List<User>> getAll(){
        List<User> userList= userRepository.findAll();

        return new ResponseEntity<List<User>>(userList, HttpStatus.ACCEPTED);
    }
}
