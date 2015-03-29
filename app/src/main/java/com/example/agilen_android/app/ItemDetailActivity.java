package com.example.agilen_android.app;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.app.ActionBarActivity;


/**
 * An activity representing a single Item detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link ItemDetailFragment}.
 */
public class ItemDetailActivity extends ActionBarActivity {

    private ActionMode mActionMode;
    private Item thisItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        //mActionMode = this.startActionMode(new ActionBarCallBack());

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            /*
            arguments.putString(ItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(ItemDetailFragment.ARG_ITEM_ID));*/
            arguments.putSerializable("item", getIntent().getSerializableExtra("item"));
            thisItem = (Item) getIntent().getSerializableExtra("item");
            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        try{
            switch(item.getItemId()){
                case android.R.id.home:
                    NavUtils.navigateUpTo(this, new Intent(this, ItemListActivity.class));
                    break;
                case R.id.delete:

                    Intent intent = new Intent(this, ItemListActivity.class);
                    intent.putExtra("delete_item", thisItem);
                    Log.d("agil ********", "trying to delete " + thisItem.getTitle());
                    startActivity(intent);
                    // TODO Delete
                    /*
                    ItemListFragment f = (ItemListFragment) getFragmentManager().findFragmentById(R.id.item_list);
                    f.addItem();*/
                    break;
            }
        } catch(Exception e){
            Log.i("Error ", e.toString());
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_b, menu);
        return true;
    }
}
