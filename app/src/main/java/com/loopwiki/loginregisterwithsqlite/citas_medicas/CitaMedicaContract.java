package com.loopwiki.loginregisterwithsqlite.citas_medicas;

import android.provider.BaseColumns;

public class CitaMedicaContract {
    public static final class CitaMedicaEntry implements BaseColumns{
        public static final String TABLE_NAME="citamedica";
        public static final String COLUMN_DOCTOR="doctor";
        public static final String COLUMN_CLINICA="clinica";
        public static final String COLUMN_TIMESTAMP="timestamp";
    }
}
