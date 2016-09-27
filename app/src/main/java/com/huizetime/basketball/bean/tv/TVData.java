package com.huizetime.basketball.bean.tv;

/**
 * Created by water_fairy on 2016/9/22.
 */
public class TVData {

    public final static int TYPE_SIGN = 1;//签到显示
    public final static int TYPE_INIT_SCORE = 2;//初始化比分界面
    public final static int TYPE_EVENT = 3;//比分界面改变
    public final static int TYPE_A_LOGO = 4;//a球队图标
    public final static int TYPE_B_LOGO = 5;//b球队图标
    public final static int TYPE_QR_CODE = 6;//二维码
    public final static int TYPE_CLOSE = -1;//手动关闭连接
    public final static int TYPE_RESULT = 8;

    public final static int RESULT_OK = 1;//回执:成功
    public final static int RESULT_ERROR = 2;//回执:失败
    public final static int RESULT_WAITING = 3;//回执:请等候

    /**
     * 请求code
     * 1.签到显示
     * 2.初始化比分界面
     * 3.比分界面改变
     * <p>
     * 4.a球队图标
     * 5.b球队图标
     * 6.二维码
     */
    private int code;//code
    private TVEventBean tvScoreEventBean;//记分板事件(比分界面改变)
    private TVScoreBean tvScoreBean;//记分板总计得分显示(初始化)
    private TVSignBean tvSignBean;//签到数据
    private String img;//图片资源 base64
    private int result;//返回结果

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public TVScoreBean getTvScoreBean() {
        return tvScoreBean;
    }

    public void setTvScoreBean(TVScoreBean tvScoreBean) {
        this.tvScoreBean = tvScoreBean;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public TVSignBean getTvSignBean() {
        return tvSignBean;
    }

    public void setTvSignBean(TVSignBean tvSignBean) {
        this.tvSignBean = tvSignBean;
    }

    public TVEventBean getTvScoreEventBean() {
        return tvScoreEventBean;
    }

    public void setTvScoreEventBean(TVEventBean tvScoreEventBean) {
        this.tvScoreEventBean = tvScoreEventBean;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
