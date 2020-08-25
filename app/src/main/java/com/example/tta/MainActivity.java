/*
The LayoutInflater class is used to instantiate the contents of layout XML files into their corresponding View objects.

In other words, it takes an XML file as input and builds the View objects from it.
 */
/*
Context is the base class for Activity, Service, Application, etc
Another way to describe this: Consider context as remote of a TV & channel's in the television are resources, services, using intents etc - - - Here remote acts as an access to get access to all the different resources into foreground.

So, Remote has access to channels such as resources, services, using intents etc ....

Likewise ... Whoever has access to remote naturally has access to all the things such as resources, services, using intents etc
 */
package com.example.tta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listview;

    //Default function
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUIViews();
        initToolbar();
        setupListView();
    }

    // It's like activating the components of
    // activity_main.xml
    private void setupUIViews() {
        toolbar = (Toolbar)findViewById(R.id.ToolbarMain);
        listview = (ListView)findViewById(R.id.lvMain);
    }

    // The below function is necessary because the toolbar is not interactive
    // It is like a dead widget. But by converting it to actionBar then
    // it can become interactive.
    private void initToolbar() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Timetable App");
        }
    }

    //We pass the required data for all the CardViews through this function
    // To the simpleAdapter.
    private void setupListView() {
        //Main and Description are located in the strings.xml file
        String[] title = getResources().getStringArray(R.array.Main);
        String[] description = getResources().getStringArray(R.array.Description);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, title, description);
        listview.setAdapter(simpleAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: {
                        //Intent means action (communication between two components of the app)
                        Intent intent = new Intent(MainActivity.this, WeekActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: {
                        Intent intent = new Intent(MainActivity.this, SubjectActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 2: {
                        Intent intent = new Intent(MainActivity.this, FacultyActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 3: {
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        });
    }
// Baseadapter is the simplest of all adapters
// SimpleAdaptor extends BaseAdaptor means
// implements Filterable(A filterable class can have its data constrained by a filter), ThemedSpinnerAdapter(An extension of SpinnerAdapter that is capable of inflating drop-down views against a different theme than normal views.) (extra features)
    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        // Loads different layouts in the ListView
        private TextView title, description;
        private String[] titleArray;
        private String[] descriptionArray;
        private ImageView imageView;

        public SimpleAdapter(Context context, String[] title, String[] description) {
            mContext = context;
            titleArray = title;
            descriptionArray = description;
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
            // Main activity has access only to activity_main.xml
            // But since we want to put the contents of the CardView(main_activity_single_item)
            // to the listview(activity_main.xml) we need to get access to the main_activity_single_item.
            // convertView helps in that way.
            if(convertView == null) {
                convertView = layoutInflater.inflate(R.layout.main_activity_single_item, null);
            }
            title = (TextView)convertView.findViewById(R.id.tvMain);
            description = (TextView)convertView.findViewById(R.id.tvDescription);
            imageView = (ImageView)convertView.findViewById(R.id.ivMain);
            title.setText(titleArray[position]);
            // titlearray contains the title for all  cardviews
            // position denotes the title for that particular cardview.
            // same for description
            description.setText(descriptionArray[position]);

            if(titleArray[position].equalsIgnoreCase("Timetable")){
                imageView.setImageResource(R.drawable.timetable);
            }else if(titleArray[position].equalsIgnoreCase("Subjects")){
                imageView.setImageResource(R.drawable.book);
            }else if(titleArray[position].equalsIgnoreCase("Faculty")){
                imageView.setImageResource(R.drawable.contact);
            }else {
                imageView.setImageResource(R.drawable.settings);
            }
            return convertView;
        }
    }
}
