package com.android.demo.activity.jetpack;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;

import com.android.demo.R;
import com.android.demo.databinding.ActivitySimpleLiveDataBinding;

/**
 * <p>
 *
 * @author anlc
 * @date 2021/2/1
 */
public class SimpleLiveDataActivity extends AppCompatActivity {

    public static class SimpleData {
        private MutableLiveData<Integer> count = new MutableLiveData<>();

        public SimpleData() {
            this.count.setValue(0);
        }

        public MutableLiveData<Integer> getCount() {
            return count;
        }

        public void add() {
            count.setValue(count.getValue() + 1);
        }
    }

//    private SimpleData mSimpleData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySimpleLiveDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_simple_live_data);
        SimpleData mSimpleData = new SimpleData();
        binding.setData(mSimpleData);
        binding.setLifecycleOwner(this);
    }
}
