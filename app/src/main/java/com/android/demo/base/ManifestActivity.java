package com.android.demo.base;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.demo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/20.
 */
public class ManifestActivity extends BaseActivity {

    private static final String TAG_CLASS_NAME = "className";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_INTENT = "intent";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setContentAndAction(String action) {
        ListView listView = new ListView(this);
        setContentView(listView);

        setAdapter(listView, action);
    }

    public final void setAdapter(ListView listView, String manifestAction) {
        listView.setAdapter(new SimpleAdapter(this, getData(manifestAction),
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

    private List<Map<String, Object>> getData(String manifestAction) {
        List<Map<String, Object>> data = new ArrayList<>();
        Intent intent = new Intent(manifestAction, null);
        intent.setPackage(getPackageName());
        List<ResolveInfo> infoList = getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo info : infoList) {
            String label = info.loadLabel(getPackageManager()).toString();
            String name = info.activityInfo.name.replace(getPackageName() + ".activity.", "");
            String[] arr = name.split("\\.");
            name = arr.length == 2 ? arr[1] : name;
            if (name.equals("MainActivity")) continue;
            addItem(data, name, label, intent(info));
        }
        return data;
    }

    private Intent intent(ResolveInfo info) {
        Intent intent = new Intent();
        intent.setClassName(info.activityInfo.applicationInfo.packageName, info.activityInfo.name);
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
