package org.ut.rme.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ut.rme.R;
import org.ut.rme.adapter.RemindListAdapter;
import org.ut.rme.dto.RemindDTO;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends AbstractTabFragment {

    private  static final int LAYOUT = R.layout.fragment_history;

    public static HistoryFragment getInstance(Context context) {
        Bundle args = new Bundle();
        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTitle(context.getString(R.string.tab_item_history));
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycleView);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(new RemindListAdapter(creatMockRemindListData()));


        return view;
    }

    private List<RemindDTO> creatMockRemindListData() {
        List<RemindDTO> data = new ArrayList<>();
        data.add(new RemindDTO("Item 1"));
        data.add(new RemindDTO("Item 2"));
        data.add(new RemindDTO("Item 3"));
        data.add(new RemindDTO("Item 4"));
        data.add(new RemindDTO("Item 5"));
        data.add(new RemindDTO("Item 6"));
        data.add(new RemindDTO("Item 7"));
        data.add(new RemindDTO("Item 8"));
        data.add(new RemindDTO("Item 9"));
        data.add(new RemindDTO("Item 10"));
        data.add(new RemindDTO("Item 11"));
        data.add(new RemindDTO("Item 13"));
        data.add(new RemindDTO("Item 14"));
        data.add(new RemindDTO("Item 15"));
        data.add(new RemindDTO("Item 16"));
        data.add(new RemindDTO("Item 17"));
        data.add(new RemindDTO("Item 18"));
        data.add(new RemindDTO("Item 19"));
        data.add(new RemindDTO("Item 20"));
        data.add(new RemindDTO("Item 21"));

        return data;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}
