package com.android.demo;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_CLASS_NAME = "className";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_INTENT = "intent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.ac_list);
        listView.setAdapter(new SimpleAdapter(this, getData(),
                R.layout.list_item_main,
                new String[]{TAG_CLASS_NAME, TAG_DESCRIPTION,},
                new int[]{R.id.className, R.id.description,}));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                startActivity((Intent) map.get(TAG_INTENT));
            }
        });
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> data = new ArrayList<>();
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.setPackage(getPackageName());
        List<ResolveInfo> infoList = getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : infoList) {
            String label = info.loadLabel(getPackageManager()).toString();
            String name = info.activityInfo.name.replace(getPackageName(), "");
            name = name.replace(".", "");
            addItem(data, name, label, intent(info));
        }
        return data;
    }

    private Intent intent(ResolveInfo info) {
        Intent intent = new Intent();
        intent.setClassName(info.activityInfo.applicationInfo.packageName,
                info.activityInfo.name);
        return intent;
    }

    protected void addItem(List<Map<String, Object>> data, String className, String description, Intent intent) {
        Map<String, Object> temp = new HashMap<>();
        temp.put(TAG_CLASS_NAME, className);
        temp.put(TAG_DESCRIPTION, description);
        temp.put(TAG_INTENT, intent);
        data.add(temp);
    }
}
