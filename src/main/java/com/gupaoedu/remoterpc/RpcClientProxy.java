package com.gupaoedu.remoterpc;

import java.lang.reflect.Proxy;

public class RpcClientProxy {

    public <T> T proxy(Class<T> interfaceCls, String host, int port) {
        return (T)Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                                            new Class<?>[]{interfaceCls},
                                                new RemoteInvocationHandler(host,port));
    }
}
