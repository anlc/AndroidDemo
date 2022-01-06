package com.android.demo.activity.design;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.demo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *
 * @author anlc
 * @date 2020/11/2
 */
public class ItemHelperActivity extends AppCompatActivity {

    private static final String TAG = "ItemHelperActivity";

    private ItemTouchHelper mTouchHelper;
    private RecyclerView mRvList;
    private GridLayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_helper);
        mRvList = findViewById(R.id.rvList);
        layoutManager = new GridLayoutManager(this, 3);
        mRvList.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter();
        mRvList.setAdapter(adapter);

        mTouchHelper = new ItemTouchHelper(new TouchCallback(adapter));
        mTouchHelper.attachToRecyclerView(mRvList);
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView mTvItem;

        public Holder(@NonNull View itemView) {
            super(itemView);
            mTvItem = itemView.findViewById(R.id.tv_item);
        }
    }

    class Adapter extends RecyclerView.Adapter<Holder> implements IItemHelper {

        private List<String> stringList = new ArrayList<>();

        public Adapter() {
            for (int i = 0; i < 10; i++) {
                stringList.add("item " + i);
            }
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Holder(View.inflate(ItemHelperActivity.this, R.layout.item_helper, null));
        }

        @Override
        public void onBindViewHolder(@NonNull final Holder holder, int position) {
            holder.mTvItem.setText(stringList.get(position));
            holder.mTvItem.setSelected(holder.itemView.isSelected());
        }

        @Override
        public int getItemCount() {
            return stringList.size();
        }

        @Override
        public void itemMoved(int oldPosition, int newPosition) {
            Collections.swap(stringList, oldPosition, newPosition);
            notifyItemMoved(oldPosition, newPosition);
        }

        @Override
        public void itemDismiss(int position) {
            // 从数据集移除数据
            stringList.remove(position);
            // 通知Adapter更新
            notifyItemRemoved(position);
        }
    }

    public interface IItemHelper {

        void itemMoved(int oldPosition, int newPosition);

        void itemDismiss(int position);
    }

    class TouchCallback extends ItemTouchHelper.Callback {

        private IItemHelper itemHelper;

        public TouchCallback(IItemHelper itemHelper) {
            this.itemHelper = itemHelper;
        }


        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            int dragFlags;
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof GridLayoutManager || manager instanceof StaggeredGridLayoutManager) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
            // 如果想支持滑动(删除)操作, swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END
            int swipeFlags = 0;
            if (viewHolder.getAdapterPosition() < 2) {
                return makeMovementFlags(0, swipeFlags);
            }
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int targetPosition = target.getAdapterPosition();
            if (targetPosition < 2) {
                targetPosition = 2;
            }
            itemHelper.itemMoved(viewHolder.getAdapterPosition(), targetPosition);
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            itemHelper.itemDismiss(viewHolder.getAdapterPosition());
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            super.onSelectedChanged(viewHolder, actionState);
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                Log.d(TAG, "onSelectedChanged: == SWIPE");
            } else if (actionState == ItemTouchHelper.ACTION_STATE_IDLE) {
                Log.d(TAG, "onSelectedChanged: == IDLE");
            } else if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                Log.d(TAG, "onSelectedChanged: == DRAG");
                ((Holder) viewHolder).mTvItem.setSelected(true);
            }
            Log.d(TAG, "onSelectedChanged: == actionState: " + actionState);
        }

        //滑动完，拖动完调用
        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            Log.d(TAG, "clearView: == ");
            ((Holder) viewHolder).mTvItem.setSelected(false);
        }

        //重写这个方法修改你的UI响应
        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                Log.d(TAG, "onChildDraw: == SWIPE");
            } else if (actionState == ItemTouchHelper.ACTION_STATE_IDLE) {
                Log.d(TAG, "onChildDraw: == IDLE");
            } else if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                Log.d(TAG, "onChildDraw: == DRAG");
            }
            Log.d(TAG, "onChildDraw: == actionState: " + actionState);
        }
    }
}
