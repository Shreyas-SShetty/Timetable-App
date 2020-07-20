package com.example.tta;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class SubjectDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);
        setupUIViews();
        initToolbar();
        setupListView();
    }

    private void setupUIViews() {
        toolbar = (Toolbar)findViewById(R.id.ToolbarSubjectDetails);
        listview = (ListView)findViewById(R.id.lvSubjectDetails);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Syllabus");
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView() {
        String subject_selected = SubjectActivity.subjectPreferences.getString(SubjectActivity.SUB_PREF,null);

        String[] syllabus = new String[]{};
        String[] titles = getResources().getStringArray(R.array.titles);

        if(subject_selected.equalsIgnoreCase("Biology")){
            syllabus = getResources().getStringArray(R.array.Biology);
        }
        else if(subject_selected.equalsIgnoreCase("Chemistry")){
            syllabus = getResources().getStringArray(R.array.Chemistry);
        }
        else{
            syllabus = getResources().getStringArray(R.array.Physics);
        }

        SubjectDetailsAdapter subjectDetailsAdapter = new SubjectDetailsAdapter(this,titles,syllabus);
        listview.setAdapter(subjectDetailsAdapter);
    }

    public class SubjectDetailsAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, syllabus;
        private String[] titleArray;
        private String[] syllabusArray;

        public SubjectDetailsAdapter(Context context, String[] title, String[] syllabus) {
            mContext = context;
            titleArray = title;
            syllabusArray = syllabus;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = layoutInflater.inflate(R.layout.subject_details_single_item, null);
            }
            title = (TextView)convertView.findViewById(R.id.tvSubjectTitle);
            syllabus = (TextView)convertView.findViewById(R.id.tvSyllabus);
            title.setText(titleArray[position]);
            syllabus.setText(syllabusArray[position]);

            return convertView;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}