package com.example.s165158.aspiri.list_view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.s165158.aspiri.MainActivity;
import com.example.s165158.aspiri.R;
import com.example.s165158.aspiri.SubjectFragment;

/**
 * Created by s165158 on 08-11-2017.
 */

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {
    private Listener mListener;

    private String[] subjectListArray, subtextListArray;
    private Integer[] imageArray;
    private Context context;
    private AppCompatActivity mActivity;

    public RecyclerListAdapter(Context context,String[] subjectListArray, String[] subtextListArray, Integer[] imageArray) {
        this.subjectListArray = subjectListArray;
        this.subtextListArray = subtextListArray;
        this.imageArray = imageArray;
        this.context = context;


    }


    public interface Listener {
        void onClick(int position);
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

        TextView title = view.findViewById(R.id.listSubjectTitle);
        title.setText(subjectListArray[position]);
        TextView subtext = view.findViewById(R.id.listSubtext);
        subtext.setText(subtextListArray[position]);
        ImageView images = view.findViewById(R.id.listImageView);
        images.setImageResource(imageArray[position]);

        final SubjectFragment subjectFragment = new SubjectFragment();
        final SubListFragment subListFragment = new SubListFragment();
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(mListener != null){
//                 sætter on click listerner til at indekserer efter position i listen.
                    mListener.onClick(position);
                }


                FragmentManager manager = ((Activity)context).getFragmentManager();
//              bundle til at oveføre det indtrykkede index til ExpandableTextViewExample
                final Bundle bundle = new Bundle();
                bundle.putInt("listindex",position);
                bundle.putString("title",subjectListArray[position]);

//                Intent intent = new Intent(((Activity)context), DrawerActivityShowUp.class);
//                intent.putExtra("bundle",bundle);

//                              Hvis vi vil have forskellige layouts til forskellige emner, skal koden ligge her!
                subjectFragment.setArguments(bundle);
                subListFragment.setArguments(bundle);


                if (context instanceof Activity) {
                    mActivity = (AppCompatActivity) context;
                    int backstack = mActivity.getFragmentManager().getBackStackEntryCount();
                    if (backstack == 0) {
                        ((MainActivity) mActivity).setOldindex(position);
                        ((MainActivity) mActivity).replaceFragment(subListFragment, SubListFragment.TAG);
                    } else {
//                        bundle.putInt("oldindex", oldindex);
                        ((MainActivity) mActivity).replaceFragment(subjectFragment, SubjectFragment.TAG);
                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        try {
            return subjectListArray.length;
        } catch (ArrayIndexOutOfBoundsException exception) {
            Log.d("ArrayIndexOutOfBounds", "look at the switch-case in sublistfragment or listfragment");
            ((MainActivity) mActivity).showMessage("Error generating list-element. Contact appdeveloper");
            return 0;
        }



    }
}