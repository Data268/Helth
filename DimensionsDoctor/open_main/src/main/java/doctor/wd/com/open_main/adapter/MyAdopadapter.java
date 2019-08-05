package doctor.wd.com.open_main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.doctor.common.bean.MyAdoptedBean;
import com.wd.doctor.common.utils.DateUtils;

import java.text.ParseException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;

public class MyAdopadapter extends RecyclerView.Adapter<MyAdopadapter.VHH> {
    Context context;
    List<MyAdoptedBean> data;
    public MyAdopadapter(Context context, List<MyAdoptedBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VHH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_agread_item,null);
        return new VHH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHH holder, int position) {
        holder.userHand.setImageURI(data.get(position).getReleaseUserHeadPic());
        holder.userName.setText(data.get(position).getReleaseUserNickName());
        holder.tit.setText(data.get(position).getTitle());
        try {
            holder.userTime.setText(DateUtils.dateTransformer(data.get(position).getAdoptTime(),DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.userIll.setText(data.get(position).getDisease());
        holder.mySuggest.setText(data.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VHH extends RecyclerView.ViewHolder {

        private final SimpleDraweeView userHand;
        private final TextView userName;
        private final TextView tit;
        private final TextView userIll;
        private final TextView userTime;
        private final TextView mySuggest;

        public VHH(@NonNull View itemView) {
            super(itemView);
            userHand = itemView.findViewById(R.id.user_hand);
            userName = itemView.findViewById(R.id.user_name);
            tit = itemView.findViewById(R.id.usr_tit);
            userIll = itemView.findViewById(R.id.user_ill);
            userTime = itemView.findViewById(R.id.user_time);
            mySuggest = itemView.findViewById(R.id.my_suggest);
        }
    }
}
