package com.thomas.andfun.user.bean;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
public class IntegralListBean {


    /**
     * curPage : 2
     * datas : [{"coinCount":17,"date":1568865094000,"desc":"2019-09-19 11:51:34 签到,积分：10 + 7","id":48608,"reason":"签到","type":1,"userId":5515,"userName":"953530507"},{"coinCount":16,"date":1568681712000,"desc":"2019-09-17 08:55:12 签到,积分：10 + 6","id":46481,"reason":"签到","type":1,"userId":5515,"userName":"953530507"},{"coinCount":15,"date":1568265447000,"desc":"2019-09-12 13:17:27 签到,积分：10 + 5","id":44180,"reason":"签到","type":1,"userId":5515,"userName":"953530507"},{"coinCount":14,"date":1568084126000,"desc":"2019-09-10 10:55:26 签到,积分：10 + 4","id":42375,"reason":"签到","type":1,"userId":5515,"userName":"953530507"},{"coinCount":13,"date":1567989837000,"desc":"2019-09-09 08:43:57 签到,积分：10 + 3","id":41177,"reason":"签到","type":1,"userId":5515,"userName":"953530507"},{"coinCount":12,"date":1567818584000,"desc":"2019-09-07 09:09:44 签到,积分：10 + 2","id":40344,"reason":"签到","type":1,"userId":5515,"userName":"953530507"},{"coinCount":11,"date":1567646214000,"desc":"2019-09-05 09:16:54 签到,积分：10 + 1","id":38684,"reason":"签到","type":1,"userId":5515,"userName":"953530507"},{"coinCount":10,"date":1567475644000,"desc":"2019-09-03 09:54:04 签到,积分：10 + 0","id":36988,"reason":"签到","type":1,"userId":5515,"userName":"953530507"}]
     * offset : 20
     * over : true
     * pageCount : 2
     * size : 20
     * total : 28
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
         * coinCount : 17
         * date : 1568865094000
         * desc : 2019-09-19 11:51:34 签到,积分：10 + 7
         * id : 48608
         * reason : 签到
         * type : 1
         * userId : 5515
         * userName : 953530507
         */

        private int coinCount;
        private long date;
        private String desc;
        private int id;
        private String reason;
        private int type;
        private int userId;
        private String userName;

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
