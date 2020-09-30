package com.abajlo.abajlo.dao;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class AutomobilBaza_Impl extends AutomobilBaza {
  private volatile AutomobilSQL _automobilSQL;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `automobili` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nazivAutomobila` TEXT, `godisteAutomobila` TEXT, `opisAutomobila` TEXT, `slikaAutomobila` TEXT, `vrsta_automobila` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"993ff88f55dd2ea1f64ae72325d353ae\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `automobili`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsAutomobili = new HashMap<String, TableInfo.Column>(6);
        _columnsAutomobili.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsAutomobili.put("nazivAutomobila", new TableInfo.Column("nazivAutomobila", "TEXT", false, 0));
        _columnsAutomobili.put("godisteAutomobila", new TableInfo.Column("godisteAutomobila", "TEXT", false, 0));
        _columnsAutomobili.put("opisAutomobila", new TableInfo.Column("opisAutomobila", "TEXT", false, 0));
        _columnsAutomobili.put("slikaAutomobila", new TableInfo.Column("slikaAutomobila", "TEXT", false, 0));
        _columnsAutomobili.put("vrsta_automobila", new TableInfo.Column("vrsta_automobila", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAutomobili = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAutomobili = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAutomobili = new TableInfo("automobili", _columnsAutomobili, _foreignKeysAutomobili, _indicesAutomobili);
        final TableInfo _existingAutomobili = TableInfo.read(_db, "automobili");
        if (! _infoAutomobili.equals(_existingAutomobili)) {
          throw new IllegalStateException("Migration didn't properly handle automobili(com.abajlo.abajlo.model.Automobil).\n"
                  + " Expected:\n" + _infoAutomobili + "\n"
                  + " Found:\n" + _existingAutomobili);
        }
      }
    }, "993ff88f55dd2ea1f64ae72325d353ae", "7daa71cc18add07ca424e54539614661");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "automobili");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `automobili`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public AutomobilSQL automobilSQL() {
    if (_automobilSQL != null) {
      return _automobilSQL;
    } else {
      synchronized(this) {
        if(_automobilSQL == null) {
          _automobilSQL = new AutomobilSQL_Impl(this);
        }
        return _automobilSQL;
      }
    }
  }
}
