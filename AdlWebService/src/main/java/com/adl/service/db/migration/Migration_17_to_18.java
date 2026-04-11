package com.adl.service.db.migration;

import android.database.Cursor;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.HashSet;
import java.util.Set;

public class Migration_17_to_18 extends Migration {
    public Migration_17_to_18() {
        super(17, 18);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        Cursor cursor = database.query("PRAGMA table_info('_student_entity')");
        boolean hasStudentNum = false;
        boolean hasUserNamePy = false;
        boolean hasUserNamePyShort = false;
        try {
            int nameIndex = cursor.getColumnIndex("name");
            while (cursor.moveToNext()) {
                String name = cursor.getString(nameIndex);
                if ("_student_num".equals(name)) hasStudentNum = true;
                if ("_user_name_py".equals(name)) hasUserNamePy = true;
                if ("_user_name_py_short".equals(name)) hasUserNamePyShort = true;
            }
        } finally {
            cursor.close();
        }
        if (!hasStudentNum) {
            try {
                database.execSQL("ALTER TABLE _student_entity ADD COLUMN _student_num TEXT");
            } catch (Exception ignored) {
                Log.e("AdlService", "migrate 18 error add _student_num");
            }
        }
        if (!hasUserNamePy) {
            try {
                database.execSQL("ALTER TABLE _student_entity ADD COLUMN _user_name_py TEXT DEFAULT ''");
            } catch (Exception ignored) {
                Log.e("AdlService", "migrate 18 error add _user_name_py");
            }
        }
        if (!hasUserNamePyShort) {
            try {
                database.execSQL("ALTER TABLE _student_entity ADD COLUMN _user_name_py_short TEXT DEFAULT ''");
            } catch (Exception ignored) {
                Log.e("AdlService", "migrate 18 error add _user_name_py_short");
            }
        }

        boolean hasStatus = false;
        Cursor cursor1 = database.query("PRAGMA table_info('_sport_plan')");
        try {
            int nameIndex = cursor1.getColumnIndex("name");
            while (cursor1.moveToNext()) {
                String name = cursor1.getString(nameIndex);
                if ("_status".equals(name)) hasStatus = true;
            }
        } finally {
            cursor1.close();
        }
        if (!hasStatus) {
            try {
                database.execSQL("ALTER TABLE _sport_plan ADD COLUMN _status TEXT");
            } catch (Exception ignored) {
                Log.e("AdlService", "migrate 18 error add _status");
            }
        }

        boolean hasStudentNumInSportPlanStudent = false;
        Cursor cursor2 = database.query("PRAGMA table_info('_sport_plan_student_1')");
        try {
            int nameIndex = cursor2.getColumnIndex("name");
            while (cursor2.moveToNext()) {
                String name = cursor2.getString(nameIndex);
                if ("_student_num".equals(name)) hasStudentNumInSportPlanStudent = true;
            }
        } finally {
            cursor2.close();
        }
        if (!hasStudentNumInSportPlanStudent) {
            try {
                database.execSQL("ALTER TABLE _sport_plan_student_1 ADD COLUMN _student_num TEXT");
            } catch (Exception ignored) {
                Log.e("AdlService", "migrate 18 error add _student_num in _sport_plan_student_1");
            }
        }

        boolean hasAppCodesInSportScene = false;
        Cursor cursor3 = database.query("PRAGMA table_info('_scene_sport')");
        try {
            int nameIndex = cursor3.getColumnIndex("name");
            while (cursor3.moveToNext()) {
                String name = cursor3.getString(nameIndex);
                if ("_app_codes".equals(name)) hasAppCodesInSportScene = true;
            }
        } finally {
            cursor3.close();
        }
        if (!hasAppCodesInSportScene) {
            try {
                database.execSQL("ALTER TABLE _scene_sport ADD COLUMN _app_codes TEXT");
            } catch (Exception ignored) {
                Log.e("AdlService", "migrate 18 error add _app_codes in _scene_sport");
            }
        }

        Set<String> recordCols = new HashSet<>();
        Cursor rc = database.query("PRAGMA table_info('_sport_record_local')");
        try {
            int nameIndex = rc.getColumnIndex("name");
            while (rc.moveToNext()) {
                recordCols.add(rc.getString(nameIndex));
            }
        } finally {
            rc.close();
        }
        addIfMissing(database, recordCols, "_student_name_py", "ALTER TABLE _sport_record_local ADD COLUMN _student_name_py TEXT");
        addIfMissing(database, recordCols, "_student_name_py_short", "ALTER TABLE _sport_record_local ADD COLUMN _student_name_py_short TEXT");
        addIfMissing(database, recordCols, "_report_code", "ALTER TABLE _sport_record_local ADD COLUMN _report_code TEXT");
        addIfMissing(database, recordCols, "_sport_groupId", "ALTER TABLE _sport_record_local ADD COLUMN _sport_groupId TEXT");
        addIfMissing(database, recordCols, "_sport_groupName", "ALTER TABLE _sport_record_local ADD COLUMN _sport_groupName TEXT");
        addIfMissing(database, recordCols, "_status", "ALTER TABLE _sport_record_local ADD COLUMN _status INTEGER NOT NULL DEFAULT 0");
        addIfMissing(database, recordCols, "_sex", "ALTER TABLE _sport_record_local ADD COLUMN _sex TEXT");
        addIfMissing(database, recordCols, "_orgId", "ALTER TABLE _sport_record_local ADD COLUMN _orgId TEXT");
        addIfMissing(database, recordCols, "_orgName", "ALTER TABLE _sport_record_local ADD COLUMN _orgName TEXT");
        addIfMissing(database, recordCols, "_sportItemName", "ALTER TABLE _sport_record_local ADD COLUMN _sportItemName TEXT");
        addIfMissing(database, recordCols, "_planUid", "ALTER TABLE _sport_record_local ADD COLUMN _planUid TEXT");
        addIfMissing(database, recordCols, "_projectUid", "ALTER TABLE _sport_record_local ADD COLUMN _projectUid TEXT");
        addIfMissing(database, recordCols, "_uploadStatus", "ALTER TABLE _sport_record_local ADD COLUMN _uploadStatus INTEGER NOT NULL DEFAULT 0");
        addIfMissing(database, recordCols, "_sportSort", "ALTER TABLE _sport_record_local ADD COLUMN _sportSort INTEGER NOT NULL DEFAULT 0");
        addIfMissing(database, recordCols, "_spendTime", "ALTER TABLE _sport_record_local ADD COLUMN _spendTime TEXT DEFAULT ''");
        addIfMissing(database, recordCols, "_number", "ALTER TABLE _sport_record_local ADD COLUMN _number INTEGER NOT NULL DEFAULT 0");
        addIfMissing(database, recordCols, "_circle", "ALTER TABLE _sport_record_local ADD COLUMN _circle INTEGER NOT NULL DEFAULT 0");
        addIfMissing(database, recordCols, "_create_time", "ALTER TABLE _sport_record_local ADD COLUMN _create_time TEXT DEFAULT '0'");
        addIfMissing(database, recordCols, "_update_time", "ALTER TABLE _sport_record_local ADD COLUMN _update_time TEXT DEFAULT '0'");

        Set<String> uploadCols = new HashSet<>();
        Cursor uc = database.query("PRAGMA table_info('_sport_record_upload')");
        try {
            int nameIndex = uc.getColumnIndex("name");
            while (uc.moveToNext()) {
                uploadCols.add(uc.getString(nameIndex));
            }
        } finally {
            uc.close();
        }
        addIfMissing(database, uploadCols, "_number", "ALTER TABLE _sport_record_upload ADD COLUMN _number INTEGER NOT NULL DEFAULT 0");
        addIfMissing(database, uploadCols, "_circle", "ALTER TABLE _sport_record_upload ADD COLUMN _circle INTEGER NOT NULL DEFAULT 0");
        addIfMissing(database, uploadCols, "_create_time", "ALTER TABLE _sport_record_upload ADD COLUMN _create_time TEXT DEFAULT '0'");
        addIfMissing(database, uploadCols, "_update_time", "ALTER TABLE _sport_record_upload ADD COLUMN _update_time TEXT DEFAULT '0'");
    }

    private void addIfMissing(SupportSQLiteDatabase database, Set<String> recordCols, String name, String ddl) {
        if (!recordCols.contains(name)) {
            try {
                database.execSQL(ddl);
            } catch (Exception ignored) {
                Log.e("AdlService", "migrate 18 error add " + name);
            }
        }
    }
}
