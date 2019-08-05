package doctor.wd.com.open_main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.doctor.common.bean.InquiryNotBean;
import com.wd.doctor.common.utils.DateUtils;

import java.text.ParseException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;

public class MyAskNotAdapter extends RecyclerView.Adapter<MyAskNotAdapter.VHH> {
    Context context;
    List<InquiryNotBean> data;

    public MyAskNotAdapter(Context context, List<InquiryNotBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VHH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_asknot,null);
        return new VHH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHH holder, int position) {
        holder.askconten.setText(data.get(position).getContent());
        try {
            holder.asktime.setText(DateUtils.dateTransformer(data.get(position).getCreateTime(),DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VHH extends RecyclerView.ViewHolder {

        private final TextView askconten;
        private final TextView asktime;
        private final TextView chakan;

        public VHH(@NonNull View itemView) {
            super(itemView);
            askconten = itemView.findViewById(R.id.ask_content);
            asktime = itemView.findViewById(R.id.ask_time);
            chakan = itemView.findViewById(R.id.chakan);

        }
    }
}
