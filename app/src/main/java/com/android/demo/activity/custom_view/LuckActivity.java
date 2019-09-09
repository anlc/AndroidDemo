package com.android.demo.activity.custom_view;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.demo.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/2/8.
 */

public class LuckActivity extends AppCompatActivity {

    private static int[] arr = new int[]{0, 1, 2, 5, 8, 7, 6, 3};
    private RecyclerView recyclerView;
    private TempAdapter adapter;
    private GridLayoutManager layoutManager;

    private Timer timer;
    private int time = 0;
    private int maxStep = 30;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luck);
        adapter = new TempAdapter();
        recyclerView = findViewById(R.id.list_view);
        layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    public void start() {
        maxStep = ((int) (Math.random() * 20)) + 30;
        layoutManager.findViewByPosition(4).setSelected(false);
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        int last;
                        if (arr[time % 8] == 0) {
                            last = 3;
                        } else {
                            last = arr[time % 8 - 1];
                        }
                        if (time == maxStep) {
                            layoutManager.findViewByPosition(last).setSelected(false);
                            layoutManager.findViewByPosition(4).setSelected(true);
                            timer.cancel();
                            timer = null;
                            time = 0;
                            return;
                        }
                        layoutManager.findViewByPosition(arr[time % 8]).setSelected(true);
                        layoutManager.findViewByPosition(last).setSelected(false);
                    }
                });
            }
        }, 0, 100);
    }

    class TempAdapter extends RecyclerView.Adapter<Holder> {

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(LuckActivity.this).inflate(R.layout.item_view, parent, false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 9;
        }
    }

    class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
