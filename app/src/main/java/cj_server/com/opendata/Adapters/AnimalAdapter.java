package cj_server.com.opendata.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import cj_server.com.opendata.Pojo.Animal;
import cj_server.com.opendata.R;

/**
 * Created by cj-sever on 2/6/17.
 */
public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.mViewHolder> {
    private ArrayList<Animal>mList;
    private Context context;

    public AnimalAdapter(ArrayList<Animal> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.animal_custom_layout,parent,false);
        return  new  mViewHolder(v);
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        Animal animal = mList.get(position);
        holder.m_districtName.setText(animal.getDistrict());
        holder.m_population.setText(animal.getProvince());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder{
        public TextView m_districtName;
        public TextView m_population;

        public mViewHolder(View itemView) {
            super(itemView);
            m_districtName  = (TextView) itemView.findViewById(R.id.tv_districtName);
            m_population = (TextView)itemView.findViewById(R.id.tv_population);
        }
    }
}
