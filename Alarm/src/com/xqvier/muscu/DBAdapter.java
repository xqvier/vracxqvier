/*
 * DBAdapter.java	26 avr. 2011
 * xMourgues 
 */
package com.xqvier.muscu;

import java.sql.Date;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Classe utilitaire pour les actions sur la base de donnée
 * 
 * @author xMourgues
 * @version
 */
public class DBAdapter {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_RECOVERY = "recovery";
    public static final String KEY_DELAY = "delay";
    public static final String KEY_COUNT = "count";

    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "musculation";
    private static final String DATABASE_TABLE = "exercise";
    private static final int DATABASE_VERSION = 6;

    private static final String DATABASE_CREATE = "create table "
	    + DATABASE_TABLE + " (" + KEY_ROWID
	    + " integer primary key autoincrement, " + KEY_NAME + " text, "
	    + KEY_DATE + " text," + KEY_TIME + " integer," + KEY_RECOVERY
	    + " integer, " + KEY_DELAY + " integer, " + KEY_COUNT
	    + " integer);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "
	    + DATABASE_TABLE;

    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    /**
     * Constructeur par défaut
     * 
     * @param context
     *            le context actuelle de l'utilisation de la base de donnée
     */
    public DBAdapter(Context context) {
	DBHelper = new DataBaseHelper(context);
    }

    public DBAdapter open() throws SQLException {
	db = DBHelper.getWritableDatabase();
	return this;
    }

    public void close() {
	DBHelper.close();
    }

    public long insertExercise(Exercise exo) {
	ContentValues cv = new ContentValues();
	Date sqlDate = new Date(exo.getDate().getTime());
	System.out.println(sqlDate.toString());
	cv.put(KEY_DATE, sqlDate.toString());
	cv.put(KEY_NAME, exo.getName());
	cv.put(KEY_TIME, exo.getTime());
	cv.put(KEY_RECOVERY, exo.getRecovery());
	cv.put(KEY_DELAY, exo.getDelay());
	cv.put(KEY_COUNT, exo.getCount());
	return db.insert(DATABASE_TABLE, null, cv);
    }

    /**
     * TODO Comment method
     * 
     * @param exerciseId
     * @return 
     */
    public int deleteExercise(int exerciseId) {
	return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + exerciseId, null);
    }

    public ArrayList<Exercise> selectAllExercise() {
	Cursor mCursor = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
	        KEY_NAME, KEY_DATE, KEY_TIME, KEY_RECOVERY, KEY_DELAY,
	        KEY_COUNT }, null, null, null, null, null);
	if (mCursor == null) {
	    return null;
	}
	ArrayList<Exercise> exos = new ArrayList<Exercise>();
	Exercise exo;
	for (int i = 0; i < mCursor.getCount(); i++) {
	    mCursor.moveToPosition(i);
	    exo = new Exercise(
		    mCursor.getInt(mCursor.getColumnIndex(KEY_ROWID)),
		    mCursor.getString(mCursor.getColumnIndex(KEY_NAME)),
		    Date.valueOf(mCursor.getString(mCursor
		            .getColumnIndex(KEY_DATE))), mCursor.getInt(mCursor
		            .getColumnIndex(KEY_TIME)), mCursor.getInt(mCursor
		            .getColumnIndex(KEY_RECOVERY)),
		    mCursor.getInt(mCursor.getColumnIndex(KEY_DELAY)),
		    mCursor.getInt(mCursor.getColumnIndex(KEY_COUNT)));
	    exos.add(exo);
	}
	mCursor.close();
	return exos;
    }

    /**
     * Recherche dans la base de données tous les nom d'exercice existant
     * 
     * @return les noms d'exercice
     */
    public String[] selectAllExoNames() {
	Cursor mCursor = db.query(true, DATABASE_TABLE,
	        new String[] { KEY_NAME }, null, null, null, null, null, null);
	if (mCursor == null) {
	    return null;
	}
	String names[] = new String[mCursor.getCount()];
	for (int i = 0; i < mCursor.getCount(); i++) {
	    mCursor.moveToPosition(i);
	    names[i] = mCursor.getString(mCursor.getColumnIndex(KEY_NAME));
	}
	mCursor.close();
	return names;
    }

    /**
     * recherche le dernier exercice avec le nom passé en parametre
     * 
     * @param name
     *            le nom de l'exercice
     * @return le dernier exercice a s'appellé name
     */
    public Exercise selectLastExercise(String name) {
	Exercise exo;
	Cursor mCursor = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
	        KEY_NAME, KEY_DATE, KEY_TIME, KEY_RECOVERY, KEY_DELAY,
	        KEY_COUNT }, KEY_NAME + "='" + name + "'", null, null, null,
	        null);
	if (mCursor == null) {
	    return null;
	}
	if (!mCursor.moveToFirst()) {
	    return null;
	}
	exo = new Exercise(
	        mCursor.getInt(mCursor.getColumnIndex(KEY_ROWID)),
	        mCursor.getString(mCursor.getColumnIndex(KEY_NAME)),
	        Date.valueOf(mCursor.getString(mCursor.getColumnIndex(KEY_DATE))),
	        mCursor.getInt(mCursor.getColumnIndex(KEY_TIME)), mCursor
	                .getInt(mCursor.getColumnIndex(KEY_RECOVERY)), mCursor
	                .getInt(mCursor.getColumnIndex(KEY_DELAY)), mCursor
	                .getInt(mCursor.getColumnIndex(KEY_COUNT)));
	mCursor.close();
	return exo;
    }

    /**
     * Renvoie tous les exercices de la base de données ayant pour nom celui
     * passé en parametre
     * 
     * @param text
     *            le nom des exercice recherché
     * @return une liste d'exercice portant le nom demandé
     */
    public ArrayList<Exercise> selectExercise(String name) {
	Cursor mCursor = db.query(DATABASE_TABLE, new String[] { KEY_ROWID,
	        KEY_NAME, KEY_DATE, KEY_TIME, KEY_RECOVERY, KEY_DELAY,
	        KEY_COUNT }, KEY_NAME + "='" + name + "'", null, null, null,
	        null);
	if (mCursor == null || mCursor.getCount()==0) {
	    return null;
	}
	ArrayList<Exercise> exos = new ArrayList<Exercise>();
	Exercise exo;
	for (int i = 0; i < mCursor.getCount(); i++) {
	    mCursor.moveToPosition(i);
	    exo = new Exercise(
		    mCursor.getInt(mCursor.getColumnIndex(KEY_ROWID)),
		    mCursor.getString(mCursor.getColumnIndex(KEY_NAME)),
		    Date.valueOf(mCursor.getString(mCursor
		            .getColumnIndex(KEY_DATE))), mCursor.getInt(mCursor
		            .getColumnIndex(KEY_TIME)), mCursor.getInt(mCursor
		            .getColumnIndex(KEY_RECOVERY)),
		    mCursor.getInt(mCursor.getColumnIndex(KEY_DELAY)),
		    mCursor.getInt(mCursor.getColumnIndex(KEY_COUNT)));
	    exos.add(exo);
	}
	mCursor.close();
	return exos;
    }

    private static class DataBaseHelper extends SQLiteOpenHelper {

	/**
	 * Constructeur par défaut
	 * 
	 * @param context
	 */
	public DataBaseHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database
	 * .sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
	    Log.i(TAG, DATABASE_CREATE);
	    db.execSQL(DATABASE_CREATE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database
	 * .sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
		    + newVersion + ", which will destroy all old data");
	    db.execSQL(DATABASE_DROP);
	    onCreate(db);

	}
    }
}
