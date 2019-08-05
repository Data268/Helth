package doctor.wd.com.open_main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.doctor.common.bean.SystemmessageBean;
import com.wd.doctor.common.utils.DateUtils;

import java.text.ParseException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;

public class MySysAdapter extends RecyclerView.Adapter<MySysAdapter.VKK> {
    Context context;
    List<SystemmessageBean> data;
    public MySysAdapter(Context context, List<SystemmessageBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VKK onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = View.inflate(context, R.layout.layout_sys,null);
        return new VKK(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VKK holder, int position) {
        holder.conten.setText(data.get(position).getContent());
        try {
            holder.ri_q.setText(DateUtils.dateTransformer(data.get(position).getCreateTime(),DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VKK extends RecyclerView.ViewHolder {

        private final TextView conten,ri_q,tim;

        public VKK(@NonNull View itemView) {
            super(itemView);
            conten = itemView.findViewById(R.id.t_content);
            ri_q = itemView.findViewById(R.id.ri_q);
            tim = itemView.findViewById(R.id.tim);

        }
    }
}
