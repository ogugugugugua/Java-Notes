package com.yulin.tmall.util;

/**
 * 这个类专门为分页提供信息
 */

public class Page {

    private int start; //开始页数
    private int count; //每页显示个数
    private int total; //总个数
    private String param; //参数

    private static final int defaultCount = 5; //默认每页显示5条

    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public Page (){
        count = defaultCount;
    }
    public Page(int start, int count) {
        this();
        this.start = start;
        this.count = count;
    }

    /**
     * 在页面的el表达式中,page.hasPrevious实际上是去调用hasPrevious的get方法,也就是isHasPrevious();
     * 没有定义hasPrevious这个属性为啥不报错呢,因为 这里的isHasPrevious();直接返回的是布尔值
     * @return
     */
    public boolean isHasPrevious(){
        if(start==0)
            return false;
        return true;
    }


    /**
     * 同理，一个重要的结论：el表达式里面page.propertyName的时候实际上是调用的是getPropertyname()方法
     * 因此在这里即使没有hasNext这个变量也没有关系，因为isHasNext()方法直接返回对应的值了，相当于在外面披了一层表皮
     * 这里为什么是is开头而不是get开头？ -- 因为这里返回的是boolean值，会使用is开头的getter函数
     * @return
     */
    public boolean isHasNext(){
        if(start==getLast())
            return false;
        return true;
    }

    /**
     * 理由同上这里的函数名字是getTotalPage，因为adminPage.jsp文件中需要获取page.totalPage这个整型数数据成员，因此对应的函数名字由get开头
     * @return
     */
    public int getTotalPage(){
        int totalPage;
        // 假设总数是50，是能够被5整除的，那么就有10页
        if (0 == total % count)
            totalPage = total /count;
            // 假设总数是51，不能够被5整除的，那么就有11页
        else
            totalPage = total / count + 1;

        if(0==totalPage)
            totalPage = 1;
        return totalPage;

    }

    public int getLast(){
        int last;
        // 假设总数是50，是能够被5整除的，那么最后一页的开始就是45
        if (0 == total % count)
            last = total - count;
            // 假设总数是51，不能够被5整除的，那么最后一页的开始就是50
        else
            last = total - total % count;
        last = last<0?0:last;
        return last;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", count=" + count +
                ", total=" + total +
                ", param='" + param + '\'' +
                '}';
    }

    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public String getParam() {
        return param;
    }
    public void setParam(String param) {
        this.param = param;
    }

}