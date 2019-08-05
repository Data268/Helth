package doctor.wd.com.open_main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.doctor.common.bean.SearchcircleBean;
import com.wd.doctor.common.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.activity.wode.SearchActivity;

public class MySearchAdapter extends XRecyclerView.Adapter<MySearchAdapter.VHH> {
    private final List<SearchcircleBean> list;
    Context context;

    public MySearchAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public VHH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = View.inflate(context, R.layout.layout_searchitem,null);
        return new VHH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHH holder, int position) {
        holder.titij.setText(list.get(position).getTitle());
        holder.contentList.setText(list.get(position).getDetail());
        holder.curryCount.setText(list.get(position).getAmount()+"1");
        try {
            holder.nian.setText(DateUtils.dateTransformer(list.get(position).getReleaseTime(),DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<SearchcircleBean> data) {
        list.addAll(data);
    }


    public class VHH extends XRecyclerView.ViewHolder {

        private final TextView titij;
        private final LinearLayout hlin;
        private final TextView curryCount;
        private final TextView contentList;
        private final TextView nian;
        private final TextView shi;

        public VHH(@NonNull View itemView) {
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
