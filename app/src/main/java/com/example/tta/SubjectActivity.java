package com.example.tta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.tta.Utils.LetterImageView;

import java.util.Objects;

public class SubjectActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listview;
    private String[] subjects;
    public static SharedPreferences subjectPreferences;
    public static String SUB_PREF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        setupUIViews();
        initToolbar();
        setupListView();
    }

    private void setupUIViews() {
        toolbar = (Toolbar)findViewById(R.id.ToolbarSubject);
        listview = (ListView)findViewById(R.id.lvSubject);
        subjectPreferences = getSharedPreferences("Subjects", MODE_PRIVATE);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Subject");
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    private void setupListView() {
        subjects = getResources().getStringArray(R.array.Subjects);
        SubjectAdapter subjectAdapter = new SubjectAdapter(this, R.layout.subject_single_item, subjects);
        listview.setAdapter(subjectAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0: {
                        subjectPreferences.edit().putString(SUB_PREF, "Biology").apply();
                        Intent intent = new Intent(SubjectActivity.this, SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: {
                        subjectPreferences.edit().putString(SUB_PREF, "Chemistry").apply();
                        Intent intent = new Intent(SubjectActivity.this, SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    case 2: {
                        subjectPreferences.edit().putString(SUB_PREF, "Physics").apply();
                        Intent intent = new Intent(SubjectActivity.this, SubjectDetails.class);
                        startActivity(intent);
                        break;
                    }
                    default:break;
                }
            }
        });
    }

    public class SubjectAdapter extends ArrayAdapter {

        private int resource;
        private LayoutInflater layoutInflater;
        private String[] subjects = new String[]{};

        public SubjectAdapter(@NonNull Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.subjects = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.ivLetterSubject);
                holder.tvSubject = (TextView)convertView.findViewById(R.id.tvSubject);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(subjects[position].charAt(0));
            holder.tvSubject.setText(subjects[position]);

            return convertView;
        }
        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvSubject;
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