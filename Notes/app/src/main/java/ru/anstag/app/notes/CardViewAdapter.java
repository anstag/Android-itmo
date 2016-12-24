package ru.anstag.app.notes;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {

    private String[] name, description;


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
    public CardViewAdapter(String[] name, String[] description){
        this.name = name;
        this.description = description;
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

        TextView names = (TextView) cardView.findViewById(R.id.name);
        TextView description1 = (TextView) cardView.findViewById(R.id.description);

        // Название выводится в компоненте TextView
        names.setText(name[position]);
        description1.setText(description[position]);
    }

    @Override
    public int getItemCount(){
        // Возвращает количество вариантов в наборе данных
        return name.length;
    }
}

