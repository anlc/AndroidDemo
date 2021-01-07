package com.android.demo.activity.jetpack;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.demo.R;
import com.android.demo.databinding.ActivityRecyclerViewBinding;
import com.android.demo.databinding.LayoutImageListBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * <p>
 *
 * @author v_anlaochou
 * @date 2021/1/6
 */
public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRecyclerViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view);

        EmployeeDBResponse dbResponse = new Gson().fromJson(content, EmployeeDBResponse.class);
        Adapter adapter = new Adapter(this, dbResponse.getEmployee());
        binding.viewEmployees.setLayoutManager(new LinearLayoutManager(this));
        binding.viewEmployees.setAdapter(adapter);
    }

    static class Adapter extends RecyclerView.Adapter<Holder> {

        private Context mContext;
        private List<Employee> mEmployeeList;

        public Adapter(Context mContext, List<Employee> mEmployeeList) {
            this.mContext = mContext;
            this.mEmployeeList = mEmployeeList;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.layout_image_list, parent, false);
            return new Holder(binding.getRoot());
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            LayoutImageListBinding binding = DataBindingUtil.getBinding(holder.itemView);
            binding.setEmployee(mEmployeeList.get(position));
        }

        @Override
        public int getItemCount() {
            return mEmployeeList.size();
        }
    }

    static class Holder extends RecyclerView.ViewHolder {

        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }

    String content = "{" +
            "  \"page\": 1," +
            "  \"per_page\": 3," +
            "  \"total\": 12," +
            "  \"total_pages\": 4," +
            "  \"data\": [" +
            "    {" +
            "      \"id\": 1," +
            "      \"email\": \"george.bluth@reqres.in\"," +
            "      \"first_name\": \"George\"," +
            "      \"last_name\": \"Bluth\"," +
            "      \"avatar\": \"https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png\"" +
            "    }," +
            "    {" +
            "      \"id\": 2," +
            "      \"email\": \"janet.weaver@reqres.in\"," +
            "      \"first_name\": \"Janet\"," +
            "      \"last_name\": \"Weaver\"," +
            "      \"avatar\": \"https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png\"" +
            "    }," +
            "    {" +
            "      \"id\": 3," +
            "      \"email\": \"emma.wong@reqres.in\"," +
            "      \"first_name\": \"Emma\"," +
            "      \"last_name\": \"Wong\"," +
            "      \"avatar\": \"https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png\"" +
            "    }" +
            "  ]" +
            "}";


    public static class Employee {
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("email")
        private String email;
        @SerializedName("first_name")
        private String firstName;
        @SerializedName("id")
        private Long id;
        @SerializedName("last_name")
        private String lastName;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        // important code for loading image here
        @BindingAdapter({"avatar"})
        public static void loadImage(ImageView imageView, String imageURL) {
            Glide.with(imageView.getContext())
                    .setDefaultRequestOptions(new RequestOptions().circleCrop())
                    .load(imageURL)
                    .placeholder(R.drawable.ic_arrow_back_black_24dp)
                    .into(imageView);
        }
    }

    public class EmployeeDBResponse {
        @SerializedName("page")
        @Expose
        private Integer page;
        @SerializedName("per_page")
        @Expose
        private Integer perPage;
        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("total_pages")
        @Expose
        private Integer totalPages;
        @SerializedName("data")
        @Expose
        private List<Employee> employee = null;

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

        public List<Employee> getEmployee() {
            return employee;
        }

        public void setEmployee(List<Employee> employee) {
            this.employee = employee;
        }
    }
}
