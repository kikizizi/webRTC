package com.example.signaling.Config;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import com.example.signaling.Data.SocketDto;

import com.example.signaling.Data.ConnectData;

@Component
public class WebSocketEventListener {

  @Autowired
  private SimpMessagingTemplate temp;

  @EventListener
  public void handleWebSocketConnectListener(SessionConnectedEvent event) {
    // System.out.println(event.getMessage());
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
    String connectId = headerAccessor.getSessionId();
    System.out.println("websocket connect " + connectId);    
  }

  @EventListener
  public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
    String disconnectId = headerAccessor.getSessionId();
    Map<String,String> userAndRoomNum = ConnectData.getInstance().getUserAndRoomNum();
    String roomNum = userAndRoomNum.get(disconnectId);
    Map<String,List<String>> userLists = ConnectData.getInstance().getUserLists();
    List<String> userList = userLists.get(roomNum);
    userList.remove(disconnectId);

    SocketDto dto = new SocketDto();
    dto.setFrom(disconnectId);
    dto.setType("disconnect");
    userList.remove(disconnectId);
    System.out.println(userList);
    System.out.println("websocket disconnect " + disconnectId +"/roomNum :" + roomNum);
    for (String id : userList){
      temp.convertAndSend("/getData/"+roomNum+"/"+id, dto);
    }
  }
}