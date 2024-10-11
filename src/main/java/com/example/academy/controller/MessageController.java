//package com.example.academy.controller;
//
//import com.example.academy.dto.chat.MessageReadByChatRoomIdDTO;
//import com.example.academy.dto.chat.MessageReadDTO;
//import com.example.academy.dto.chat.MessageSendDTO;
//import com.example.academy.service.MessageService;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/messages")
//public class MessageController {
//
//  private final MessageService messageService;
//
//  @Autowired
//  public MessageController(MessageService messageService) {
//    this.messageService = messageService;
//  }
//
//  @PostMapping
//  public MessageReadDTO sendMessage(@RequestBody MessageSendDTO messageSendDTO) {
//    return messageService.sendMessage(messageSendDTO);
//  }
//
//  @GetMapping
//  public List<MessageReadDTO> getMessagesByChatRoomId(
//      @RequestBody MessageReadByChatRoomIdDTO messageReadByChatRoomIdDTO) {
//    return messageService.getAllMessagesByChatRoomId(messageReadByChatRoomIdDTO);
//  }
//
//}
