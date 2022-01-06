package com.android.demo.activity.jetpack;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.android.demo.R;
import com.android.demo.databinding.ActivityViewModelBinding;

/**
 * <p>
 *
 * @author anlc
 * @date 2021/1/5
 */
public class ObservableJavaActivity extends AppCompatActivity {

    public static class ObservableFieldBean {
        public ObservableField<String> name;
        public ObservableInt count;

        public ObservableFieldBean(String name, ObservableInt count) {
            this.name = new ObservableField<>(name);
            this.count = count;
        }
    }

    private ObservableFieldBean mField = new ObservableFieldBean("item", new ObservableInt(0));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityViewModelBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_view_model);

        binding.setData(mField);
        binding.setLifecycleOwner(this);

        binding.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mField.count.set(mField.count.get() + 1);
                mField.name.set("item == " + mField.count.get());
            }
        });
    }
}
