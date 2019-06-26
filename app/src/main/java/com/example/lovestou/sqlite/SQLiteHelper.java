package com.example.lovestou.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    public static String DB_NAME = "topline.db";
    public static final String U_USERINFO = "userinfo"; //用户信息
    public static final String CONSTELLATION = "constellation";//十二星座信息
    //收藏新闻信息
    public static final String COLLECTION_NEWS_INFO = "collection_news_info";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 创建用户信息表
         */
        db.execSQL("CREATE TABLE  IF NOT EXISTS " + U_USERINFO + "( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "userName VARCHAR, "  //用户名
                + "nickName VARCHAR, "  //昵称
                + "sex VARCHAR, "        //性别
                + "signature VARCHAR,"  //签名
                + "head VARCHAR "        //头像
                + ")");
        /**
         * 创建收藏表
         */
        db.execSQL("CREATE TABLE  IF NOT EXISTS " + COLLECTION_NEWS_INFO + "( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "id INTEGER, "         //新闻id
                + "type INTEGER, "      //新闻类型
                + "userName VARCHAR," //用户名
                + "newsName VARCHAR, "      //新闻名称
                + "newsTypeName VARCHAR," //新闻类型名称
                + "img1 VARCHAR, "        //图片1
                + "img2 VARCHAR, "       //图片2
                + "img3 VARCHAR, "      //图片3
                + "newsUrl VARCHAR "  //新闻链接地址
                + ")");
    }

    /**
     * 当数据库版本号增加时才会调用此方法
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + U_USERINFO);
        db.execSQL("DROP TABLE IF EXISTS " + CONSTELLATION);
        db.execSQL("DROP TABLE IF EXISTS " + COLLECTION_NEWS_INFO);
        onCreate(db);
    }
}
