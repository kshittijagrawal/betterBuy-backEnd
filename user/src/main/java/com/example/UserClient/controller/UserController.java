package com.example.UserClient.controller;

import com.example.UserClient.dto.*;
import com.example.UserClient.entity.User;
import com.example.UserClient.repository.UserRepository;
import com.example.UserClient.services.EmailService;
import com.example.UserClient.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;



@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://10.20.4.248:8080", "http://10.20.5.34:8082", "http://localhost:8084"})
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;


    class EmailThread extends Thread{
        String userId;
        public void setUserId(String userId){
            this.userId=userId;
        }
        public void run(){
            emailService.sendMail(this.userId,"Order Placed !!","Thanks For Placing order . Within two days you will receive your order!.");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Validation> login(@RequestBody UserLoginDto userLoginDto) {
        return new ResponseEntity<>(userService.loginValidation(userLoginDto.getEmailId(), userLoginDto.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Validation> register(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        BeanUtils.copyProperties(userRegistrationDTO,user);
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }
    @PostMapping("/addToCart/{userId}")
    public ResponseEntity<Validation> addToCart(@RequestBody Cart cart,@PathVariable("userId") String userId){
        System.out.println("Called from mobile");
        return new ResponseEntity<>(userService.addToCart(cart,userId),HttpStatus.OK);
    }
    @DeleteMapping("/deleteFromCart/{productId}/{merchantId}/{userId}")
    public ResponseEntity<Validation> deleteFromCart(@PathVariable("productId") String productId, @PathVariable("merchantId") String merchantId, @PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.deleteFromCart(userId, productId, merchantId),HttpStatus.OK);
    }
    @GetMapping("/viewCart/{userId}")
    public ResponseEntity<CartResponseList> viewCart(@PathVariable("userId") String userId){
//        ListOfCartItems listOfCartItems=new ListOfCartItems();
//        listOfCartItems.setCartList(userService.viewCart(userId));
//        return new ResponseEntity<ListOfCartItems>(listOfCartItems,HttpStatus.OK);
        CartResponseList cartResponseList = new CartResponseList();
        cartResponseList.setCartResponse(userService.viewCart(userId));
        return new ResponseEntity<CartResponseList>(cartResponseList,HttpStatus.OK);

    }
    @PostMapping("/checkOut/{userId}")
    public ResponseEntity<Validation> checkOut(@PathVariable("userId") String userId){
        EmailThread emailThread=new EmailThread();
        emailThread.setUserId(userId);
        emailThread.start();
        //emailService.sendMail(userId,"Order Placed !!","Thanks For Placing order . Within two days you will receive your order!.");
        return new ResponseEntity<>(userService.checkOut(userId),HttpStatus.OK);
    }
    @GetMapping("/viewHistory/{userId}")
    public ResponseEntity<HistoryList> viewHistory(@PathVariable("userId") String userId){
        HistoryList historyList = new HistoryList();
        historyList.setHistoryList(userService.viewHistory(userId));
        return new ResponseEntity<>(historyList,HttpStatus.OK);
    }
    @PostMapping("/removeFromCart/{userId}")
    public ResponseEntity<Validation> removeFromCart(@RequestBody Cart cart,@PathVariable("userId") String userId){
        return new ResponseEntity<>(userService.removeFromCart(cart, userId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCart/{userId}")
    public ResponseEntity<Validation> removeCart(@PathVariable String userId) {
        return new ResponseEntity<>(userService.removeCart(userId), HttpStatus.OK);
    }

}
