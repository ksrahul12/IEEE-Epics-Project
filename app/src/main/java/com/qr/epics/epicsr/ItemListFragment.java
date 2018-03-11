package com.qr.epics.epicsr;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Raja on 25-Jan-18.
 */

public class ItemListFragment extends Fragment {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private RecyclerView mRecycleView;
    private SimpleItemRecyclerViewAdapter mSimpleItemRecyclerViewAdapter;
    public ItemListFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mRecycleView = (RecyclerView)view.findViewById(R.id.item_list);
        mSimpleItemRecyclerViewAdapter = new SimpleItemRecyclerViewAdapter(getActivity(), DummyContent.ITEMS);
        mRecycleView.setAdapter(mSimpleItemRecyclerViewAdapter);
        return view;
    }
}