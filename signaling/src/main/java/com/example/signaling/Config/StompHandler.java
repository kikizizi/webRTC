package com.example.signaling.Config;

// import java.util.List;


import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
// import org.springframework.messaging.simp.SimpMessagingTemplate;
// import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

// import com.example.signaling.Data.ConnectData;

@Component
public class StompHandler implements ChannelInterceptor {

  // @Autowired
  // private SimpMessagingTemplate temp;

  @Override
  public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
    // System.out.println(message);
    // List<String> dataList = ConnectData.getInstance().getDataList();
    // StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
    // String sessionId = accessor.getSessionId();
    // System.out.println("\n"+message);
    // System.out.println(accessor);
    // ChannelInterceptor.super.postSend(message, channel, sent);
    
    // switch (accessor.getCommand()) {
    //   case CONNECT:
    //     for (int i = 0;i < dataList.size(); i++){
    //       Map<String,Object> d = (Map<String,Object>)dataList.get(i);
    //       String urlId = (String) d.get("urlId");
    //       // temp.convertAndSend("/getData/"+urlId, sessionId + " connect");
    //     }
    //     break;
    //   case DISCONNECT:
    //     System.out.println("websocket disconnect/ sessionId :"+sessionId);
    //     // for (String fruit : fruits) {
    //     //   System.out.println(fruit);
    //     // }
    //     for (int i = 0;i < dataList.size(); i++){
    //       Map<String,Object> d = (Map<String,Object>)dataList.get(i);
    //       if (sessionId.equals(d.get("sessionId"))){
    //         dataList.remove(i);
    //         System.out.println(dataList);
    //       } else {
    //         String urlId = (String) d.get("urlId");
    //         // temp.convertAndSend("/getData/"+urlId, sessionId + " disconnect");
    //       }
    //     }
    //     break;
    //   case SEND:
    //     System.out.println("websocket connect/ sessionId :"+sessionId);
    //     String urlId = accessor.getDestination().split("/")[2];
    //     System.out.println("urlId : "+ urlId);
    //     Map<String,Object> data = new HashMap<>();
    //     data.put("sessionId", sessionId);
    //     data.put("urlId", urlId);
    //     dataList.add(data);
    //     System.out.println(dataList);
    //     break;
    //   default:
    //     break;
    // }
  }

  
  
}