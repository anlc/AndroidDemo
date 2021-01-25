package com.android.demo.activity.jetpack;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
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

    private ActivityLiveDataBinding mContentBinding;

    private MutableLiveData<String> mTitle = new MutableLiveData<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentBinding = DataBindingUtil.setContentView(this, R.layout.activity_live_data);
        mTitle.setValue("title");
        mContentBinding.setTitle(mTitle.getValue());
        mTitle.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mContentBinding.setTitle(s);
            }
        });
        mContentBinding.setLifecycleOwner(this);
    }

    public void onBtnClick(View view) {
        mTitle.setValue("item change");
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
