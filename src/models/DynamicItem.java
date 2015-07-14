package models;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态module
 *
 * @author SUN
 * @from 2014-11-11
 * @TODO 动态数据module
 */
public class DynamicItem implements Comparable<DynamicItem> {
    private String timeId; //时光ID
    private String timeTitle; //时光标题
    private String content; //内容详情
    private String date;
    private double lat; //纬度
    private double lng; //经度
    private int likeCount; //赞 数量
    private int commentCount; //评论数量
    private int like; //是否赞
    private String bookId; //所属时光书ID
    private String bookTitle; //所属时光书标题
    private int type; //数据类型 1 时光  2 广告
    private String client; //来源
    private String correctTime;

    public List<ImgObj> getImageObjList() {
        return imageObjList;
    }

    public void setImageObjList(List<ImgObj> imageObjList) {
        this.imageObjList = imageObjList;
    }

    public String getTimeTitle() {
        return timeTitle;
    }

    private List<ImgObj> imageObjList = new ArrayList<>();

    public boolean isLike() {
        return like == 1;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public void setTimeTitle(String timeTitle) {
        this.timeTitle = timeTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }


    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }


    public String getCorrectTime() {
        if (correctTime == null) {
            return "";
        }
        return correctTime;
    }

    public void setCorrectTime(String correctTime) {
        this.correctTime = correctTime;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int compareTo(DynamicItem o) {
        return Integer.compare(this.likeCount, o.getLikeCount());
    }
}