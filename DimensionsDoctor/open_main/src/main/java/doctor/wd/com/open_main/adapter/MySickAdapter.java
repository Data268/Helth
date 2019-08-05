package doctor.wd.com.open_main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.doctor.common.bean.FindSickBean;
import com.wd.doctor.common.utils.Constant;
import com.wd.doctor.common.utils.DateUtils;

import java.text.ParseException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.activity.wode.CircleInfoActivity;

public class MySickAdapter extends XRecyclerView.Adapter<MySickAdapter.VSS> {
    Context context;
    List<FindSickBean> data;
    public MySickAdapter(Context context, List<FindSickBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VSS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_list_item,null);
        return new VSS(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VSS holder, final int position) {
        holder.titij.setText(data.get(position).getTitle());
        holder.contentList.setText(data.get(position).getDetail());
        holder.curryCount.setText(data.get(position).getAmount()+"1");
        try {
            holder.nian.setText(DateUtils.dateTransformer(data.get(position).getReleaseTime(),DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,CircleInfoActivity.class);
                int sickCircleId = data.get(position).getSickCircleId();
                intent.putExtra("sickCircleIds",sickCircleId);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                 context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VSS extends XRecyclerView.ViewHolder {

        private final TextView titij;
        private final LinearLayout hlin;
        private final TextView curryCount;
        private final TextView contentList;
        private final TextView nian;
        private final TextView shi;

        public VSS(@NonNull View itemView) {
            super(itemView);
            titij = itemView.findViewById(R.id.title_t);
            hlin = itemView.findViewById(R.id.h_lin);
            curryCount = itemView.findViewById(R.id.curry_count);
            contentList = itemView.findViewById(R.id.list_content);
            nian = itemView.findViewById(R.id.nai_n);
            shi = itemView.findViewById(R.id.shi_j);
        }
    }
}
