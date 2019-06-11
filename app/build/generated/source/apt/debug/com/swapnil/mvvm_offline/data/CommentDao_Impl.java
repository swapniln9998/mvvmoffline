package com.swapnil.mvvm_offline.data;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.RxRoom;
import android.database.Cursor;
import com.swapnil.mvvm_offline.model.Comment;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CommentDao_Impl implements CommentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfComment;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfComment;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfComment;

  public CommentDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfComment = new EntityInsertionAdapter<Comment>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Comment`(`id`,`photo_id`,`comment_text`,`timestamp`,`sync_pending`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Comment value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getPhotoId());
        if (value.getCommentText() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCommentText());
        }
        stmt.bindLong(4, value.getTimestamp());
        final int _tmp;
        _tmp = value.isSyncPending() ? 1 : 0;
        stmt.bindLong(5, _tmp);
      }
    };
    this.__deletionAdapterOfComment = new EntityDeletionOrUpdateAdapter<Comment>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Comment` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Comment value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfComment = new EntityDeletionOrUpdateAdapter<Comment>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Comment` SET `id` = ?,`photo_id` = ?,`comment_text` = ?,`timestamp` = ?,`sync_pending` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Comment value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getPhotoId());
        if (value.getCommentText() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCommentText());
        }
        stmt.bindLong(4, value.getTimestamp());
        final int _tmp;
        _tmp = value.isSyncPending() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        stmt.bindLong(6, value.getId());
      }
    };
  }

  @Override
  public long add(Comment comment) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfComment.insertAndReturnId(comment);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Comment comment) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfComment.handle(comment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(Comment comment) {
    __db.beginTransaction();
    try {
      __updateAdapterOfComment.handle(comment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Flowable<List<Comment>> getComments(long photoId) {
    final String _sql = "SELECT * FROM comment WHERE photo_id = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, photoId);
    return RxRoom.createFlowable(__db, new String[]{"comment"}, new Callable<List<Comment>>() {
      public List<Comment> call() throws Exception {
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfPhotoId = _cursor.getColumnIndexOrThrow("photo_id");
          final int _cursorIndexOfCommentText = _cursor.getColumnIndexOrThrow("comment_text");
          final int _cursorIndexOfTimestamp = _cursor.getColumnIndexOrThrow("timestamp");
          final int _cursorIndexOfSyncPending = _cursor.getColumnIndexOrThrow("sync_pending");
          final List<Comment> _result = new ArrayList<Comment>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Comment _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpPhotoId;
            _tmpPhotoId = _cursor.getLong(_cursorIndexOfPhotoId);
            final String _tmpCommentText;
            _tmpCommentText = _cursor.getString(_cursorIndexOfCommentText);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpSyncPending;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSyncPending);
            _tmpSyncPending = _tmp != 0;
            _item = new Comment(_tmpId,_tmpPhotoId,_tmpCommentText,_tmpTimestamp,_tmpSyncPending);
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
    });
  }
}
