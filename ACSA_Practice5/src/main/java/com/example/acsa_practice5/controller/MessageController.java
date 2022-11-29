package com.example.acsa_practice5.controller;

import com.example.acsa_practice5.domain.Message;
import com.example.acsa_practice5.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController{
    private final MessageRepo messageRepo;
    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping // вывод всех сообщений
    public List<Message> list(){
        return messageRepo.findAll();
    }

    @GetMapping("{id}") // ожидаем идентификатор объекта в заголовке
    public Message getOne(@PathVariable("id") Message message){
        return message;
    }

//    private Map<String, String> getMessage(String id) {
//        return messages.stream()
//                .filter(message -> message.get("id").equals(id))
//                .findFirst()
//                .orElseThrow(NotFoundException::new);
//    }

    @PostMapping
    public Message create(@RequestBody Message message){
//        message.put("id", String.valueOf(counter++)); // добавляем новый id к сообщению от пользователя
//        messages.add(message); // кладём в список сообщений
        return messageRepo.save(message); // обновленное сообщение
    }
    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb, // получим через id в заголовке
            @RequestBody Message message // получим от пользователя в виде json в теле запроса
    ){ // обновление записи
//        Map<String, String> messageFromDb = getMessage(id);
//        messageFromDb.putAll(message);
//        messageFromDb.put("id", id); // установка того id, по которому был призведён запрос
        BeanUtils.copyProperties(message, messageFromDb, "id"); // перекладываем значения из 1 во 2 кроме id
        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
//        Map<String, String> message = getMessage(id);
        messageRepo.delete(message);
    }

}