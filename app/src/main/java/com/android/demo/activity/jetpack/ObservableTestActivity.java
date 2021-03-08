package com.android.demo.activity.jetpack;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;

import com.android.demo.R;
import com.android.demo.databinding.ActivityObservableTestBinding;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2021/2/23
 */
public class ObservableTestActivity extends AppCompatActivity {

    public static class BaseBean{

        private ObservableBoolean network = new ObservableBoolean(true);

        public ObservableBoolean getNetwork() {
            return network;
        }

        public void setNetwork(boolean network) {
            this.network.set(network);
        }
    }

    public class FavoriteBean extends BaseBean{

        public void onClick(View view) {
            isShow = !isShow;
            updateView();
        }
    }

    private ActivityObservableTestBinding mContentBinding;
    private FavoriteBean mFavoriteBean;
    private boolean isShow = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentBinding = DataBindingUtil.setContentView(this, R.layout.activity_observable_test);

        mFavoriteBean = new FavoriteBean();
        mContentBinding.setData(mFavoriteBean);

        updateView();
    }

    public void updateView(){
        mFavoriteBean.setNetwork(isShow);
    }
}
