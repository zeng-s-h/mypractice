package com.example.javacoretechnology.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author 小白i
 * @date 2020/7/27
 */
@ServerEndpoint("/push")
public class EchoEndPoint {

    /**
     * 网络连接建立时触发
     *
     * @param session session会话
     * @throws IOException 异常
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        //以下代码省略...
    }

    @OnMessage
    public String onMessage(String message) {
        //以下代码省略...
        return "message";
    }

    @OnError
    public void onError(Throwable t) {
        //以下代码省略...
    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {
        //以下代码省略...
    }

}
