package com.example.signaling.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoomController {

  @RequestMapping("/room/{roomNum}")
  public ModelAndView connectRoom(@PathVariable("roomNum") Integer roomNum){
    ModelAndView mv = new ModelAndView("/room"+Integer.toString(roomNum)+".html");
    return mv;
  }
}
