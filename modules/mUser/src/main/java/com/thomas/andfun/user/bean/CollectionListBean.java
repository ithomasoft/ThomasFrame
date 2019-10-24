package com.thomas.andfun.user.bean;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/24
 * @updatelog
 * @since
 */
public class CollectionListBean {


    /**
     * curPage : 1
     * datas : [{"author":"","chapterId":423,"chapterName":"Architecture","courseId":13,"desc":"","envelopePic":"","id":93246,"link":"https://juejin.im/post/5dafc49b6fb9a04e17209922","niceDate":"刚刚","origin":"","originId":9897,"publishTime":1571905757000,"title":"是让人耳目一新的 Jetpack MVVM 精讲啊！","userId":5515,"visible":0,"zan":0},{"author":"","chapterId":93,"chapterName":"基础知识","courseId":13,"desc":"","envelopePic":"","id":92806,"link":"https://juejin.im/post/5dac6aa2518825630e5d17da","niceDate":"2天前","origin":"","originId":9856,"publishTime":1571721204000,"title":"总结UI原理和UI优化方式","userId":5515,"visible":0,"zan":0},{"author":"","chapterId":471,"chapterName":"10.0（Q）","courseId":13,"desc":"","envelopePic":"","id":92805,"link":"https://www.jianshu.com/p/10bdbf883c46?utm_source=desktop&amp;utm_medium=timeline","niceDate":"2天前","origin":"","originId":9857,"publishTime":1571721202000,"title":"Android从5.0到9.0版本的主要变更","userId":5515,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 3
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * author :
         * chapterId : 423
         * chapterName : Architecture
         * courseId : 13
         * desc :
         * envelopePic :
         * id : 93246
         * link : https://juejin.im/post/5dafc49b6fb9a04e17209922
         * niceDate : 刚刚
         * origin :
         * originId : 9897
         * publishTime : 1571905757000
         * title : 是让人耳目一新的 Jetpack MVVM 精讲啊！
         * userId : 5515
         * visible : 0
         * zan : 0
         */

        private String author;
        private int chapterId;
        private String chapterName;
        private int courseId;
        private String desc;
        private String envelopePic;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private int originId;
        private long publishTime;
        private String title;
        private int userId;
        private int visible;
        private int zan;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public int getOriginId() {
            return originId;
        }

        public void setOriginId(int originId) {
            this.originId = originId;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }
    }
}
