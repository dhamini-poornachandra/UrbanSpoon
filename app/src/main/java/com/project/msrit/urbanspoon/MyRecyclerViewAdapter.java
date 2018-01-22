package com.project.msrit.urbanspoon;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by dhamini-poorna-chandra on 08/01/18.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    TableDatabaseHelper dbHelper;
    private List<Tables> mData = Collections.emptyList();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    Context context;
    GuestDatabaseHelper guestDbHelper;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<Tables> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        dbHelper = new TableDatabaseHelper(view.getContext());
        guestDbHelper = new GuestDatabaseHelper(view.getContext());
        return viewHolder;
    }

    // binds the data in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //set view data here

        String tableName = mData.get(position).getTableName();
        if (mData.get(position).getAvailability()) {
            holder.book.setAlpha(1f);
            holder.book.setEnabled(true);
            holder.unbook.setAlpha(0.5f);
            holder.unbook.setEnabled(false);
        } else {
            holder.unbook.setAlpha(1f);
            holder.unbook.setEnabled(true);
            holder.book.setAlpha(0.5f);
            holder.book.setEnabled(false);
        }
        holder.tableName.setText(tableName);

        holder.unbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(mData.get(position).getTableName(), "true");
                displayAll();
            }
        });

        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("I was called", mData.get(position).getTableName());
                displayAllGuests(position);
            }
        });

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tableName;
        Button book;
        Button unbook;

        ViewHolder(final View itemView) {
            super(itemView);
            tableName = itemView.findViewById(R.id.table_name);
            book = itemView.findViewById(R.id.book);
            unbook = itemView.findViewById(R.id.unbook_button);
//            itemView.setOnClickListener(this);
//            unbook.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void update(String tableName, String avalability) {
        if (dbHelper.update(tableName, avalability)) {
//            displayAll();
            notifyDataSetChanged();
        } else {
            Log.d("Error", "error");
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id).getTableName();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    private void displayAll() {
        GlobalVariable.getInstance().tablesList.clear();

        Cursor result = dbHelper.view_all();
        if (result.getCount() == 0) {
            Log.d("Error", "Nothing to show");
        } else {

            while (result.moveToNext()) {
                Tables table = new Tables();
                table.withTableName(result.getString(1));
                table.withAvaliablilty(Boolean.valueOf(result.getString(2)));
                GlobalVariable.getInstance().tablesList.add(table);

            }
        }
    }

    private void displayAllGuests(Integer pos) {

        Cursor result = guestDbHelper.view_all();

        if (result.getCount() == 0) {
            Toast.makeText(context, "No guests in queue", Toast.LENGTH_LONG).show();
        } else {

            Cursor nextGuest = guestDbHelper.retrieve_next_guest();

            while (nextGuest.moveToNext()) {
                if (context instanceof TableListActivity) {
                    GlobalVariable.getInstance().phoneNumber = nextGuest.getString(1);
                    GlobalVariable.getInstance().message = "Hi, " + nextGuest.getString(0) + "Your table is ready to be occupied";

                    ((TableListActivity) context).sendSMSMessage();
                }
                guestDbHelper.remove_guest_from_queue(nextGuest.getString(0));
                update(mData.get(pos).getTableName(), "false");
            }
        }

        displayAll();
        notifyDataSetChanged();
    }

}
