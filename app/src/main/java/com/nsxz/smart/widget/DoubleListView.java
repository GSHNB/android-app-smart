package com.nsxz.smart.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.nsxz.smart.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DoubleListView extends LinearLayout {
    @BindView(R.id.rvItem)
    RecyclerView rvItem;
    @BindView(R.id.lvCate)
    ListView lvCate;

    private CateTabSelectedCallback callback;
    private GridLayoutManager layoutManager;
    private ArrayList<Integer> dataMap;
    private RecyclerView.Adapter adapter;


    public DoubleListView(Context context) {
        this(context, null);
    }

    public DoubleListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setCateAdapter(BaseAdapter adapter) {

        lvCate.setAdapter(adapter);
    }

    public void setItemAdapter(RecyclerView.Adapter adapter) {
        rvItem.setAdapter(adapter);
    }

    public void setDataMap(ArrayList<Integer> map) {
        dataMap = map;
    }

    public void setCateCallback(@NonNull CateTabSelectedCallback callback) {
        if (callback!=null) {
            this.callback = callback;
            initListener();
        }
    }

    private void init() {
        setOrientation(HORIZONTAL);
        LayoutInflater.from(getContext()).inflate(R.layout.widget_linkedlistview, this, true);
        ButterKnife.bind(this);
        layoutManager = new GridLayoutManager(getContext(),3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return 0;
            }
        });
        rvItem.setLayoutManager(layoutManager);
        rvItem.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
    }

    private void initListener() {

        lvCate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (dataMap != null) {
                    int index = dataMap.get(position);
                    rvItem.smoothScrollToPosition(index);

                }
            }
        });

        rvItem.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int position = layoutManager.findFirstVisibleItemPosition();
                    int groupId = queryGroupPosition(position);
                    if (callback != null) {
                        callback.selectCate(groupId);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }


    private int queryGroupPosition(int itemPosition) {
        if (dataMap != null) {
            for (int i = 0; i < dataMap.size(); i++) {
                int v = dataMap.get(i);
                if (itemPosition < v) {
                    return i - 1;
                }
            }
            return dataMap.size() - 1;
        }
        return 0;
    }

    public interface CateTabSelectedCallback {
        void selectCate(int position);
    }
}
