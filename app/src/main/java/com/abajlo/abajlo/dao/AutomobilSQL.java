package com.abajlo.abajlo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.abajlo.abajlo.model.Automobil;

import java.util.List;

@Dao
public interface AutomobilSQL {

    @Query("Select * from automobili order by id")
    LiveData<List<Automobil>> dohvatiAutomobile();

    @Insert
    public void dodajAutomobil(Automobil automobil);

    @Delete
    public void obrisiAutomobil(Automobil automobil);

    @Update
    public void promjeniAutomobil(Automobil automobil);
}
