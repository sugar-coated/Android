package coursework.luyu.no217.Sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import coursework.luyu.no217.JavaBeans.Event;

public class DBManager {

    private SQLiteDatabase db217;
    private Context mContext217;


    public DBManager(SQLiteDatabase db,Context context) {

        if(db==null){

            Helper helper217 = new Helper(mContext217,"TuoGe.db",null,1);

            db217 = helper217.getWritableDatabase();

        }
        this.db217 = db;
        this.mContext217 = context;

    }


    public void  insert(String title,String type,String time,String content){


        ContentValues values = new ContentValues();

        values.put("title",title);
        values.put("time",time);
        values.put("content",content);
        values.put("type",type);
        values.put("position","-1");

        db217.insert("event",null,values);
        Toast.makeText(mContext217,"插入成功",Toast.LENGTH_LONG).show();

        /*
        * 实现通过添加记录添加的事件数量
        * */

        record_data(0);
       /* SharedPreferences sharedPreferences217 = mContext217.getSharedPreferences("user1",mContext217.MODE_PRIVATE);
        SharedPreferences.Editor editor217 = sharedPreferences217.edit();
        int record_times217 = sharedPreferences217.getInt("record_times",0);
        int result217 = record_times217 + 1;
        editor217.putInt("record_times",result217);
        editor217.apply();
*/
    }


    public List<Event> read (String position217){

        List<Event> list217 = new LinkedList<>();
        Cursor cursor217;

        if(position217 != null){

            cursor217 = db217.query("event",null,"position=?",new String[]{position217},null,null,"id desc");

        }else{

          cursor217 = db217.query("event",null,null,null,null,null,"id desc");
        }



        if(cursor217.moveToFirst()){

            do{

                Event event217 = new Event();

                event217.setType217(cursor217.getString(cursor217.getColumnIndex("type")));
                event217.setTitle217(cursor217.getString(cursor217.getColumnIndex("title")));
                event217.setTime217(cursor217.getString(cursor217.getColumnIndex("time")));
                event217.setContent217(cursor217.getString(cursor217.getColumnIndex("content")));
                event217.setId(cursor217.getInt(cursor217.getColumnIndex("id")));

                list217.add(event217);


            }while (cursor217.moveToNext());

         }

         return list217;
    }

    public  void  rewrite(String condition,int position,String title,String content,String flag){

        ContentValues values217 = new ContentValues();


        switch (flag){
            case "id":
                values217.put("position",String.valueOf(position));
                db217.update("event",values217,"id=?",new String[]{condition});

                break;

            case "position":

                values217.put("content",content);
                values217.put("title",title);
                db217.update("event",values217,"position=?",new String[]{condition});

                break;

            default:
                break;

        }



    }

    public  void delete(String condition){

        db217.delete("event","position=?",new String[]{condition});
        /*
        * 试下你
        * */

        record_data(1);
       /*
        int finish_times = sharedPreferences217.getInt("finish_times",0);
        editor217.putInt("finish_times",finish_times++);
        editor217.apply();*/

    }

    private void record_data(int type){
        SharedPreferences sharedPreferences217 = mContext217.getSharedPreferences("user1",mContext217.MODE_PRIVATE);
        SharedPreferences.Editor editor217 = sharedPreferences217.edit();

        if(type==0){

            int record_times217 = sharedPreferences217.getInt("record_times",0);
            int result217 = record_times217 + 1;
            editor217.putInt("record_times",result217);



        }else if(type==1){

            int finish_times217 = sharedPreferences217.getInt("finish_times",0);
            int result217 = finish_times217 + 1;
            editor217.putInt("finish_times",result217);

        }
        editor217.apply();

    }



}
