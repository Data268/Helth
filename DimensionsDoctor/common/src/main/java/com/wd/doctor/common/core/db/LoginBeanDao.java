package com.wd.doctor.common.core.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.wd.doctor.common.bean.LoginBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LOGIN_BEAN".
*/
public class LoginBeanDao extends AbstractDao<LoginBean, Void> {

    public static final String TABLENAME = "LOGIN_BEAN";

    /**
     * Properties of entity LoginBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", false, "ID");
        public final static Property DepartmentId = new Property(1, int.class, "departmentId", false, "DEPARTMENT_ID");
        public final static Property DepartmentName = new Property(2, String.class, "departmentName", false, "DEPARTMENT_NAME");
        public final static Property InauguralHospital = new Property(3, String.class, "inauguralHospital", false, "INAUGURAL_HOSPITAL");
        public final static Property JiGuangPwd = new Property(4, String.class, "jiGuangPwd", false, "JI_GUANG_PWD");
        public final static Property JobTitle = new Property(5, String.class, "jobTitle", false, "JOB_TITLE");
        public final static Property Name = new Property(6, String.class, "name", false, "NAME");
        public final static Property ReviewStatus = new Property(7, int.class, "reviewStatus", false, "REVIEW_STATUS");
        public final static Property SessionId = new Property(8, String.class, "sessionId", false, "SESSION_ID");
        public final static Property UserName = new Property(9, String.class, "userName", false, "USER_NAME");
        public final static Property ImagePic = new Property(10, String.class, "imagePic", false, "IMAGE_PIC");
        public final static Property WhetherHaveImagePic = new Property(11, int.class, "whetherHaveImagePic", false, "WHETHER_HAVE_IMAGE_PIC");
        public final static Property Status = new Property(12, int.class, "status", false, "STATUS");
    }


    public LoginBeanDao(DaoConfig config) {
        super(config);
    }
    
    public LoginBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LOGIN_BEAN\" (" + //
                "\"ID\" INTEGER," + // 0: id
                "\"DEPARTMENT_ID\" INTEGER NOT NULL ," + // 1: departmentId
                "\"DEPARTMENT_NAME\" TEXT," + // 2: departmentName
                "\"INAUGURAL_HOSPITAL\" TEXT," + // 3: inauguralHospital
                "\"JI_GUANG_PWD\" TEXT," + // 4: jiGuangPwd
                "\"JOB_TITLE\" TEXT," + // 5: jobTitle
                "\"NAME\" TEXT," + // 6: name
                "\"REVIEW_STATUS\" INTEGER NOT NULL ," + // 7: reviewStatus
                "\"SESSION_ID\" TEXT," + // 8: sessionId
                "\"USER_NAME\" TEXT," + // 9: userName
                "\"IMAGE_PIC\" TEXT," + // 10: imagePic
                "\"WHETHER_HAVE_IMAGE_PIC\" INTEGER NOT NULL ," + // 11: whetherHaveImagePic
                "\"STATUS\" INTEGER NOT NULL );"); // 12: status
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LOGIN_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LoginBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getDepartmentId());
 
        String departmentName = entity.getDepartmentName();
        if (departmentName != null) {
            stmt.bindString(3, departmentName);
        }
 
        String inauguralHospital = entity.getInauguralHospital();
        if (inauguralHospital != null) {
            stmt.bindString(4, inauguralHospital);
        }
 
        String jiGuangPwd = entity.getJiGuangPwd();
        if (jiGuangPwd != null) {
            stmt.bindString(5, jiGuangPwd);
        }
 
        String jobTitle = entity.getJobTitle();
        if (jobTitle != null) {
            stmt.bindString(6, jobTitle);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(7, name);
        }
        stmt.bindLong(8, entity.getReviewStatus());
 
        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(9, sessionId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(10, userName);
        }
 
        String imagePic = entity.getImagePic();
        if (imagePic != null) {
            stmt.bindString(11, imagePic);
        }
        stmt.bindLong(12, entity.getWhetherHaveImagePic());
        stmt.bindLong(13, entity.getStatus());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LoginBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getDepartmentId());
 
        String departmentName = entity.getDepartmentName();
        if (departmentName != null) {
            stmt.bindString(3, departmentName);
        }
 
        String inauguralHospital = entity.getInauguralHospital();
        if (inauguralHospital != null) {
            stmt.bindString(4, inauguralHospital);
        }
 
        String jiGuangPwd = entity.getJiGuangPwd();
        if (jiGuangPwd != null) {
            stmt.bindString(5, jiGuangPwd);
        }
 
        String jobTitle = entity.getJobTitle();
        if (jobTitle != null) {
            stmt.bindString(6, jobTitle);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(7, name);
        }
        stmt.bindLong(8, entity.getReviewStatus());
 
        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(9, sessionId);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(10, userName);
        }
 
        String imagePic = entity.getImagePic();
        if (imagePic != null) {
            stmt.bindString(11, imagePic);
        }
        stmt.bindLong(12, entity.getWhetherHaveImagePic());
        stmt.bindLong(13, entity.getStatus());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public LoginBean readEntity(Cursor cursor, int offset) {
        LoginBean entity = new LoginBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // departmentId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // departmentName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // inauguralHospital
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // jiGuangPwd
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // jobTitle
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // name
            cursor.getInt(offset + 7), // reviewStatus
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // sessionId
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // userName
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // imagePic
            cursor.getInt(offset + 11), // whetherHaveImagePic
            cursor.getInt(offset + 12) // status
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LoginBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDepartmentId(cursor.getInt(offset + 1));
        entity.setDepartmentName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setInauguralHospital(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setJiGuangPwd(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setJobTitle(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setReviewStatus(cursor.getInt(offset + 7));
        entity.setSessionId(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setUserName(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setImagePic(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setWhetherHaveImagePic(cursor.getInt(offset + 11));
        entity.setStatus(cursor.getInt(offset + 12));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(LoginBean entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(LoginBean entity) {
        return null;
    }

    @Override
    public boolean hasKey(LoginBean entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}