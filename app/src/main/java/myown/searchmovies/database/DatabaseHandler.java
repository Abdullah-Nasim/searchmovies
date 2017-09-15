package myown.searchmovies.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import myown.searchmovies.database.model.MovieDetails;

/**
 * Created by Netaq on 9/15/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "movieDetailsManager";

    // Movie Details table name
    private static final String TABLE_MOVIE_DETAILS = "movieDetails";

    // Movie Details Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_TAG_LINE= "tag_line";
    private static final String KEY_STATUS= "status";
    private static final String KEY_BUDGET= "budget";
    private static final String KEY_GENRES= "genres";
    private static final String KEY_HOMEPAGE= "homepage";
    private static final String KEY_OVERVIEW= "overview";
    private static final String KEY_POPULARITY= "popularity";
    private static final String KEY_POSTER_PATH= "poster_path";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Create table query
        String CREATE_MOVIE_DETAILS_TABLE = "CREATE TABLE " + TABLE_MOVIE_DETAILS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_TAG_LINE + " TEXT," + KEY_STATUS + " TEXT," + KEY_BUDGET
                + " INTEGER," + KEY_GENRES + " TEXT," + KEY_HOMEPAGE + " TEXT,"
                + KEY_OVERVIEW + " TEXT," + KEY_POPULARITY + " TEXT,"
                + KEY_POSTER_PATH + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_MOVIE_DETAILS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE_DETAILS);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    /**
     * (Create, Read) Operations
     */

    // Adding new movie
    public void addMovie(MovieDetails movie) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, movie.getId());
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_TAG_LINE, movie.getTagline());
        values.put(KEY_STATUS, movie.getStatus());
        values.put(KEY_BUDGET, movie.getBudget());
        values.put(KEY_GENRES, "");
        values.put(KEY_HOMEPAGE, movie.getHomepage());
        values.put(KEY_OVERVIEW, movie.getOverview());
        values.put(KEY_POPULARITY, movie.getPopularity());
        values.put(KEY_POSTER_PATH, movie.getPoster_path());

        // Inserting Row
        db.insert(TABLE_MOVIE_DETAILS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public MovieDetails getMovie(Integer id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MOVIE_DETAILS, new String[] { KEY_ID, KEY_TITLE, KEY_TAG_LINE, KEY_STATUS, KEY_BUDGET, KEY_GENRES, KEY_HOMEPAGE, KEY_OVERVIEW, KEY_POPULARITY, KEY_POSTER_PATH }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        try {
            //Fetching the movie record from the database
            MovieDetails movie = new MovieDetails(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9));
            return movie; //Returning the movie object

        }catch (Exception ex){
            return null;
        }
    }
}
