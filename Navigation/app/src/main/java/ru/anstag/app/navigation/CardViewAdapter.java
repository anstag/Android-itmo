package ru.anstag.app.navigation;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import android.support.v7.widget.CardView;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {

    private String[] captions;
    private int[] imageIds;

    // Представляет ссылку на представления, используемые в RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;

        /*Каждый вариант в RecyclerView представляет собой карточку; следовательно, класс
            ViewHolder должен содержать CardView */

        public ViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }

    // Данные передаваемые адаптеру через конструктор
    public CardViewAdapter(String[] captions, int[] imageIds){
        this.captions = captions;
        this.imageIds = imageIds;
    }

    @Override
    public CardViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Создание нового представления
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                // Указываем какой макет используем
                .inflate(R.layout.card_view, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        // Заполнениезаданного представления данными
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.info_image);
        // Изображения выводятся в графическом представлении ImageView

        Drawable drawable = cardView.getResources().getDrawable(imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);

        TextView textView = (TextView) cardView.findViewById(R.id.info_text);
        // Название выводится в компоненте TextView
        textView.setText(captions[position]);
    }

    @Override
    public int getItemCount(){
        // Возвращает количество вариантов в наборе данных
        return captions.length;
    }
}
