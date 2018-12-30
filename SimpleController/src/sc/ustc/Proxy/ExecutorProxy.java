package sc.ustc.Proxy;

import sc.ustc.handler.ExecutorHandler;
import sc.ustc.service.Executor;

import java.lang.reflect.Proxy;

public class ExecutorProxy {
    public Object getProxy(Object object) {//ͨ�����ô˷�����ȡһ���������
    	//����һ�����������������executorHandler��executorHandlerʵ����InvocationHandler�ӿ�
    	//InvocationHandler����һ��invoke����������ִ�д������ķ������ᱻ�滻��ִ��invoke����
        ExecutorHandler executorHandler = new ExecutorHandler();
        
        executorHandler.setObject(object);
        
      //����һ���������
        return Proxy.newProxyInstance(Executor.class.getClassLoader(), object.getClass().getInterfaces(), executorHandler);
    }
}
