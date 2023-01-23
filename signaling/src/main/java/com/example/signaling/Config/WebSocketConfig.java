package com.example.signaling.Config;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
// import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

  // @Autowired
  // private StompHandler stompHandler;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/getData"); // b
		registry.setApplicationDestinationPrefixes("/sendData"); // a
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
    // SockJS 연결 시 ex) var sock = new Sock("/websocket")
		registry.addEndpoint("/websocket").withSockJS();
	}

	// socket intercepter 가능
	// @Override
	// public void configureClientInboundChannel(ChannelRegistration registration) {
	// 	registration.interceptors(stompHandler);
	// }


}