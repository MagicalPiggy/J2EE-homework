package sc.ustc.Proxy;

import sc.ustc.handler.ExecutorHandler;
import sc.ustc.service.Executor;

import java.lang.reflect.Proxy;

public class ExecutorProxy {
    public Object getProxy(Object object) {//通过调用此方法获取一个代理对象
    	//创建一个与代理对象相关联的executorHandler，executorHandler实现了InvocationHandler接口
    	//InvocationHandler中有一个invoke方法，所有执行代理对象的方法都会被替换成执行invoke方法
        ExecutorHandler executorHandler = new ExecutorHandler();
        
        executorHandler.setObject(object);
        
      //返回一个代理对象
        return Proxy.newProxyInstance(Executor.class.getClassLoader(), object.getClass().getInterfaces(), executorHandler);
    }
}
