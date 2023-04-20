package com.example.javary.learning;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.javary.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearningFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String  mParam1;
    private String mParam2;
    private String[] titles = {
            "• Урок #1 | Что такое  Java",
            "• Урок #2 | Импорт",
            "• Урок #3 | Коментарии",
            "• Урок #4 | Переменнные",
            "• Урок #5 | Операторы",
            "• Урок #6 | Типы данных",
            "• Урок #7 | Математика",
            "• Урок #8 | Логические операторы сравнения",
            "• Урок #9 | Условный оператор if",
            "• Урок #10 | Оператор switch",
            "• Урок #11 | Цикл while",
            "• Урок #12 | Цикл for",
            "• Урок #13 | Массивы",
            "• Урок #14 | Двухмерные массивы",
            "• Урок #15 | Методы",
            "• Урок #16 | Тест",
    };
    private String[] descriptions = {
            "Что такое  Java",
            "Импорт",
            "Коментарии",
            "Переменнные",
            "Операторы",
            "Типы данных",
            "Математика",
            "Логические операторы сравнения",
            "Условный оператор if",
            "Оператор switch",
            "Цикл while",
            "Цикл for",
            "Массивы",
            "Двухмерные массивы",
            "Методы",
            "Тест",
    };
    public LearningFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearningFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearningFragment newInstance(String param1, String param2) {
        LearningFragment fragment = new LearningFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_learning, container, false);
        ListView lv = view.findViewById(R.id.list_view);

        ArrayList<ItemContext> lessonContext = new ArrayList<>();
        double a =10;
        for(int i = 0; i < titles.length; i++){
            lessonContext.add(new ItemContext(
                    titles[i],
                    descriptions[i],
                    null,

                    8,1
            ));
        }

        LessonAdapter titlesAdapter = new LessonAdapter(getContext(), lessonContext);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });


        lv.setAdapter(titlesAdapter);

        return view;
    }
}