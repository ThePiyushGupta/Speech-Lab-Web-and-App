package com.example.iitg_speech_lab;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iitg_speech_lab.Model.StudentCoursesAsTADataModel;
import com.example.iitg_speech_lab.Model.StudentCoursesDataModel;

import java.util.ArrayList;

public class StudentCoursesAsTACustomAdapter extends RecyclerView.Adapter<StudentCoursesAsTACustomAdapter.MyViewHolder> {

    private ArrayList<StudentCoursesAsTADataModel> dataSet;

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCourseID;
        TextView textViewCourseName;

        MyViewHolder(View itemView) {
            super(itemView);
            this.textViewCourseID = itemView.findViewById(R.id.textViewCourseID);
            this.textViewCourseName = itemView.findViewById(R.id.textViewCourseName);
        }
    }

    StudentCoursesAsTACustomAdapter(ArrayList<StudentCoursesAsTADataModel> data) {
        this.dataSet = data;
    }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        view.setOnClickListener(StudentCoursesActivity.myOnClickListener2);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewCourseID = holder.textViewCourseID;
        TextView textViewCourseName = holder.textViewCourseName;

        textViewCourseID.setText(dataSet.get(listPosition).getId());
        textViewCourseName.setText(dataSet.get(listPosition).getName());

        holder.itemView.setTag(dataSet.get(listPosition).getInfo());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
