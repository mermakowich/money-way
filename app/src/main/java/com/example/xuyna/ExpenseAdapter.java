package com.example.xuyna;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private List<Expense> expenses;

    public ExpenseAdapter(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {

        public TextView description;

        public ExpenseViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.expense_description);
        }
    }e

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.description.setText(expense.getDescription());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }


}
