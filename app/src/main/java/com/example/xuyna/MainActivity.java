package com.example.xuyna;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private long summ;
    private long summ1;




    ViewPager2 viewPager2;
    BottomNavigationView bottomNavigationView;




    private EditText expenseInput;
    private FirebaseAuth mAuth;
    Button buttonLogout;
    private Button addButton, addButton1;
    private ExpenseAdapter expenseAdapter;
    private List<Expense> expenses = new ArrayList<>();
    private ListView listView;
    private static final String TABLE_NAME = "mytable";
    private static final String COLUMN_VALUE = "value";



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        mAuth = FirebaseAuth.getInstance();




        int sum1 = 0;



        getSupportActionBar().setDisplayShowHomeEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();




        expenseInput = findViewById(R.id.edit_text_input);

        expenseAdapter = new ExpenseAdapter(expenses);




        addButton = findViewById(R.id.add_button);;
        Button addButon = findViewById(R.id.add_button);
        EditText editText = findViewById(R.id.edit_text_input);
        listView = findViewById(R.id.list_view_output);
        final int[] sum = {0};

        ArrayList<String> arrayList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        ListView listView = findViewById(R.id.list_view_output);


        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().trim().equals("")) {
                    arrayList.add(editText.getText() + "₽");
                    adapter.notifyDataSetChanged();
                    Integer selectedValue = (Integer) listView.getSelectedItem();

                    String input = editText.getText().toString();
                    int number = Integer.parseInt(input);
                    summ += number;

                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.COLUMN_VALUE, selectedValue);
                    db.insert(DatabaseHelper.TABLE_NAME, null, values);
                    editText.setText("");

                }

                PieChart mobilityPieChart = findViewById(R.id.mobility_pie_chart);
                mobilityPieChart.setDragDecelerationFrictionCoef(1f);

                ArrayList<PieEntry> mobilityEntries = new ArrayList<>();
                mobilityEntries.add(new PieEntry(summ, "Траты"));
                mobilityEntries.add(new PieEntry(summ1, "Доходы"));

                PieDataSet mobilityDataSet = new PieDataSet(mobilityEntries, "Label");
                mobilityDataSet.setSliceSpace(5f);
                mobilityDataSet.setSelectionShift(0f);
                mobilityDataSet.setValueTextSize(12f);
                mobilityDataSet.setValueTextColor(Color.TRANSPARENT);

                int[] mobilityColor = {Color.rgb(255, 0, 0), Color.rgb(26, 255, 0)};

                mobilityDataSet.setColors(mobilityColor);

                PieData mobilityData = new PieData(mobilityDataSet);

                mobilityPieChart.setData(mobilityData);
                mobilityPieChart.setUsePercentValues(false);
                mobilityPieChart.setHoleRadius(75f);
                mobilityPieChart.setTransparentCircleRadius(100f);
                mobilityPieChart.getDescription().setEnabled(false);
                mobilityPieChart.setDrawEntryLabels(false);
                mobilityPieChart.getLegend().setEnabled(false);
                mobilityPieChart.setEntryLabelTextSize(20f);
                mobilityPieChart.setDrawCenterText(true);
