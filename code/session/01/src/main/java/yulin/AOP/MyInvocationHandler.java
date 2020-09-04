package yulin.AOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        preMission();
        Object result = method.invoke(target, args);
        afterMission();
        return result;
    }

    public void preMission(){
        System.out.println("preMission");
    }

    public void afterMission(){
        System.out.println("afterMission");
    }
}
