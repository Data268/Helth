package doctor.wd.com.open_main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.doctor.common.bean.HistroyBean;
import com.wd.doctor.common.utils.DateUtils;

import java.text.ParseException;
import java.util.List;

import androidx.annotation.NonNull;
import doctor.wd.com.open_main.R;

public class MyHistroyAdapter extends XRecyclerView.Adapter<MyHistroyAdapter.GHH> {
    Context context;
    List<HistroyBean> data;
    public MyHistroyAdapter(Context context, List<HistroyBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GHH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_histroy,null);
        return new GHH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GHH holder, int position) {
        Glide.with(context).load(data.get(position).getUserHeadPic()).into(holder.head);
        holder.name.setText(data.get(position).getNickName());
        try {
            holder.time.setText(DateUtils.dateTransformer(data.get(position).getInquiryTime(),DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.pingl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class GHH extends XRecyclerView.ViewHolder {
        private final SimpleDraweeView head;
        private TextView name;
        private TextView time;
        private Button look;
        private Button pingl;

        public GHH(@NonNull View itemView) {
            super(itemView);

            head = itemView.findViewById(R.id.head_hi);
            name = itemView.findViewById(R.id.name_hi);
            time = itemView.findViewById(R.id.time_hi);
            look = itemView.findViewById(R.id.look_hi);
            pingl = itemView.findViewById(R.id.pingl_hi);

        }
    }
}
