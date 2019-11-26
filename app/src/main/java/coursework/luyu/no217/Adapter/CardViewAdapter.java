package coursework.luyu.no217.Adapter;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.List;
import java.util.Random;

import coursework.luyu.no217.Activities.EventDetils;
import coursework.luyu.no217.JavaBeans.Event;
import coursework.luyu.no217.R;
import coursework.luyu.no217.Sqlite.DBManager;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.Holder> {

      private Context mContext217;
      private List<Event> list217 ;
      private LayoutInflater layoutInflater217;
      private Holder holder217;
      private SQLiteDatabase db217;

   /*
   * 在创建适配器需要将获取fragment中的上下文，同时初始化好的事件数据，
   * 从而才能显现到布局当中
   * */

    public CardViewAdapter(Context mContext217,List<Event> list217,SQLiteDatabase db217) {

        this.mContext217 = mContext217;
        this.list217 = list217;
        this.db217 = db217;
        layoutInflater217 = LayoutInflater.from(mContext217);

    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(mContext217==null){

            mContext217 =  parent.getContext();

        }

        holder217 = new Holder(mContext217,layoutInflater217.inflate(R.layout.cardview,null));

        return holder217;
    }

    /*
    * 给布局中的组件绑定数据
    * */
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        //通过生成随机数获取到随机的图片
        int num217 = new Random().nextInt(22)+1;

        holder.cardTitle.setText(list217.get(position).getTitle217());
        holder.cardContent.setText(list217.get(position).getContent217());
        holder.carTime.setText(list217.get(position).getTime217());

        int id = list217.get(position).getId();

        /*
        * 更新当前所有cardview的position
        * */
        DBManager dbManager217 = new DBManager(db217,mContext217);
        dbManager217.rewrite(String.valueOf(id),position,null,null,"id");
        setImage(num217,holder217);

        holder217.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent217 = new Intent(mContext217, EventDetils.class);
                intent217.putExtra("card_view_position",String.valueOf(position));
                mContext217.startActivity(intent217);


            }
        });

    }


    private void setImage(int num217, Holder holder) {

        switch (num217){

            case 1:
                Glide.with(mContext217).load(R.mipmap.picture1).into(holder.cardImage);
                break;
            case 2:
                Glide.with(mContext217).load(R.mipmap.picture2).into(holder.cardImage);
                break;
            case 3:
                Glide.with(mContext217).load(R.mipmap.picture3).into(holder.cardImage);
                break;
            case 4:
                Glide.with(mContext217).load(R.mipmap.picture4).into(holder.cardImage);
                break;
            case 5:
                Glide.with(mContext217).load(R.mipmap.picture5).into(holder.cardImage);
                break;
            case 6:
                Glide.with(mContext217).load(R.mipmap.picture6).into(holder.cardImage);
                break;
            case 7:
                Glide.with(mContext217).load(R.mipmap.picture7).into(holder.cardImage);
                break;
            case 8:
                Glide.with(mContext217).load(R.mipmap.picture8).into(holder.cardImage);
                break;
            case 9:
                Glide.with(mContext217).load(R.mipmap.picture9).into(holder.cardImage);
                break;
            case 10:
                Glide.with(mContext217).load(R.mipmap.picture10).into(holder.cardImage);
                break;
            case 11:
                Glide.with(mContext217).load(R.mipmap.picture11).into(holder.cardImage);
                break;
            case 12:
                Glide.with(mContext217).load(R.mipmap.picture12).into(holder.cardImage);
                break;
            case 13:
                Glide.with(mContext217).load(R.mipmap.picture13).into(holder.cardImage);
                break;
            case 14:
                Glide.with(mContext217).load(R.mipmap.picture14).into(holder.cardImage);
                break;
            case 15:
                Glide.with(mContext217).load(R.mipmap.picture15).into(holder.cardImage);
                break;
            case 16:
                Glide.with(mContext217).load(R.mipmap.picture16).into(holder.cardImage);
                break;
            case 17:
                Glide.with(mContext217).load(R.mipmap.picture17).into(holder.cardImage);
                break;
            case 18:
                Glide.with(mContext217).load(R.mipmap.picture18).into(holder.cardImage);
                break;

            case 19:
                Glide.with(mContext217).load(R.mipmap.picture19).into(holder.cardImage);
                break;
            case 20:
                Glide.with(mContext217).load(R.mipmap.picture20).into(holder.cardImage);
                break;
            case 21:
                Glide.with(mContext217).load(R.mipmap.picture21).into(holder.cardImage);
                break;
            case 22:
                Glide.with(mContext217).load(R.mipmap.picture22).into(holder.cardImage);
                break;

                default:
                    break;



        }



    }


    @Override
    public int getItemCount() {

        return list217.size();

    }

    class Holder extends RecyclerView.ViewHolder{

          public TextView cardTitle;
          public ImageView cardImage;
          public TextView cardContent;
          public CardView cardView;
          public TextView carTime;

          public Holder(Context mContext, View itemView) {
            super(itemView);

            cardTitle = (TextView)itemView.findViewById(R.id.cardTitle);
            cardImage = (ImageView) itemView.findViewById(R.id.carImage);
            cardContent = (TextView)itemView.findViewById(R.id.card_content);
            cardView = (CardView)itemView.findViewById(R.id.card);
            carTime = (TextView)itemView.findViewById(R.id.cardTime);

        }

    }

}
