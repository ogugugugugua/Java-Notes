package yulin.elasticsearch.springboot.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class logTest {
    public static void main(String[] args) {
        log.info("from log");
    }

    //甩锅前端必备哈哈哈
    public static Boolean checkUser(String name, String password){
        log.info("params,name:{},password:{}", name, password);
        System.out.println("do something");
        Boolean result = true;
        log.info("retValue:{}", result);
        return result;
    }

    //外部调用
    public static Boolean bizWithRpc(String parameters){
        log.debug("Calling external system:" + parameters);
        Object result = null;
        try{
//            result = callRemoteSystem(parameters);
            log.debug("Called successfully, result is " + result);
        }catch (Exception e){
            log.warn("Failed at calling xxx system. Exception:" + e);
        }
        return true;
    }




}
