package cj_server.com.opendata;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import cj_server.com.opendata.General__Functions.Constants;

public class Analytics extends AppCompatActivity {
    private Toolbar toolbar;
    private String  district;
    private String category;
    private int male;
    private int female;
    private int total;
    private String location;
    private String  county;
    private PieChart mChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra(Constants.DISTRICT));

        }
        male = getIntent().getIntExtra(Constants.NO_OF_MALE,1);
        female = getIntent().getIntExtra(Constants.NO_OF_FEMALE,1);

        Log.d("Male", String.valueOf(male));
        mChart = (PieChart) findViewById(R.id.chart);
//        configure  pie chart
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);



//        enable a hole and confiqure
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleRadius(7);
//        mChart.setTransparentCircleColor(10);
        mChart.setHoleColor(Color.TRANSPARENT);
        mChart.setTransparentCircleRadius(10);

//        enable rotation  ofthe chart by touch
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

//        set a value selected listener
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e == null)
                    return;
                Log.i("VAL SELECTED",
                        "Value: " + e.getY() + ", index: " + h.getX()
                                + ", DataSet index: " + e.getX());
                Toast.makeText(getApplicationContext(),""+e.getY()+"index"+h.getX(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });
        //    add data
        addData();

//        configure lengend
        Legend legend = mChart.getLegend();
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_RIGHT);
        legend.setXEntrySpace(7);
        legend.setYEntrySpace(5);
    }
    private void addData(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(male, "Male"));
        entries.add(new PieEntry(female, "Female"));


//        creating pie dataset
        PieDataSet dataSet = new PieDataSet(entries, "Gender");//
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(2);

//        add several colors
        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);

//        instatiate pie data object
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(14);
        data.setValueTextColor(Color.BLACK);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();





    }


}
