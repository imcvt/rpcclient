package com.gupaoedu.remoterpc;

public class App {

    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        IHelloService helloService = rpcClientProxy.proxy(IHelloService.class,"localhost", 8080);

        System.out.println(helloService.sayHello("hello"));

        User user = new User();
        user.setName("eeeeemmmmmm");
        System.out.println(helloService.saveUser(user));
    }
}
