package com.example.s165158.aspiri.test2;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.s165158.aspiri.R;
import com.example.s165158.aspiri.test.SubjectFragment;

/**
 * Created by s165158 on 08-11-2017.
 */

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {
    private Listener mListener;

    private String[] subjectListArray;
    private String[] subtextListArray;
    private Integer[] imageArray;
    private Context context;
    public RecyclerListAdapter(Context context,String[] subjectListArray, String[] subtextListArray, Integer[] imageArray) {
        this.subjectListArray = subjectListArray;
        this.subtextListArray = subtextListArray;
        this.imageArray = imageArray;
        this.context = context;

    }


    public static interface Listener{
        public void onClick(int position);
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view =  itemView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_single_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        View view = holder.view;
        TextView title = (TextView) view.findViewById(R.id.nameTextViewID);
        final SubjectFragment subjectFragment = new SubjectFragment();
        title.setText(subjectListArray[position]);
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(mListener != null){
//                 sætter on click listerner til at indekserer efter position i listen.
                    mListener.onClick(position);
                }
                FragmentManager manager = ((Activity)context).getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.fragmentindhold,subjectFragment).commit();


            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectListArray.length;
    }
}