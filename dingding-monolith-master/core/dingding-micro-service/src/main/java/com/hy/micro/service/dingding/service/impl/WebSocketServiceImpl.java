package com.hy.micro.service.dingding.service.impl;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

import com.hy.micro.service.dingding.service.WebSocketService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-07
 * @github: https://github.com/weixuan2008
 */

@Service
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocketServiceImpl implements WebSocketService {
	private Session session;
	private static CopyOnWriteArraySet<WebSocketServiceImpl> webSockets;

	static {
		webSockets = new CopyOnWriteArraySet<>();
	}

	@OnOpen
	public void opOpen(Session session) {
		this.session = session;
		webSockets.add(this);
		log.info("【websock message】, get new connection, current account of connection={}", webSockets.size());
	}

	@OnClose
	public void onClose(Session session) {
		webSockets.remove(this);
		log.info("【websock message】, get disconnecton, current account of connection={}", webSockets.size());
	}

	@OnMessage
	public void onMessage(String message) {
		log.info("【websock message】, get new message, msg={}", message);
	}


	@Override
	public void sendMessage(String message) {
		for (WebSocketServiceImpl webSocket : webSockets) {
			log.info("【websock message】 broadcast message.");

			try {
				webSocket.session.getBasicRemote().sendText(message);
			} catch (IOException ex) {
				log.error("【websock message】, failed to broadcast message, error = {}", ex);
			}
		}
	}

}