//        mobilityPieChart.setCenterText("1020");
                mobilityPieChart.setCenterTextSize(50f);
                mobilityPieChart.animateY(1500, Easing.EaseInOutQuad);


            }
        });


        Button Button4 = findViewById(R.id.button4);
        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastItemIndex = listView.getCount() - 1;
                if (lastItemIndex >= 0) {

                    ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();

                    adapter.remove(adapter.getItem(lastItemIndex));

                    adapter.notifyDataSetChanged();
                }

                PieChart mobilityPieChart = findViewById(R.id.mobility_pie_chart);
                mobilityPieChart.setDragDecelerationFrictionCoef(1f);

                ArrayList<PieEntry> mobilityEntries = new ArrayList<>();
                mobilityEntries.add(new PieEntry(summ, "Траты"));
                mobilityEntries.add(new PieEntry(summ1, "Доходы"));

                PieDataSet mobilityDataSet = new PieDataSet(mobilityEntries, "Label");
                mobilityDataSet.setSliceSpace(5f);
                mobilityDataSet.setSelectionShift(0f);
                mobilityDataSet.setValueTextSize(12f);
                mobilityDataSet.setValueTextColor(Color.TRANSPARENT);

                int[] mobilityColor = {Color.rgb(255, 0, 0), Color.rgb(26, 255, 0)};

                mobilityDataSet.setColors(mobilityColor);

                PieData mobilityData = new PieData(mobilityDataSet);

                mobilityPieChart.setData(mobilityData);
                mobilityPieChart.setUsePercentValues(false);
                mobilityPieChart.setHoleRadius(75f);
                mobilityPieChart.setTransparentCircleRadius(100f);
                mobilityPieChart.getDescription().setEnabled(false);
                mobilityPieChart.setDrawEntryLabels(false);
                mobilityPieChart.getLegend().setEnabled(false);
                mobilityPieChart.setEntryLabelTextSize(20f);
                mobilityPieChart.setDrawCenterText(true);
//        mobilityPieChart.setCenterText("1020");
                mobilityPieChart.setCenterTextSize(50f);
                mobilityPieChart.animateY(1500, Easing.EaseInOutQuad);

            }


        });



        addButton1 = findViewById(R.id.add_button1);
        Button addButton1 = findViewById(R.id.add_button1);
        EditText editText1 = findViewById(R.id.edit_text_input1);
        ListView listView1 = findViewById(R.id.list_view_output1);
        ArrayList<String> arrayList1 = new ArrayList<>();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1, arrayList1);
        listView1.setAdapter(adapter1);
        addButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText1.getText().toString().trim().equals("")) {
                    arrayList1.add(editText1.getText() + "₽");
                    adapter1.notifyDataSetChanged();

                    Integer selectedValue = (Integer) listView.getSelectedItem();

                    String input1 = editText1.getText().toString();
                    int number1 = Integer.parseInt(input1);
                    summ1 += number1;

                    ContentValues values = new ContentValues();
                    values.put(DatabaseHelper.COLUMN_VALUE, selectedValue);
                    db.insert(DatabaseHelper.TABLE_NAME, null, values);

                    editText1.setText("");

                }

                PieChart mobilityPieChart = findViewById(R.id.mobility_pie_chart);
                mobilityPieChart.setDragDecelerationFrictionCoef(1f);

                ArrayList<PieEntry> mobilityEntries = new ArrayList<>();
                mobilityEntries.add(new PieEntry(summ, "Траты"));
                mobilityEntries.add(new PieEntry(summ1, "Доходы"));

                PieDataSet mobilityDataSet = new PieDataSet(mobilityEntries, "Label");
                mobilityDataSet.setSliceSpace(5f);
                mobilityDataSet.setSelectionShift(0f);
                mobilityDataSet.setValueTextSize(12f);
                mobilityDataSet.setValueTextColor(Color.TRANSPARENT);

                int[] mobilityColor = {Color.rgb(255, 0, 0), Color.rgb(26, 255, 0)};

                mobilityDataSet.setColors(mobilityColor);

                PieData mobilityData = new PieData(mobilityDataSet);

                mobilityPieChart.setData(mobilityData);
                mobilityPieChart.setUsePercentValues(false);
                mobilityPieChart.setHoleRadius(75f);
                mobilityPieChart.setTransparentCircleRadius(100f);
                mobilityPieChart.getDescription().setEnabled(false);
                mobilityPieChart.setDrawEntryLabels(false);
                mobilityPieChart.getLegend().setEnabled(false);
                mobilityPieChart.setEntryLabelTextSize(20f);
                mobilityPieChart.setDrawCenterText(true);
