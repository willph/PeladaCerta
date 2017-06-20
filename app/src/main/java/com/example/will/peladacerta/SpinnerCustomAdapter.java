package com.example.will.peladacerta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.will.peladacerta.models.PeladaModel;
import com.example.will.peladacerta.models.SoccerTeam;
import com.example.will.peladacerta.models.User;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafaelcadena on 15/06/17.
 */

public class SpinnerCustomAdapter extends BaseAdapter {

    private List<User> lista;
    private SoccerTeam soccerTeam;
    private LayoutInflater inflater;
    private Context context;

    public SpinnerCustomAdapter(Context context, SoccerTeam soccerTeam) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.soccerTeam = soccerTeam;
        lista = new ArrayList<>();

        for (int i = 0; i < soccerTeam.getListaJogadores().size(); i++){
            lista.add(soccerTeam.getListaJogadores().get(i));
        }


    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public User getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        View adapter = inflater.inflate(R.layout.spinner_custom_adapter,null);
        User user = lista.get(i);
        TextView col1 = (TextView) adapter.findViewById(R.id.spinner_custom_adapter_textView_item_1);
        TextView col2 = (TextView) adapter.findViewById(R.id.spinner_custom_adapter_textView_item_2);
        col1.setText(user.getNome());
        col2.setText(user.getPosition());
        return adapter;
    }

    public void setSoccerTeam(SoccerTeam soccerTeam) {
        this.soccerTeam = soccerTeam;
    }

    //    public class MulherPelada {
//
//        private String item1;
//        private String item2;
//
//        public MulherPelada(String item1, String item2) {
//            this.item1 = item1;
//            this.item2 = item2;
//        }
//
//        public String getItem1() {
//            return item1;
//        }
//
//        public void setItem1(String item1) {
//            this.item1 = item1;
//        }
//
//        public String getItem2() {
//            return item2;
//        }
//
//        public void setItem2(String item2) {
//            this.item2 = item2;
//        }
//    }
}
