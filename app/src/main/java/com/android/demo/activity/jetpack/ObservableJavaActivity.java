package com.android.demo.activity.jetpack;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;

import com.android.demo.R;
import com.android.demo.databinding.ActivityViewModelBinding;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2021/1/5
 */
public class ObservableJavaActivity extends AppCompatActivity {

    public static class ObservableFieldBean {
        public String name;
        public ObservableInt count;

        public ObservableFieldBean(String name, ObservableInt count) {
            this.name = name;
            this.count = count;
        }
    }

    private MutableLiveData<String> mTitle = new MutableLiveData<>();
    private static ObservableFieldBean mField = new ObservableFieldBean("item", new ObservableInt(0));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityViewModelBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_view_model);
//        mTitle.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
//            @Override
//            public void onPropertyChanged(Observable sender, int propertyId) {
//                binding.setTitle(mTitle.get());
//            }
//        });
        mTitle.postValue("default title");

        binding.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitle.postValue("xxx" + mField.count.get());
                mField.count.set(mField.count.get() + 1);
            }
        });
    }
}
