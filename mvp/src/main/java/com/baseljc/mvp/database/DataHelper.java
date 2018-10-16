//package com.linet.mvp.database;
//
//import android.content.Context;
//
//import org.greenrobot.greendao.database.Database;
//
///**
// * Created by linet on 2017/5/12.
// */
//
//public abstract class DataHelper<T, C> {
//
//
//    /**
//     * A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher.
//     */
//    public static final boolean ENCRYPTED = false;
//    public static String DB_NAME = ENCRYPTED ? "notes-db-encrypted" : "notes-db";
//
//    protected Context mContext;
//    protected Database mDatabase;
//    protected T mDaoMaster;
//    protected C mDaoSession;
//
//    public DataHelper(Context context) {
//        mContext = context;
//        init(mContext);
//    }
//
//    public Database getDatabase() {
//        return mDatabase;
//    }
//
//    public T getDaoMaster() {
//        return mDaoMaster;
//    }
//
//    public C getDaoSession() {
//        return mDaoSession;
//    }
//
//    public abstract void init(Context context);
//
//}
