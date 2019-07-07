package alrefa.android.com.homefit.Data.DB;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import javax.inject.Inject;

import alrefa.android.com.homefit.DI.Qualifier.ApplicationContext;
import alrefa.android.com.homefit.Data.Network.Model.DaoMaster;
import alrefa.android.com.homefit.Utils.AppConstants;

public class DbOpenHelper extends DaoMaster.OpenHelper {

    @Inject
    public DbOpenHelper(@ApplicationContext Context context) {
        super(context, AppConstants.DB_NAME);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

    }
}
