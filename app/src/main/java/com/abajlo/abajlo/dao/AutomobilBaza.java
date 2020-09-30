package com.abajlo.abajlo.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.abajlo.abajlo.model.Automobil;

//singleton
@Database(entities = {Automobil.class}, version = 1, exportSchema = false)
public abstract class AutomobilBaza extends RoomDatabase {

    public abstract AutomobilSQL automobilSQL();

    private static AutomobilBaza bazaHandler;

    public static AutomobilBaza getBaza(Context context){
        if(bazaHandler == null){
            bazaHandler = Room.databaseBuilder(context.getApplicationContext(), AutomobilBaza.class, "automobil-baza").allowMainThreadQueries().build();
        }
        return bazaHandler;
    };

    public static void deleteInstance(){
        bazaHandler = null;
    }
}
