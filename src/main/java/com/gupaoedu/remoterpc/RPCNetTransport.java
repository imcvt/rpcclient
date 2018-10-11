package com.gupaoedu.remoterpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RPCNetTransport {

    String host;
    int port;

    public RPCNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket() {
        System.out.println("创建一个socket");
        Socket socket;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException("连接建立失败");
        }

        return socket;
    }

    public Object sendRequest(RPCRequest rpcRequest) {
        Socket socket = null;
        try {
            socket = newSocket();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object  result = objectInputStream.readObject();

            objectInputStream.close();
            objectOutputStream.close();

            return result;

        } catch (IOException e) {
            throw new RuntimeException("senRequest socket 建立失败:"+e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (socket == null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
