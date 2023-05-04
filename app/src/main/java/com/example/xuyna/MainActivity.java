package com.example.xuyna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText expenseInput;
    private Button addButton;
    private RecyclerView expenseList;
    private ExpenseAdapter expenseAdapter;
    private List<Expense> expenses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseInput = findViewById(R.id.expense_input);
        addButton = findViewById(R.id.add_button);
        expenseList = findViewById(R.id.expense_list);

        expenseAdapter = new ExpenseAdapter(expenses);
        expenseList.setLayoutManager(new LinearLayoutManager(this));
        expenseList.setAdapter(expenseAdapter);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.edit_text_input);
                TextView textView = findViewById(R.id.text_view_output);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }});
                String expenseDescription = expenseInput.getText().toString();
                if (!expenseDescription.isEmpty()) {
                    Expense expense = new Expense(expenseDescription);
                    expenseInput.setText("");
                }
            }
        });
    }
}