package com.android.demo.activity.jetpack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.demo.R;
import com.android.demo.databinding.ActivityDataDindingBinding;
import com.android.demo.databinding.FragmentBindingBinding;
import com.android.demo.databinding.LayoutListBindingBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2021/1/5
 */
public class DataBindingActivity extends AppCompatActivity {
    
    public static class UserName extends BaseObservable {
        public String name;

        public UserName(String name) {
            this.name = name;
        }

        @Bindable
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
            notifyPropertyChanged(BR.name);

        }
    }

    public static class DataBean {
        public String name;
        public int age;

        public DataBean(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public class Events{
        public void onNameClick(View view) {

        }
    }

    private RecyclerView mRvList;
    private List<DataBean> mDataBeanList;
    private RecyclerView.Adapter<Holder> mAdapter;
    private BindingFragment mFragment;
    private UserName mName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataDindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_dinding);
        mName = new UserName("三年二班");
        binding.setUser(mName);

        findViewById(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName.name = "xxx";
            }
        });

        mFragment = new BindingFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_layout, mFragment).commit();

        initData();
        mRvList = findViewById(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new Adapter();
        mRvList.setAdapter(mAdapter);
    }

    public void onUserNameClick(){
        mName.name = "xxx";
    }

    private void initData() {
        mDataBeanList = new ArrayList<>();
        mDataBeanList.add(new DataBean("aaa", 12));
        mDataBeanList.add(new DataBean("bbb", 12));
        mDataBeanList.add(new DataBean("ccc", 12));
        mDataBeanList.add(new DataBean("ddd", 12));
    }

    public class Adapter extends RecyclerView.Adapter<Holder> {

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(DataBindingActivity.this);
            LayoutListBindingBinding inflate = DataBindingUtil.inflate(inflater, R.layout.layout_list_binding, parent, false);
            return new Holder(inflate.getRoot());
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, final int position) {
            LayoutListBindingBinding holderBinding = DataBindingUtil.getBinding(holder.itemView);
            holderBinding.setDataBean(mDataBeanList.get(position));
            holderBinding.executePendingBindings();
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mFragment.setData(mDataBeanList.get(position));
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return mDataBeanList == null ? 0 : mDataBeanList.size();
        }

        public void onItemClick(DataBean bean) {
            mFragment.setData(bean);
        }
    }

    static class Holder extends RecyclerView.ViewHolder {

        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class BindingFragment extends Fragment {

        private FragmentBindingBinding binding;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_binding, container, false);
            return binding.getRoot();
        }

        public void setData(DataBean bean) {
            binding.setDataBean(bean);
        }
    }
}
