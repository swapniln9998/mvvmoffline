package com.swapnil.mvvm_offline.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class CommentDatabase_Impl extends CommentDatabase {
  private volatile CommentDao _commentDao;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Comment` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `photo_id` INTEGER NOT NULL, `comment_text` TEXT, `timestamp` INTEGER NOT NULL, `sync_pending` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"4677a6e24b49896f3ade9ae5c42fe8a1\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Comment`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsComment = new HashMap<String, TableInfo.Column>(5);
        _columnsComment.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsComment.put("photo_id", new TableInfo.Column("photo_id", "INTEGER", true, 0));
        _columnsComment.put("comment_text", new TableInfo.Column("comment_text", "TEXT", false, 0));
        _columnsComment.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0));
        _columnsComment.put("sync_pending", new TableInfo.Column("sync_pending", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysComment = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesComment = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoComment = new TableInfo("Comment", _columnsComment, _foreignKeysComment, _indicesComment);
        final TableInfo _existingComment = TableInfo.read(_db, "Comment");
        if (! _infoComment.equals(_existingComment)) {
          throw new IllegalStateException("Migration didn't properly handle Comment(com.swapnil.mvvm_offline.model.Comment).\n"
                  + " Expected:\n" + _infoComment + "\n"
                  + " Found:\n" + _existingComment);
        }
      }
    }, "4677a6e24b49896f3ade9ae5c42fe8a1");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Comment");
  }

  @Override
  public CommentDao commentDao() {
    if (_commentDao != null) {
      return _commentDao;
    } else {
      synchronized(this) {
        if(_commentDao == null) {
          _commentDao = new CommentDao_Impl(this);
        }
        return _commentDao;
      }
    }
  }
}
