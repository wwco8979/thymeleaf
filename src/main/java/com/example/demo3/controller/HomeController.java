package com.example.demo3.controller;


import com.example.demo3.dto.DateDto;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
public class HomeController {
    @GetMapping
    public String intro(Model model) {
        model.addAttribute("Date", LocalDate.now());
        log.info("log text:{}", "ok");
        System.out.println("테스트");
        return "intro";
    }

    @GetMapping("/t_output")
    public String output(Model model) {
        log.info("t_output ok");

        model.addAttribute("htmlStr", "<h3>h3테스트 내용</h3>");

        Map<String, String> map = new HashMap<>();
        map.put("name", "홍길동");
        map.put("age", "20");
        map.put("addr", "인천 미추홀구");
        model.addAttribute("mapDate", map);
        //Dto(또는 vo) 데이터 묶음
        // alt+enter : 자동완성

        DateDto dto = new DateDto();
        dto.setName("아무개");
        dto.setAge(20);
        dto.setAddress("인천시 서구");
        model.addAttribute("dtoDate", dto);
        model.addAttribute("msg", "서버로부터의 메시지");
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DateDto dao = new DateDto();
            dao.setName("이름" + i);
            dao.setAge(20 + i);
            dao.setAddress("주소" + i);
            list.add(dao);
        }
        System.out.println(list);
        model.addAttribute("list", list);
        return "t_output";
    }

    //@RequestMapping(value = "/t_control",method = RequestMethod.GET)
    @GetMapping("/t_control")
    public String control(Model model, HttpSession session) {
        session.setAttribute("user_id", "admin");

        //session.invalidate(); //로그 아웃
        model.addAttribute("msg", "이 문자열이 보입니다.");
        model.addAttribute("age", 34);

        return "t_control";

    }

    @GetMapping("/info/{id}")
    public String info(Model model, @PathVariable("id") String id) {
        System.out.println("id:" + id);
        return null;
    }

    @GetMapping("/sendData")
    public String sendData() {
        return "sendData";
    }

    @GetMapping("/a_send")
    public String aSend(@RequestParam("num1") Integer num1
            , @RequestParam("num2") Integer num2, Model model) {
        System.out.println("num1:" + num1);
        System.out.println("num2:" + num2);


        model.addAttribute("result", num1 + num2);

        return "s_result";

    }

    @GetMapping("/noneDtoSend")
    public String noneDtoSend(@RequestParam String name, @RequestParam(value = "age", defaultValue = "1") int age, @RequestParam String address, Model model) {
        System.out.println("name:" + name);
        System.out.println("age:" + age);
        System.out.println("address:" + address);
        model.addAttribute("result", "none dto send ok");

        return "s_result";
    }
    @PostMapping("dtoSend")
    public String dtoSend(@ModelAttribute DateDto dto, Model model) {
        System.out.println("name:" + dto.getName());
        System.out.println("age:" + dto.getAge());
        System.out.println("address:" + dto.getAddress());
        //model.addAttribute("dtoDate", dto);
        model.addAttribute("result", "dto send ok");
        return "s_result";
    }
    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("user", new DateDto("pah",20,"인천"));
        return "user";

    }
    @PostMapping("/users")
    public String user(@ModelAttribute DateDto dto, Model model) {
        System.out.println("name:" + dto.getName());
        System.out.println("age:" + dto.getAge());
        System.out.println("address:" + dto.getAddress());
        return "user";

    }
}
