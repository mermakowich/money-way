package com.example.xuyna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                String expenseDescription = expenseInput.getText().toString();
                if (!expenseDescription.isEmpty()) {
                    Expense expense = new Expense(expenseDescription);
                    expenses.add(expense);
                    expenseAdapter.notifyDataSetChanged();
                    expenseInput.setText("");
                }
            }
        });
    }
}