//        mobilityPieChart.setCenterText("1020");
                mobilityPieChart.setCenterTextSize(50f);
                mobilityPieChart.animateY(1500, Easing.EaseInOutQuad);
            }
        });

        Button Button5 = findViewById(R.id.button5);
        Button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lastItemIndex = listView1.getCount() - 1;
                if (lastItemIndex >= 0) {
                    // Получите адаптер вашего ListView
                    ArrayAdapter adapter = (ArrayAdapter) listView1.getAdapter();

                    // Удалите последний элемент из адаптера
                    adapter.remove(adapter.getItem(lastItemIndex));

                    // Обновите ListView после удаления элемента
                    adapter.notifyDataSetChanged();
                }

                PieChart mobilityPieChart = findViewById(R.id.mobility_pie_chart);
                mobilityPieChart.setDragDecelerationFrictionCoef(1f);

                ArrayList<PieEntry> mobilityEntries = new ArrayList<>();
                mobilityEntries.add(new PieEntry(summ, "Траты"));
                mobilityEntries.add(new PieEntry(summ1, "Доходы"));

                PieDataSet mobilityDataSet = new PieDataSet(mobilityEntries, "Label");
                mobilityDataSet.setSliceSpace(5f);
                mobilityDataSet.setSelectionShift(0f);
                mobilityDataSet.setValueTextSize(12f);
                mobilityDataSet.setValueTextColor(Color.TRANSPARENT);

                int[] mobilityColor = {Color.rgb(255, 0, 0), Color.rgb(26, 255, 0)};

                mobilityDataSet.setColors(mobilityColor);

                PieData mobilityData = new PieData(mobilityDataSet);

                mobilityPieChart.setData(mobilityData);
                mobilityPieChart.setUsePercentValues(false);
                mobilityPieChart.setHoleRadius(75f);
                mobilityPieChart.setTransparentCircleRadius(100f);
                mobilityPieChart.getDescription().setEnabled(false);
                mobilityPieChart.setDrawEntryLabels(false);
                mobilityPieChart.getLegend().setEnabled(false);
                mobilityPieChart.setEntryLabelTextSize(20f);
                mobilityPieChart.setDrawCenterText(true);
//        mobilityPieChart.setCenterText("1020");
                mobilityPieChart.setCenterTextSize(50f);
                mobilityPieChart.animateY(1500, Easing.EaseInOutQuad);

            }
        });






        Button Button6 = findViewById(R.id.button6);
        Button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
                adapter.clear();
                ArrayAdapter adapter1 = (ArrayAdapter) listView1.getAdapter();
                adapter1.clear();
            }


        });




        Button Button7 = findViewById(R.id.button7);
        Button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                finish();
            }
        });

        PieChart mobilityPieChart = findViewById(R.id.mobility_pie_chart);
        mobilityPieChart.setDragDecelerationFrictionCoef(1f);

        ArrayList<PieEntry> mobilityEntries = new ArrayList<>();
        mobilityEntries.add(new PieEntry(summ, "Траты"));
        mobilityEntries.add(new PieEntry(summ1, "Доходы"));

        PieDataSet mobilityDataSet = new PieDataSet(mobilityEntries, "Label");
        mobilityDataSet.setSliceSpace(5f);
        mobilityDataSet.setSelectionShift(0f);
        mobilityDataSet.setValueTextSize(12f);
        mobilityDataSet.setValueTextColor(Color.TRANSPARENT);

        int[] mobilityColor = {Color.rgb(255, 0, 0), Color.rgb(26, 255, 0)};

        mobilityDataSet.setColors(mobilityColor);

        PieData mobilityData = new PieData(mobilityDataSet);

        mobilityPieChart.setData(mobilityData);
        mobilityPieChart.setUsePercentValues(false);
        mobilityPieChart.setHoleRadius(75f);
        mobilityPieChart.setTransparentCircleRadius(100f);
        mobilityPieChart.getDescription().setEnabled(false);
        mobilityPieChart.setDrawEntryLabels(false);
        mobilityPieChart.getLegend().setEnabled(false);
        mobilityPieChart.setEntryLabelTextSize(20f);
        mobilityPieChart.setDrawCenterText(true);
//        mobilityPieChart.setCenterText("1020");
        mobilityPieChart.setCenterTextSize(50f);
        mobilityPieChart.animateY(1500, Easing.EaseInOutQuad);


    }




}