package com.qhit.springbootmybatisdemo1.beans;

import java.util.List;

public class Page {

    private int currPageNo;//当前页
    private int pageSize;//页大小
    private int totalCount;//总记录数
    private int totalPageCount;//总页数
    private List<User> newsList;//每页新闻集合



    public int getCurrPageNo() {
        return currPageNo;
    }
    public void setCurrPageNo(int currPageNo) {
        this.currPageNo = currPageNo;
    }
    public int getPageSize() {
        return pageSize;
    }
    //设置每页显示的数据个数
    public void setPageSize(int pageSize) {
        //判断页面中每页的显示的数据如果为0则给值10要不然为参数新pageSize
        this.pageSize = pageSize==0?10:pageSize;
    }
    public int getTotalCount() {
        return totalCount;
    }

    //计算总记录数,根据总记录数计算总页数
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        //计算总页数
        if(totalCount>0){
            //不小于0时,则用三元运算判断最后显示的数据是不是等于0,用于解决最后一页显示最后一条数据和我们每页显示10条数据的冲突
            totalPageCount =this.totalCount%this.pageSize==0?
                    this.totalCount/this.pageSize //当能除尽情况下
                    :(this.totalCount/this.pageSize)+1;//除不尽时 ,当 101 10/11
        }else{
            totalPageCount=0;
        }
    }


    public int getTotalPageCount() {
        return totalPageCount;
    }
    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
    public List<User> getNewsList() {
        return newsList;
    }
    public void setNewsList(List<User> newsList) {
        this.newsList = newsList;
    }
}
