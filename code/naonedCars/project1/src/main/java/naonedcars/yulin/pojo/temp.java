package naonedcars.yulin.pojo;

import org.springframework.stereotype.Component;

@Component
public class temp {
    String name;
    String author;

    @Override
    public String toString() {
        return "temp{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
