package com.example.asus.mysqlitedemo.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.asus.mysqlitedemo.sqlite.MySqliteOpenHalper;

public class MyContentProvider extends ContentProvider {
    private SQLiteDatabase db;
    private MySqliteOpenHalper helper;

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final String PREFIX = "content://";
    private static final String SPLIT = "/";
    private static final String PATH_CONTACT = MySqliteOpenHalper.TABLE;
    private static final String AUTHORITES = "com.simple.contact.v2.provider";
    private static final int URI_MATCHER_CONTACT = 1;

    public static final String CONTENT_URI_CONTACT =
            PREFIX + AUTHORITES + SPLIT + PATH_CONTACT;

    static {
        matcher.addURI(AUTHORITES,PATH_CONTACT,URI_MATCHER_CONTACT);
    }

    public MyContentProvider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return db.delete(MySqliteOpenHalper.TABLE,
                selection,
                selectionArgs
                );
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db.insert(MySqliteOpenHalper.TABLE,null,values);
        return Uri.parse(MyContentProvider.CONTENT_URI_CONTACT);
    }

    @Override
    public boolean onCreate() {
        this.helper = new MySqliteOpenHalper(this.getContext(),"db_user",null,1);
        this.db = this.helper.getWritableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = this.db.query(MySqliteOpenHalper.TABLE, projection, selection, selectionArgs, null, null, null);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return db.update(
                MySqliteOpenHalper.TABLE,
                values,
                selection,
                selectionArgs
        );
    }
}
