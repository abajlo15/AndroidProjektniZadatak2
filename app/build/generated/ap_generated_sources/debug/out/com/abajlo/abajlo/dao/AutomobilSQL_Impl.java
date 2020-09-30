package com.abajlo.abajlo.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.abajlo.abajlo.model.Automobil;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public final class AutomobilSQL_Impl implements AutomobilSQL {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfAutomobil;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfAutomobil;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfAutomobil;

  public AutomobilSQL_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAutomobil = new EntityInsertionAdapter<Automobil>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `automobili`(`id`,`nazivAutomobila`,`godisteAutomobila`,`opisAutomobila`,`slikaAutomobila`,`vrsta_automobila`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Automobil value) {
        stmt.bindLong(1, value.getId());
        if (value.getNazivAutomobila() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNazivAutomobila());
        }
        if (value.getGodisteAutomobila() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getGodisteAutomobila());
        }
        if (value.getOpisAutomobila() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getOpisAutomobila());
        }
        if (value.getSlikaAutomobila() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSlikaAutomobila());
        }
        stmt.bindLong(6, value.getVrsta_automobila());
      }
    };
    this.__deletionAdapterOfAutomobil = new EntityDeletionOrUpdateAdapter<Automobil>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `automobili` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Automobil value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfAutomobil = new EntityDeletionOrUpdateAdapter<Automobil>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `automobili` SET `id` = ?,`nazivAutomobila` = ?,`godisteAutomobila` = ?,`opisAutomobila` = ?,`slikaAutomobila` = ?,`vrsta_automobila` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Automobil value) {
        stmt.bindLong(1, value.getId());
        if (value.getNazivAutomobila() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNazivAutomobila());
        }
        if (value.getGodisteAutomobila() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getGodisteAutomobila());
        }
        if (value.getOpisAutomobila() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getOpisAutomobila());
        }
        if (value.getSlikaAutomobila() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSlikaAutomobila());
        }
        stmt.bindLong(6, value.getVrsta_automobila());
        stmt.bindLong(7, value.getId());
      }
    };
  }

  @Override
  public void dodajAutomobil(Automobil automobil) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfAutomobil.insert(automobil);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void obrisiAutomobil(Automobil automobil) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfAutomobil.handle(automobil);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void promjeniAutomobil(Automobil automobil) {
    __db.beginTransaction();
    try {
      __updateAdapterOfAutomobil.handle(automobil);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Automobil>> dohvatiAutomobile() {
    final String _sql = "Select * from automobili order by id";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Automobil>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Automobil> compute() {
        if (_observer == null) {
          _observer = new Observer("automobili") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfNazivAutomobila = _cursor.getColumnIndexOrThrow("nazivAutomobila");
          final int _cursorIndexOfGodisteAutomobila = _cursor.getColumnIndexOrThrow("godisteAutomobila");
          final int _cursorIndexOfOpisAutomobila = _cursor.getColumnIndexOrThrow("opisAutomobila");
          final int _cursorIndexOfSlikaAutomobila = _cursor.getColumnIndexOrThrow("slikaAutomobila");
          final int _cursorIndexOfVrstaAutomobila = _cursor.getColumnIndexOrThrow("vrsta_automobila");
          final List<Automobil> _result = new ArrayList<Automobil>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Automobil _item;
            _item = new Automobil();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpNazivAutomobila;
            _tmpNazivAutomobila = _cursor.getString(_cursorIndexOfNazivAutomobila);
            _item.setNazivAutomobila(_tmpNazivAutomobila);
            final String _tmpGodisteAutomobila;
            _tmpGodisteAutomobila = _cursor.getString(_cursorIndexOfGodisteAutomobila);
            _item.setGodisteAutomobila(_tmpGodisteAutomobila);
            final String _tmpOpisAutomobila;
            _tmpOpisAutomobila = _cursor.getString(_cursorIndexOfOpisAutomobila);
            _item.setOpisAutomobila(_tmpOpisAutomobila);
            final String _tmpSlikaAutomobila;
            _tmpSlikaAutomobila = _cursor.getString(_cursorIndexOfSlikaAutomobila);
            _item.setSlikaAutomobila(_tmpSlikaAutomobila);
            final int _tmpVrsta_automobila;
            _tmpVrsta_automobila = _cursor.getInt(_cursorIndexOfVrstaAutomobila);
            _item.setVrsta_automobila(_tmpVrsta_automobila);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
