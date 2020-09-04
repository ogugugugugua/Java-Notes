package springboot.chapter2.pojo;

import org.apache.ibatis.type.Alias;

@Alias(value = "UserForMybatis")
public class UserForMybatis {
    private Long id = null;
    private String name = null;
    private String note = null;
    private SexEnum sex = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }
}
