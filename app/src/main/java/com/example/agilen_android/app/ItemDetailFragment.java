package com.example.agilen_android.app;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.TextView;


import com.example.agilen_android.app.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Item mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    ActionMode mActionMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments().containsKey("item")) { //TODO varför ska denna finnas här?
            mItem = (Item) getArguments().getSerializable("item");

            if(getArguments().getBoolean("two_pane") == true) {
                mActionMode = getActivity().startActionMode(new ActionBarCallBack());
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            TextView tv = (TextView) rootView.findViewById(R.id.title);
            ((TextView) rootView.findViewById(R.id.title)).setText(mItem.getTitle());
           // ((TextView) rootView.findViewById(R.id.rating)).setText(mItem.getRating());
            ((TextView) rootView.findViewById(R.id.descr)).setText(mItem.getDescription());
        }

        return rootView;
    }

    class ActionBarCallBack implements ActionMode.Callback {

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // TODO Auto-generated method stub
            mode.getMenuInflater().inflate(R.menu.menu_b, menu);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            // TODO Auto-generated method stub
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            // TODO Auto-generated method stub
            //mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            //String item = getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID); //TODO lägg in texten
            mode.setTitle("Item " + mItem.getTitle() + " everybody!");
            return false;
        }
    }
}
