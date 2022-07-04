package ltd.muhuzhongxun.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ltd.muhuzhongxun.web.entity.Message;
import ltd.muhuzhongxun.web.entity.SysUser;
import ltd.muhuzhongxun.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author muhuzhongxun
 * @since 2022-03-12
 */
@Slf4j
@Component
@ServerEndpoint("/Chat/{fromid}")
public class WebSocketServerController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
   /**
    *  concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    */
    private static CopyOnWriteArraySet<WebSocketServerController> webSocketSet = new CopyOnWriteArraySet<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收fromid
     */
    private String fromid="";

    /**
     * 收到消息调用的方法，群成员发送消息
     *
     * @param message：发送消息
     */
    @OnMessage
    public void onMessage(String message) throws IOException {
//        List<Session> sessionList = groupMemberInfoMap.get("1");
//        Set<Integer> onlineUserList = onlineUserMap.get("1");

        log.info("收到来自窗口"+fromid+"的信息:"+message);

//        System.out.println(message);
        // json字符串转对象
//        Message msg = JSONObject.parseObject(message, Message.class);
//        List<Session> collect = sessionList.stream().filter(s ->
//                s.get() == msg.getId().toString())
//                .collect(Collectors.toList());
        webSocketSet.forEach(item -> {
            try {
                // json字符串转对象
                Message msg = JSONObject.parseObject(message, Message.class);
                // json对象转字符串
                String text = JSONObject.toJSONString(msg);
                if(msg.getType().equals("group")){item.session.getBasicRemote().sendText(text);}
                else{//消息类型为私聊时
                    if(item.fromid.equals(msg.getId().toString())){
                        System.out.println("向"+item.fromid+"用户发送消息:"+text);
                        item.session.getBasicRemote().sendText(text);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    /**
     * 建立连接调用的方法，群成员加入
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("fromid") String fromid) {
        this.fromid=fromid;
        this.session=session;
        //加入set中
        webSocketSet.add(this);
        //在线人数加一
        addOnlineCount();
        //todo 添加socket.onOpen(),更改用户的在线状态。onClose()同理
//        SysUser user = new SysUser();
//        sysUserService.updateById(user);
//        // 发送上线通知
//        sendInfo(sid, userId, onlineUserList.size(), "上线了~");
        log.info("有新窗口开始监听:"+fromid+",当前在线人数为" + getOnlineCount());
        System.out.println(fromid+"上线了");
    }


    public void sendInfo(Integer userId, Integer onlineSum, Message message) {
        // 获取该连接用户信息
//        SysUser currentUser = ApplicationContextUtil.getApplicationContext().getBean(SysUserMapper.class).selectById(userId);
        // 发送通知
//        Message msg = new Message();
////      msg.setCount(onlineSum);
//        msg.setFromid(userId);
//        msg.setAvatar(currentUser.getAvatar());
//        msg.setContent(currentUser.getNickName() + info);
        // json对象转字符串
        String text = JSONObject.toJSONString(message);
        System.out.println("sendInfo");
//        onMessage(userId, text);
    }

    /**
     * 关闭连接调用的方法，群成员退出
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session,@PathParam("fromid") Integer userId) {
//        List<Session> sessionList = groupMemberInfoMap.get("1");
//        sessionList.remove(session);
//        Set<Integer> onlineUserList = onlineUserMap.get("1");
//        onlineUserList.remove(userId);
//        // 发送离线通知
//        sendInfo(sid, userId, onlineUserList.size(), "下线了~");
        // 模拟在线人数减一

        //从set中删除
        webSocketSet.remove(this);
        //在线人数减一
        subOnlineCount();
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 传输消息错误调用的方法
     *
     * @param error
     */
    @OnError
    public void OnError(Throwable error) {
//        log.info("Connection error");
        System.out.println("出错了");
        System.out.println(error);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        WebSocketServerController.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        WebSocketServerController.onlineCount--;
    }


}
