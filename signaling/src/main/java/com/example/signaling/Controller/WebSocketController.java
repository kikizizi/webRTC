package com.example.signaling.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.signaling.Data.ConnectData;
import com.example.signaling.Data.SocketDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
  
  private final SimpMessagingTemplate temp;

  @MessageMapping("/data/{roomNum}")
  public void dataTransfer(@DestinationVariable("roomNum") String roomNum,@RequestParam SocketDto dto){
    System.out.println("\ndtoType " + dto.getType()+"/ from: "+dto.getFrom()+" /to: " +dto.getTo()+"/roomNum:" + roomNum);

    String key = dto.getFrom()+"to"+dto.getTo();
    SocketDto result = null;
    Map<String,List<Object>> candidateLists = ConnectData.getInstance().getCandidateLists();
    List<Object> candidateList = null;
    switch (dto.getType()){
    case ("get_userList"):
      Map<String,String> userAndRoomNum = ConnectData.getInstance().getUserAndRoomNum();
      userAndRoomNum.put(dto.getFrom(),(String) dto.getData());
      Map<String,List<String>> userLists = ConnectData.getInstance().getUserLists();
      List<String> userList = userLists.get(roomNum);
      if (userList == null){
        userList = new ArrayList<>();
        userLists.put(roomNum, userList);
      }
      result = new SocketDto();
      result.setData(userList);
      result.setType("get_userList");
      temp.convertAndSend("/getData/"+roomNum+"/"+dto.getFrom(), result);
      for (String id : userList){
        result = new SocketDto();
        result.setData(dto.getFrom());
        result.setType("connect");
        temp.convertAndSend("/getData/"+roomNum+"/"+id, result);
      }
      userList.add(dto.getFrom());
      break;
    case ("save_candidate"):
      candidateList = candidateLists.get(key);
      System.out.println("before save candidate "+candidateList);
      if (candidateList == null){
        candidateList = new ArrayList<>();
        candidateLists.put(key, candidateList);
      }
      candidateList.add(dto.getData());
      System.out.println("save candidate " + candidateList);
      break;
    case ("get_candidate"):
      candidateList = candidateLists.get(key);
      System.out.println("get candidate "+candidateList);
      for (Object candidate : candidateList){
        result = new SocketDto(candidate,"candidate",dto.getFrom(),dto.getTo());
        temp.convertAndSend("/getData/"+roomNum+"/"+dto.getTo(), result);
      }
      candidateLists.remove(key);
      break;
    case ("ready_offer"):
      dto.setType("offer");
      temp.convertAndSend("/getData/"+roomNum+"/"+dto.getTo(), dto);
      break;
    case ("ready_answer"):
      dto.setType("answer");
      temp.convertAndSend("/getData/"+roomNum+"/"+dto.getTo(), dto);
      break;
    }
  }

}