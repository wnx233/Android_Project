package com.example.xlhhlocation.com.user;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBOpenHelper extends SQLiteOpenHelper {

    public DBOpenHelper(Context context){
        super(context,"db_text",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "password TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
    /**
     * 接下来写自定义的增删改查方法
     * 这些方法，写在这里归写在这里，以后不一定都用
     * add()
     * delete()
     * update()
     * getAllData()
     */
    public void add(SQLiteDatabase db, String name, String password){
        db.execSQL("INSERT INTO user (name,password) VALUES(?,?)",new Object[]{name,password});
    }
    public void delete(SQLiteDatabase db, String name, String password){
        db.execSQL("DELETE FROM user WHERE name = AND password ="+name+password);
    }
    public void updata(SQLiteDatabase db, String password){
        db.execSQL("UPDATE user SET password = ?",new Object[]{password});
    }

    /**
     * 前三个没啥说的，都是一套的看懂一个其他的都能懂了
     * 下面重点说一下查询表user全部内容的方法
     * 我们查询出来的内容，需要有个容器存放，以供使用，
     * 所以定义了一个ArrayList类的list
     * 有了容器，接下来就该从表中查询数据了，
     * 这里使用游标Cursor，这就是数据库的功底了，
     * 在Android中我就不细说了，因为我数据库功底也不是很厚，
     * 但我知道，如果需要用Cursor的话，第一个参数："表名"，中间5个：null，
     *                                                     最后是查询出来内容的排序方式："name DESC"
     * 游标定义好了，接下来写一个while循环，让游标从表头游到表尾
     * 在游的过程中把游出来的数据存放到list容器中
     * @return
     */

    public ArrayList<User> getAllData(SQLiteDatabase db){

        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(name,password));
        }
        return list;
    }
}
