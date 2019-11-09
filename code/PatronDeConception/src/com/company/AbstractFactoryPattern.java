package com.company;

public class AbstractFactoryPattern {
    public static void main(String args[]){
        Provider provider = new SmsSenderProvider();
        Sender smsSender =  provider.produce();
        smsSender.Send();
    }
}

/*Sender*/
interface Sender{
    public void Send();
}
class SmsSender implements Sender{
    String name;
    public SmsSender(String name){
        this.name = name;
    }
    @Override
    public void Send() {
        System.out.println(this.name);
    }
}
class MailSender implements Sender{
    String name;
    public MailSender(String name){
        this.name = name;
    }
    @Override
    public void Send() {
        System.out.println(this.name);
    }
}

/*Sender Provider*/
interface Provider{
    public Sender produce();
}
class SmsSenderProvider implements Provider{

    @Override
    public SmsSender produce() {
        return new SmsSender("Sms");
    }
}
class MailSenderProvider implements Provider{

    @Override
    public MailSender produce() {
        return new MailSender("Mail");
    }
}