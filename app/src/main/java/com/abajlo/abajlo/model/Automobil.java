package com.abajlo.abajlo.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;



@Entity(tableName = "automobili")
public class Automobil implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivAutomobila() {
        return nazivAutomobila;
    }

    public void setNazivAutomobila(String nazivAutomobila) {
        this.nazivAutomobila = nazivAutomobila;
    }

    public String getGodisteAutomobila() {
        return godisteAutomobila;
    }

    public void setGodisteAutomobila(String godisteAutomobila) {
        this.godisteAutomobila = godisteAutomobila;
    }

    public String getOpisAutomobila() {
        return opisAutomobila;
    }

    public void setOpisAutomobila(String opisAutomobila) {
        this.opisAutomobila = opisAutomobila;
    }

    public String getSlikaAutomobila() {
        return slikaAutomobila;
    }

    public void setSlikaAutomobila(String slikaAutomobila) {
        this.slikaAutomobila = slikaAutomobila;
    }

    private String nazivAutomobila;
    private String godisteAutomobila;
    private String opisAutomobila;
    private String slikaAutomobila;
    private int vrsta_automobila;

    public int getVrsta_automobila() {
        return vrsta_automobila;
    }

    public void setVrsta_automobila(int vrsta_automobila) {
        this.vrsta_automobila = vrsta_automobila;
    }
}
