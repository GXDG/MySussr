package com.example.mysussrx.config;

/**
 * Created by Administrator on 2017/12/20.
 */

public class AppConfig {

    public static final String BASE_URL = "http://m.privyDate.com/";//BaseURL
    public static final String URL_EVENT = "?event=iosapi.iosuser.index";//Event key-value
    public static final String API_ACCESS_KEY = "3d3d158b-9c41-46a5-95f4-28f95eb834e9";//API接口访问key

    public static final String MOB_APPKEY = "232e8b94e793c";
    public static final String MOB_APPSECRET = "6ee9bc50a1a89865921c9427127d006a";
    public static final String TYPE = "type";//选择以什么身份进行注册或者登录的页面
    public static final String DATER = "dater";//dater 类型
    public static final String MATCHMAKER = "matchmaker";//matchmaker 类型

    public static final String BUGLY_APP_ID = "99d805828f";//灰度升级、异常上报appid

    public static final String KEY_ERROR = "ERROR";//返回数据ERROR字段

    public static final String ACTION = "ACTION";//POST请求方法字段
    public static final String USER_ID = "user_id";//POST请求用户名字段
    public static final String PHOTO_URL="photo";//头像url
    public static final String MATCHMAKER_USER_ID="matchmakerUserID";//POST请求用户角色字段
    public static final String VOUCHED_DATER_PROFILE_ID="vouchedDaterProfileID";//POST请求，profile要传入的id

    public static final String VOUCH_POOL_TAB_ID="voucheesPoolTabID";//POST请求tab id字段
    public static final String TOTAL_TAB_VOUCHEES_DISPLAYED="totalTabVoucheesDisplayed";//POST请求，已经显示了的数目
    public static final String VOUCHING_HISTORY_ID="vouchingHistoryID";//POST请求，dater匹配历史id
    public static final String VOUCHEE_USER_ID="voucheeUserID";//POST请求，要匹配的朋友的id
    public static final String TOTAL_SPIN_MATCHES_DISPLAYED="totalSpinmatchesDisplayed";//POST请求，已经显示了的要匹配的朋友数目
    public static final String TOTAL_APPROVAL_INTRODUCTIONS_DISPLAYED="totalApprovalIntroductionsDisplayed";//POST请求，已经显示了的要介绍的朋友数目
    public static final String MATCHMAKING_HISTORY_ID="matchmakingHistoryID";//POST请求，matchmaker配对dater历史id
    public static final String INTRODUCED_VOUCHED_DATER_PROFILE_ID="introducedVouchedDaterProfileID";//POST请求，介绍的dater profile id
    public static final String MATCHMAKER_SUCCESS_POOL_TAB_ID="matchmakerSuccessPoolTabID";//POST请求,成功匹配的tab，1|2
    public static final String TOTAL_SUCCESS_TAB_DISPLAYED="totalSuccessTabDisplayed";//POST请求，已经显示了的匹配成功的数目
    public static final String UNIVERSITY_RECORD_ID = "university_record_id";//大学id
    public static final String TOTAL_VOUCHEES_PHOTOS_DISPLAYED = "totalVoucheePhotosDisplayed";//已显示的图片数量

    public static String TEST_USER_ID = "1556";//用于测试的userD 可在测试页面中修改
}
