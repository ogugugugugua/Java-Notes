package com.company;

public class MyStringBuffer {

    class IndexlsNegativeException extends Exception{
        public IndexlsNegativeException(){}
        public IndexlsNegativeException(String msg){
            super(msg);
        }
    }
    class IndexlsOutofRangeException extends Exception{
        public IndexlsOutofRangeException(){}
        public IndexlsOutofRangeException(String msg){
            super(msg);
        }
    }

    public MyStringBuffer(String str){
        this.str = str;
    }
    String str;
    public String insert(int pos, String b) throws Exception{
        if(pos<0)
            throw new IndexlsNegativeException();
        if(pos>this.str.length())
            throw new IndexlsOutofRangeException();
        if(b==null)
            throw new NullPointerException();
        String front = this.str.substring(0,pos);
        String back = this.str.substring(pos,this.str.length());
        String result = front+b+back;
        return result;
    }
    public static void main(String[] args) {
        MyStringBuffer test = new MyStringBuffer("hello");
        try{
            String result = test.insert(4,null);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
