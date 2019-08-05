package doctor.wd.com.open_main.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.doctor.common.bean.Department;
import com.wd.doctor.common.utils.Constant;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import doctor.wd.com.open_main.R;
import doctor.wd.com.open_main.activity.AskActivity;

public class MyDepartmentAdapter extends RecyclerView.Adapter<MyDepartmentAdapter.VDD> {
    Context context;
    List<Department> data;

    public MyDepartmentAdapter(Context context, List<Department> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VDD onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_department, null);
        return new VDD(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VDD holder, final int i) {
        holder.titi.setText(data.get(i).getDepartmentName());
        holder.titi.setTextColor(data.get(i).getTextcolor());
        holder.titi.setTag(data.get(i));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object tag = v.getTag();
                onClickListener.onClick(data.get(i));
                int ids = data.get(i).getId();
                String departmentName = data.get(i).getDepartmentName();
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setTextcolor(0xff333333);
                }
                data.get(i).setTextcolor(0xff3087ea);
                notifyDataSetChanged();
////                EventBus.getDefault().postSticky(ids);
//                Intent intent = new Intent(Constant.ACTIVITY_URL_ASK);
//                intent.putExtra("idss",ids);

                Log.i("aaaa", "onClick: " + ids);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VDD extends RecyclerView.ViewHolder {

        private final TextView titi;

        public VDD(@NonNull View itemView) {
            super(itemView);
            titi = itemView.findViewById(R.id.titl);
        }
    }

    private OnClickListener onClickListener;

    public void setOnClickListr(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(Department department);
    }
}
