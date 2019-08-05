package doctor.wd.com.open_main.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.doctor.common.bean.InquiryBean;
import com.wd.doctor.common.utils.Constant;
import com.wd.doctor.common.utils.DateUtils;

import java.text.ParseException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.activity.wode.IMInquiryActivity;
import doctor.wd.com.open_main.activity.wode.UserInfoActivity;

public class MyInquiryAdapter extends RecyclerView.Adapter<MyInquiryAdapter.VGG> {
    Context context;
    List<InquiryBean> data;

    public MyInquiryAdapter(Context context, List<InquiryBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VGG onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_inquiry, null);
        return new VGG(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VGG holder, final int position) {


        holder.imgin.setImageURI(Uri.parse(data.get(position).getUserHeadPic()));
        holder.name.setText(data.get(position).getNickName());
        holder.content.setText(data.get(position).getLastContent());
        try {
            holder.timei.setText(DateUtils.dateTransformer(data.get(position).getInquiryTime(), DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.imgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserInfoActivity.class);
                int userId = data.get(position).getUserId();
                intent.putExtra("userIds", userId);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IMInquiryActivity.class);
                int userId = data.get(position).getUserId();
                intent.putExtra("userIds", userId);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class VGG extends RecyclerView.ViewHolder {

        private final SimpleDraweeView imgin;
        private final TextView name;
        private final TextView content;
        private final TextView timei;

        public VGG(@NonNull View itemView) {
            super(itemView);

            imgin = itemView.findViewById(R.id.img_in);
            name = itemView.findViewById(R.id.user_name);
            content = itemView.findViewById(R.id.content_in);
            timei = itemView.findViewById(R.id.time_in);
        }
    }
}
