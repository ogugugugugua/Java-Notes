package com.company;

public class StaticFactoryPattern {
    public static void main(String args[]){
        //注意下面这个初始化类型是Sender_StaticFactoryPattern，而SendFactory.produceSms()返回的是其子类
        Sender_StaticFactoryPattern smsSender = SendFactory.produceSms();
        smsSender.Send();
        Sender_StaticFactoryPattern mailSender = SendFactory.produceMail();
        mailSender.Send();
    }
}
interface Sender_StaticFactoryPattern{
    public void Send();
}
class SmsSender_StaticFactoryPattern implements Sender_StaticFactoryPattern{
    String name;
    public SmsSender_StaticFactoryPattern(){
        this.name = "sms";
    }
    @Override
    public void Send() {
        System.out.println(this.name);
    }
}
class MailSender_StaticFactoryPattern implements Sender_StaticFactoryPattern {
    String name;
    public MailSender_StaticFactoryPattern(){
        this.name = "mail";
    }
    @Override
    public void Send() {
        System.out.println(this.name);
    }
}
class SendFactory{
    //注意这里函数头的返回类型是父类Sender_StaticFactoryPattern，实际返回对应的子类SmsSender_StaticFactoryPattern或者MailSender_StaticFactoryPattern
    public static Sender_StaticFactoryPattern produceSms(){
        return new SmsSender_StaticFactoryPattern();
    }
    public static Sender_StaticFactoryPattern produceMail(){
        return new MailSender_StaticFactoryPattern();
    }
}