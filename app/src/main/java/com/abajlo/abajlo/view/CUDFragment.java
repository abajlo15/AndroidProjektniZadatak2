package com.abajlo.abajlo.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.abajlo.abajlo.R;
import com.abajlo.abajlo.viewModel.AutomobilViewModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CUDFragment extends Fragment {

    static final int SLIKANJE =1;

    private String trenutnaPutanjaSlike;

    @BindView(R.id.imeAutomobila)
    EditText imeAutomobila;
    @BindView(R.id.vrsta_automobila)
    Spinner vrsta_automobila;
    @BindView(R.id.godina)
    EditText godina;
    @BindView(R.id.opis)
    EditText opis;
    @BindView(R.id.slika)
    ImageView slika;
    @BindView(R.id.noviAutomobil)
    Button noviAutomobil;
    @BindView(R.id.Slikaj)
    Button Slikaj;
    @BindView(R.id.promjenaAutomobila)
    Button promjenaAutomobila;
    @BindView(R.id.obrisiAutomobil)
    Button obrisiAutomobil;

    AutomobilViewModel model;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cud,
                container, false);
        ButterKnife.bind(this, view);

        model = ((MainActivity) getActivity()).getModel();

        if (model.getAutomobil().getId() == 0) {
            definirajNoviAutomobil();
            return view;
        }
        definirajPromjenaBrisanjeAutomobila();

        return view;
    }


    private void definirajPromjenaBrisanjeAutomobila() {
        noviAutomobil.setVisibility(View.GONE);
        vrsta_automobila.setSelection(model.getAutomobil().getVrsta_automobila());
        imeAutomobila.setText(model.getAutomobil().getNazivAutomobila());
        godina.setText(model.getAutomobil().getGodisteAutomobila());
        opis.setText(model.getAutomobil().getOpisAutomobila());
        Picasso.get().load(model.getAutomobil().getSlikaAutomobila()).error(R.drawable.automobil).into(slika);

        Slikaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uslikaj();
            }
        });

        promjenaAutomobila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promjenaAutomobila();
            }
        });

        obrisiAutomobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obrisiAutomobil();
            }
        });


    }

    private void promjenaAutomobila() {
        model.getAutomobil().setNazivAutomobila(imeAutomobila.getText().toString());
        model.getAutomobil().setVrsta_automobila(vrsta_automobila.getSelectedItemPosition());
        model.getAutomobil().setGodisteAutomobila(godina.getText().toString());
        model.getAutomobil().setOpisAutomobila(opis.getText().toString());
        model.promjeniAutomobil();
        nazad();
    }

    private void definirajNoviAutomobil() {
        promjenaAutomobila.setVisibility(View.GONE);
        obrisiAutomobil.setVisibility(View.GONE);
        Slikaj.setVisibility(View.GONE);
        noviAutomobil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noviAutomobil();
            }
        });
    }

    private void noviAutomobil() {
        model.getAutomobil().setNazivAutomobila(imeAutomobila.getText().toString());
        vrsta_automobila.setSelection(0);
        model.getAutomobil().setVrsta_automobila(vrsta_automobila.getSelectedItemPosition());
        model.getAutomobil().setGodisteAutomobila(godina.getText().toString());
        model.getAutomobil().setOpisAutomobila(opis.getText().toString());
        model.dodajNoviAutomobil();
        nazad();
    }


    private void obrisiAutomobil() {
        model.getAutomobil().setNazivAutomobila(imeAutomobila.getText().toString());
        model.getAutomobil().setVrsta_automobila(vrsta_automobila.getSelectedItemPosition());
        model.getAutomobil().setGodisteAutomobila(godina.getText().toString());
        model.getAutomobil().setOpisAutomobila(opis.getText().toString());
        model.obrisiAutomobil();
        nazad();
    }

    @OnClick(R.id.natrag)
    public void nazad() {
        ((MainActivity) getActivity()).read();
    }

    private void uslikaj() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) == null) {
            Toast.makeText(getActivity(), "Problem kod kreiranja slike", Toast.LENGTH_LONG).show();
            return;

        }

        File slika = null;
        try {
            slika = kreirajDatotekuSlike();
        } catch (IOException ex) {
            Toast.makeText(getActivity(), "Problem kod kreiranja slike", Toast.LENGTH_LONG).show();
            return;
        }

        if (slika == null) {
            Toast.makeText(getActivity(), "Problem kod kreiranja slike", Toast.LENGTH_LONG).show();
            return;
        }

        Uri slikaURI = FileProvider.getUriForFile(getActivity(),"com.abajlo.provider", slika);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, slikaURI);
        startActivityForResult(takePictureIntent, SLIKANJE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SLIKANJE && resultCode == Activity.RESULT_OK) {

            model.getAutomobil().setSlikaAutomobila("file://" + trenutnaPutanjaSlike);
            model.promjeniAutomobil();
            Picasso.get().load(model.getAutomobil().getSlikaAutomobila()).into(slika);

        }
    }

    private File kreirajDatotekuSlike() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imeSlike = "AUTOMOBIL" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imeSlike,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        trenutnaPutanjaSlike = image.getAbsolutePath();
        return image;
    }

}
