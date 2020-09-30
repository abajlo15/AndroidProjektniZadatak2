package com.abajlo.abajlo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.abajlo.abajlo.R;
import com.abajlo.abajlo.model.Automobil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AutomobilAdapter extends ArrayAdapter<Automobil> {

    public List<Automobil> getListaAutomobila() {
        return listaAutomobila;
    }

    public void setListaAutomobila(List<Automobil> listaAutomobila) {
        this.listaAutomobila = listaAutomobila;
    }

    private List<Automobil> listaAutomobila;
    private AutomobilClickListener automobilClickListener;
    private Context context;
    private int resource;

    public AutomobilAdapter(@NonNull Context context, int resource, AutomobilClickListener automobilClickListener) {
        super(context, resource);

        this.resource = resource;
        this.context = context;
        this.automobilClickListener = automobilClickListener;
    }

    private static class ViewHolder{

        private ImageView slika;
        private TextView nazivAutomobila;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        final Automobil automobil;
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                view = inflater.inflate(this.resource, null);

                viewHolder.nazivAutomobila = view.findViewById(R.id.vrsta_automobila);
                viewHolder.slika = view.findViewById(R.id.slika);
            } else {
                viewHolder = (ViewHolder) view.getTag();

            }

            automobil = getItem(position);

            if (automobil != null) {

                viewHolder.nazivAutomobila.setText(automobil.getNazivAutomobila() + " - " + context.getResources().getStringArray(R.array.vrsta_automobila)[automobil.getVrsta_automobila()]);

                if (automobil.getSlikaAutomobila() == null) {
                    Picasso.get().load(R.drawable.automobil).fit().centerCrop().into(viewHolder.slika);
                } else {
                    Picasso.get().load(automobil.getSlikaAutomobila()).fit().centerCrop().into(viewHolder.slika);
                }
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    automobilClickListener.onItemClick(automobil);
                }
            });
        }
        return view;
    }

    @Override
    public int getCount() {
        return listaAutomobila == null ? 0 : listaAutomobila.size();
    }

    @Nullable
    @Override
    public Automobil getItem(int position) {
        return listaAutomobila.get(position);
    }

    public void setPodaci(List<Automobil> Automobila) {
        this.listaAutomobila = Automobila;
    }

    public void osvjeziPodatke() {
        notifyDataSetChanged();
    }
}
