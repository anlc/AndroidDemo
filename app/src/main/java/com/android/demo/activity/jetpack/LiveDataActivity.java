package com.android.demo.activity.jetpack;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.demo.R;
import com.android.demo.databinding.ActivityLiveDataBinding;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2021/1/5
 */
public class LiveDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLiveDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_live_data);
        binding.setLifecycleOwner(this);

    }

    class TitleViewModel extends ViewModel implements Observable {

        private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();
        LiveData userName;

        public TitleViewModel() {
        }

        @Override
        public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
            callbacks.add(callback);
        }

        @Override
        public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
            callbacks.remove(callback);
        }
    }
}
