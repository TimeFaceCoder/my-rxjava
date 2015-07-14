package models;

import models.DynamicItem;

import java.util.List;

/**
 * 动态返回数据模型
 *
 * @author (QQ:1522289706)
 * @from 2014-11-11
 * @TODO 动态返回数据模型
 */
public class DynamicResponse  {
    private static final long serialVersionUID = 1L;
    private int currentPage; // 当前页码
    private int totalPage; //总页数
    private List<DynamicItem> dataList; // 返回数据集

    public DynamicResponse(int currentPage, int totalPage,
                           List<DynamicItem> dataList) {
        super();
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.dataList = dataList;
    }

    public DynamicResponse() {
        super();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<DynamicItem> getDataList() {
        return dataList;
    }

    public void setDataList(List<DynamicItem> dataList) {
        this.dataList = dataList;
    }

}
