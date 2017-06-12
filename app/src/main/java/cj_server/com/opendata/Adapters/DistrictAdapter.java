package cj_server.com.opendata.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import cj_server.com.opendata.Pojo.Data;
import cj_server.com.opendata.Pojo.Distict;
import cj_server.com.opendata.R;

/**
 * Created by cj-sever on 1/30/17.
 */
public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Data>dataList;
    private DecimalFormat formatter;

    public DistrictAdapter(Context context, ArrayList<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.distict_custom_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        formatter = new DecimalFormat("#,###,###");
        Data data  = dataList.get(position);
        holder.m_districtName.setText(data.getDistrict());
        if(data.getTotal_population()<=0){
            holder.m_population.setText("null");
        }else {
            holder.m_population.setText(formatter.format(data.getTotal_population()));


        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView m_districtName;
        public TextView m_population;

        public ViewHolder(View itemView) {
            super(itemView);

            m_districtName  = (TextView) itemView.findViewById(R.id.tv_districtName);
            m_population = (TextView)itemView.findViewById(R.id.tv_population);

        }
    }
}
