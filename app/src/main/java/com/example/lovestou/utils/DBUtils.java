package com.example.lovestou.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lovestou.bean.UserBean;
import com.example.lovestou.sqlite.SQLiteHelper;



public class DBUtils {
    private static DBUtils instance = null;
    private static SQLiteHelper helper;
    private static SQLiteDatabase db;
    public DBUtils(Context context) {
        helper = new SQLiteHelper(context);
        db = helper.getWritableDatabase();
    }
    public static DBUtils getInstance(Context context) {
        if (instance == null) {
            instance = new DBUtils(context);
        }
        return instance;
    }
    /*
 * 根据登录名获取用户头像
 */
    public String getUserHead(String userName) {
        String sql = "SELECT head FROM " + SQLiteHelper.U_USERINFO + " WHERE userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{userName});
        String head = "";
        while (cursor.moveToNext()) {
            head = cursor.getString(cursor.getColumnIndex("head"));
        }
        cursor.close();
        return head;
    }
    /**
     * 保存个人资料信息
     */
    public void saveUserInfo(UserBean bean) {
        ContentValues cv = new ContentValues();
        cv.put("userName", bean.getUserName());
        cv.put("nickName", bean.getNickName());
        cv.put("sex", bean.getSex());
        cv.put("signature", bean.getSignature());
        db.insert(SQLiteHelper.U_USERINFO, null, cv);
    }
    /**
     * 获取个人资料信息
     */
    public UserBean getUserInfo(String userName) {
        String sql = "SELECT * FROM " + SQLiteHelper.U_USERINFO + " WHERE userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{userName});
        UserBean bean = null;
        while (cursor.moveToNext()) {
            bean = new UserBean();
            bean.setUserName(cursor.getString(cursor.getColumnIndex("userName")));
            bean.setNickName(cursor.getString(cursor.getColumnIndex("nickName")));
            bean.setSex(cursor.getString(cursor.getColumnIndex("sex")));
            bean.setSignature(cursor.getString(cursor.getColumnIndex("signature")));
            bean.setHead(cursor.getString(cursor.getColumnIndex("head")));
        }
        cursor.close();
        return bean;
    }
    /**
     * 修改个人资料
     */
    public void updateUserInfo(String key, String value, String userName) {
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        db.update(SQLiteHelper.U_USERINFO, cv, "userName=?",new String[]{userName});
    }

    /**
     * 保存收藏信息
     */
//    public void saveCollectionNewsInfo(NewsBean bean, String userName) {
//        ContentValues cv = new ContentValues();
//        cv.put("id", bean.getId());
//        cv.put("type", bean.getType());
//        cv.put("userName", userName);
//        cv.put("newsName", bean.getNewsName());
//        cv.put("newsTypeName", bean.getNewsTypeName());
//        cv.put("img1", bean.getImg1());
//        cv.put("img2", bean.getImg2());
//        cv.put("img3", bean.getImg3());
//        cv.put("newsUrl", bean.getNewsUrl());
//        db.insert(SQLiteHelper.COLLECTION_NEWS_INFO, null, cv);
//    }
    /**
     * 获取收藏信息
     */
//    public List<NewsBean> getCollectionNewsInfo(String userName) {
//        String sql = "SELECT * FROM " + SQLiteHelper.COLLECTION_NEWS_INFO
//                + " WHERE  userName=? ";
//        Cursor cursor = db.rawQuery(sql, new String[]{userName});
//        List<NewsBean> newsList = new ArrayList<>();
//        NewsBean bean = null;
//        while (cursor.moveToNext()) {
//            bean = new NewsBean();
//            bean.setId(cursor.getInt(cursor.getColumnIndex("id")));
//            bean.setType(cursor.getInt(cursor.getColumnIndex("type")));
//            bean.setNewsName(cursor.getString(cursor.getColumnIndex("newsName")));
//            bean.setNewsTypeName(cursor.getString(cursor.getColumnIndex("newsTypeName")));
//            bean.setImg1(cursor.getString(cursor.getColumnIndex("img1")));
//            bean.setImg2(cursor.getString(cursor.getColumnIndex("img2")));
//            bean.setImg3(cursor.getString(cursor.getColumnIndex("img3")));
//            bean.setNewsUrl(cursor.getString(cursor.getColumnIndex("newsUrl")));
//            newsList.add(bean);
//        }
//        cursor.close();
//        return newsList;
//    }
    /**
     * 判断一条新闻是否被收藏
     */
    public boolean hasCollectionNewsInfo(int id, int type, String userName) {
        boolean hasNewsInfo = false;
        String sql = "SELECT * FROM " + SQLiteHelper.COLLECTION_NEWS_INFO
                + " WHERE id=? AND type=? AND userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{id + "", type + "", userName + ""});
        if (cursor.moveToFirst()) {
            hasNewsInfo = true;
        }
        cursor.close();
        return hasNewsInfo;
    }
    /**
     * 删除某一条收藏信息
     */
    public boolean delCollectionNewsInfo(int id, int type, String userName) {
        boolean delSuccess = false;
        if (hasCollectionNewsInfo(id, type, userName)) {
            int row = db.delete(SQLiteHelper.COLLECTION_NEWS_INFO,
                    " id=? AND type=? AND userName=? ", new String[]{id + "", type + "", userName});
            if (row > 0) {
                delSuccess = true;
            }
        }
        return delSuccess;
    }

}
