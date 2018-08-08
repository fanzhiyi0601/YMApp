package com.socket;

import com.google.gson.Gson;
import com.ymedia.dao.ChatDAO;
import com.ymedia.dao.Impl.ChatDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket/{user}")
public class WebSocketListen {

    Gson gson = new Gson();
    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。  
    private static int onlineCount = 0;

    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识  
    private static ConcurrentHashMap<String, WebSocketListen> webSocketSet = new ConcurrentHashMap<String, WebSocketListen>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据  
    private Session session;

    private String user = "";


    @Autowired
    ChatDAO chatDAO = new ChatDAOImpl();

    /**
     * 连接建立成功调用的方法 
     *
     * @param session
     *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据 
     */
    @OnOpen
    public void onOpen(@PathParam(value = "user") String param, Session session) {
        System.out.println(param);
        this.session = session;
        webSocketSet.put(param, this); // 加入set中
        addOnlineCount(); // 在线数加1  
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法 
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(user); // 从set中删除
        subOnlineCount(); // 在线数减1  
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法 
     *
     * @param message
     *            客户端发送过来的消息 
     * @param session
     *            可选的参数 
     */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception{
        System.out.println("来自客户端的消息:" + message);
        // 群发消息  
//        for (WebSocketListen item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
        sendToUser(message);
    }

    /**
     * 给指定的人发送消息
     * @param message
     */
    private void sendToUser(String message) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map = gson.fromJson(message, Map.class);
        String sender = String.valueOf(map.get("from"));
        String reciever = String.valueOf(map.get("to"));
        String msg = String.valueOf(map.get("msg"));
        String time = String.valueOf(map.get("time"));
        try {
            if (webSocketSet.get(reciever) != null) {
                webSocketSet.get(reciever).sendMessage("<br>" + sender + ": [" + time + "] <br> " + msg);
            } else {
                System.out.println("当前用户不在线");
                webSocketSet.get(sender).sendMessage("<br>当前用户不在线，将以离线消息发送给对方<br>");
                chatDAO.insertOffline(sender,reciever,msg,time);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用 
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。 
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        // this.session.getAsyncRemote().sendText(message);  
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketListen.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketListen.onlineCount--;
    }
}  