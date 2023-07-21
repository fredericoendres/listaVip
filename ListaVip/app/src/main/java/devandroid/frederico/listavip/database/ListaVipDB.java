package devandroid.frederico.listavip.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ListaVipDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "listavip.db";
    public static final int DB_VERSION = 1;

    Cursor cursor;
    SQLiteDatabase db;


    public ListaVipDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlListaVip
                = "CREATE TABLE Lista (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "primeiroNome TEXT, " +
                "sobrenome TEXT, " +
                "telefone TEXT, " +
                "generoInformado TEXT)";

        sqLiteDatabase.execSQL(sqlListaVip);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public void salvarObjeto(String tabela, ContentValues dados) {
        db.insert(tabela, null, dados);
    }
}
