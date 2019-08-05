package com.wd.doctor.open_login.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.bean.LoginBean;
import com.wd.doctor.common.bean.be.RetrofitUtils;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.core.Cinterface;
import com.wd.doctor.common.core.db.DaoMaster;
import com.wd.doctor.common.core.db.LoginBeanDao;
import com.wd.doctor.common.core.exception.ApiException;
import com.wd.doctor.common.utils.Constant;
import com.wd.doctor.common.utils.RealPathFromUriUtils;
import com.wd.doctor.common.utils.UIUtils;
import com.wd.doctor.common.utils.http.ApiRequest;
import com.wd.doctor.common.utils.http.NetWorkManager;
import com.wd.doctor.open_login.R;
import com.wd.doctor.open_login.presenter.UploadIaPresenter;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(path = Constant.ACTIVITY_URL_IMAG)
public class ImageActivity extends BaseActivity {

    private ImageButton camera;
    private String file=Environment.getExternalStorageDirectory()+"/t.png";
    private UploadIaPresenter uploadIaPresenter;
    private List<LoginBean> loginBeans;
    private Long id;
    private String sessionId;

    private AlertDialog alertDialog;

    private TextView xiangce;
    private TextView paizhao;
    String[] permiss={Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
    private File filea;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_image;
    }

    @Override
    protected void initView() {
        camera = findViewById(R.id.camera_btn);

        LoginBeanDao loginBeanDao = DaoMaster.newDevSession(getBaseContext(), LoginBeanDao.TABLENAME).getLoginBeanDao();
        loginBeans = loginBeanDao.loadAll();
        id = loginBeans.get(0).getId();
        sessionId = loginBeans.get(0).getSessionId();
        Log.i("aaa", "initView: "+id+"----"+sessionId);
        uploadIaPresenter = new UploadIaPresenter(new imageCall());


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Portss();
//                alertDialog.show();
            }
        });

    }



    public void Portss() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();//创建对话框
        dialog.setTitle("上传头像");//设置对话框标题
        //分别设置三个button
        dialog.setButton(DialogInterface.BUTTON_POSITIVE,"相机", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(file)));
                startActivityForResult(intent,100);
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "相册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                       Intent intent1=new Intent(Intent.ACTION_PICK);
                       intent1.setType("image/*");
                       startActivityForResult(intent1,200);
            }
        });
        dialog.show();//显示对话框



    }

    @Override
    protected void destoryData() {

    }

    private class imageCall implements Cinterface {


        @Override
        public void success(Object data, Object... args) {
            UIUtils.showToastSafe("上传成功!!!!!!!!!");

        }

        @Override
        public void fail(ApiException data, Object... args) {
            UIUtils.showToastSafe(data.getCode() + "===" + data.getDisplayMessage());
            Log.e("ssssssss",""+data.getDisplayMessage()+"++++++++++"+data.getCode());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
//    获取相机传过来的照片
            Uri uri=Uri.fromFile(new File(file));
            if (uri!=null) {
                File filea = new File(file);
                if (!filea.exists()) {
                    filea.mkdirs();
                }

                uploadIaPresenter.reqeust(id+"", sessionId, filea);

            }
        }else if (requestCode==200&&resultCode==RESULT_OK){
        //    获取相册传过来的照片
            if (data.getData()!=null) {
                String realPathFromUri = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
                filea = new File(realPathFromUri);
                if (!filea.exists()){
                    filea.mkdirs();
                }
                uploadIaPresenter.reqeust(id+"",sessionId, filea);
               /* Bitmap bitmap=BitmapFactory.decodeFile(realPathFromUri);
                saveBitmapFile(bitmap);*/


                Log.e("eee",realPathFromUri+"++++"+id+"55555555"+"333333333"+sessionId+"1111111111"+ filea);
            }
        }
    }

   /* public void saveBitmapFile(Bitmap bitmap) {

        File file = new File("/storage/s.jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            Log.e("ssssssssss",file+"");

            bos.flush();
            bos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
 }

