package com.abajlo.abajlo.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.abajlo.abajlo.dao.AutomobilBaza;
import com.abajlo.abajlo.dao.AutomobilSQL;
import com.abajlo.abajlo.model.Automobil;

import java.util.List;

public class AutomobilViewModel extends AndroidViewModel {

     AutomobilSQL automobilSQl;
    private Automobil automobil;

    public Automobil getAutomobil() {
        return automobil;
    }
    public void setAutomobil(Automobil automobil){this.automobil = automobil;}

    private LiveData<List<Automobil>> listaAutomobila;


    public AutomobilViewModel(Application application) {
        super(application);
        automobilSQl = AutomobilBaza.getBaza(application.getApplicationContext()).automobilSQL();
    }

    public LiveData<List<Automobil>> dohvatiAutomobile(){
        listaAutomobila = automobilSQl.dohvatiAutomobile();
        return listaAutomobila;
    }

    public void dodajNoviAutomobil(){

        automobilSQl.dodajAutomobil(automobil);
    }

    public void promjeniAutomobil(){

        automobilSQl.promjeniAutomobil(automobil);
    }

    public void obrisiAutomobil(){

       automobilSQl.obrisiAutomobil(automobil);
    }
}
