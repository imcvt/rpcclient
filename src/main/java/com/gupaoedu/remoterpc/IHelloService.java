package com.gupaoedu.remoterpc;

import java.io.Serializable;

public interface IHelloService {

    String sayHello(String content);

    String saveUser(User user);

}
