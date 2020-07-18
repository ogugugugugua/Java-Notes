package naonedcars.yulin.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.io.Serializable;
@Component
@Data
public class Result implements Serializable {
    private String code;
    private String msg;
    private Object data;

    public static Result success(Object data){
        Result m = new Result();
        m.setCode("0");
        m.setData(data);
        m.setMsg("success");
        return m;
    }

    public static Result success(Object data, String msg){
        Result m = new Result();
        m.setMsg(msg);
        m.setData(data);
        m.setCode("0");
        return m;
    }

    public static Result success( String msg){
        Result m = new Result();
        m.setMsg(msg);
        m.setCode("0");
        return m;
    }

    public static Result fail(String msg){
        Result m = new Result();
        m.setMsg(msg);
        m.setCode("-1");
        m.setData(null);
        return m;
    }

    public static Result fail(Object data, String msg){
        Result m = new Result();
        m.setMsg(msg);
        m.setData(data);
        m.setCode("-1");
        return m;
    }
}
