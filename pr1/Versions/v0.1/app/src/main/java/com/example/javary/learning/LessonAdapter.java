package com.example.javary.learning;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.javary.R;

import java.util.ArrayList;

public class LessonAdapter extends ArrayAdapter<ItemContext> {

    public LessonAdapter(@NonNull Context context, ArrayList<ItemContext> lessonTitles) {
        super(context, R.layout.list_item, lessonTitles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
        }
        TextView title = convertView.findViewById(R.id.lesson_title);
        TextView description = convertView.findViewById(R.id.lesson_description);
        ProgressBar progressBar = convertView.findViewById(R.id.lesson_progress);
        ItemContext itemContext = getItem(position);

        title.setText(itemContext.title);
        description.setText(itemContext.description);
        progressBar.setMax(itemContext.maxProgress);
        progressBar.setProgress(itemContext.currentProgress);

        return convertView;
    }
}
