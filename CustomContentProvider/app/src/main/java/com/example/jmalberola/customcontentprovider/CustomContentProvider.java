package com.example.jmalberola.customcontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

/**
 * Created by jmalberola
 */
public class CustomContentProvider extends ContentProvider {
    // Campos del content provider
    static final String PROVIDER_NAME = "com.example.jmalberola.customcontentprovider";
    static final String URL = "content://" + PROVIDER_NAME + "/discos";
    static final Uri CONTENT_URI = Uri.parse(URL);

    // campos de la BBDD
    static final String _ID = "_id";
    static final String TITLE = "title";
    static final String YEAR = "year";

    MyDbHelper dbHelper;

    // Constantes usados para el content URI
    static final int DISCOS = 1;
    static final int DISCOS_ID = 2;

    // Mapeo de patrones de content URI a los valores definidos arriba
    static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"discos",DISCOS);
        uriMatcher.addURI(PROVIDER_NAME,"discos/#",DISCOS_ID);
    }

    // database declarations
    private SQLiteDatabase discos_database;
    static final String DATABASE_NAME = "dbdiscos.db";
    static final String TABLE_NAME = "discos";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE =
            "CREATE TABLE "+TABLE_NAME+" (_id integer primary key autoincrement, title text, year integer);";
    static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    public String getType(Uri uri){
        switch (uriMatcher.match(uri)){
            //Todos los discos
            case DISCOS:
                return "vnd.android.cursor.dir/vnd.com.example.jmalberola.customcontentprovider";
            //Un disco particular
            case DISCOS_ID:
                return "vnd.android.cursor.item/vnd.com.example.jmalberola.customcontentprovider";
            default:
                throw new IllegalArgumentException("Unsupported URI "+uri);
        }
    }

    public boolean onCreate(){

        dbHelper = new MyDbHelper(getContext());

        discos_database = dbHelper.getWritableDatabase();

        if(discos_database == null)
            return false;
        else
            return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        //La tabla sobre la que hacemos la consulta
        queryBuilder.setTables(TABLE_NAME);

        switch (uriMatcher.match(uri)){
            //Todos los registros
            case DISCOS:
                break;
            //El registro que contiene el _id
            case DISCOS_ID:
                queryBuilder.appendWhere(_ID + " = " + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI "+uri);
        }

        return queryBuilder.query(discos_database, null, null, null, null,null,null);

    }

    public Uri insert(Uri uri, ContentValues values){
        /* to implement */
        Uri _uri = uri;
        return _uri;
    }

    public int update (Uri uri, ContentValues values, String selection, String[] selectionArgs){
        /* to implement */
        return 1;
    }

    public int delete (Uri uri, String selection, String[] selectionArgs){
        /* to implement */
        return 1;
    }

    // Clase que crea y gestiona la BBDD del provider
    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(MyDbHelper.class.getName(),
                    "Upgrading database from version "+oldVersion+
                    " to "+newVersion+
                    ". Old data will be destroyed");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }

    }


}
