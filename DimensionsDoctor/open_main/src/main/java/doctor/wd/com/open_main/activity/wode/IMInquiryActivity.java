package doctor.wd.com.open_main.activity.wode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import doctor.wd.com.open_main.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wd.doctor.common.core.BaseActivity;
import com.wd.doctor.common.utils.Constant;

@Route(path = Constant.ACTIVITY_URL_IMINQUIRY)
public class IMInquiryActivity extends AppCompatActivity {

    private ImageView picture;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iminquiry);
        picture = findViewById(R.id.picture_im);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,2);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2){
            if (data != null){
                Uri uri = data.getData();
                picture.setImageURI(uri);
            }
        }
    }
}
