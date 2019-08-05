package doctor.wd.com.open_main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.doctor.common.bean.CurrencyNotBean;
import com.wd.doctor.common.utils.DateUtils;

import java.text.ParseException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;

public class MyCurrAdapter extends RecyclerView.Adapter<MyCurrAdapter.VMM> {
    Context context;
    List<CurrencyNotBean> data;
    public MyCurrAdapter(Context context, List<CurrencyNotBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VMM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = View.inflate(context, R.layout.layout_currenecy,null);
        return new VMM(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VMM holder, int position) {
        holder.conte.setText(data.get(position).getContent());
        try {
            holder.tim.setText(DateUtils.dateTransformer(data.get(position).getCreateTime(),DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VMM extends RecyclerView.ViewHolder {

        private final TextView conte;
        private final TextView tim;

        public VMM(@NonNull View itemView) {
            super(itemView);
            conte = itemView.findViewById(R.id.cur_content);
            tim = itemView.findViewById(R.id.cur_time);
        }
    }
}
