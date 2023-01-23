package com.example.signaling.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectData {
  private static ConnectData instance;  
  
  private ConnectData() { }  

  private Map<String,String> userAndRoomNum = new HashMap<>();
  private Map<String,List<String>> userLists = new HashMap<>();
  private Map<String,List<Object>> candidateLists = new HashMap<>();

  public static synchronized ConnectData getInstance() {  
      if (instance == null) {  
          instance = new ConnectData();  
      }  
      return instance;  
  }
  
  public Map<String,String> getUserAndRoomNum(){
    return this.userAndRoomNum;
  }

  public Map<String,List<String>> getUserLists(){
    return this.userLists;
  }

  public Map<String,List<Object>> getCandidateLists(){
    return this.candidateLists;
  }
}
