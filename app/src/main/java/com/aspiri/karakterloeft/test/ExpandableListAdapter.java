//package com.aspiri.karakterloeft.test;
//
//import android.content.Context;
//import android.graphics.Typeface;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.ExpandableListView;
//import android.widget.TextView;
//
//import com.aspiri.karakterloeft.R;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * Created by jonas on 21/11/2017.
// */
//
//public class ExpandableListAdapter extends BaseExpandableListAdapter {
//
//    private Context _context;
//    private List<String> _listDataHeader; // header titles
//    // child data in format of header title, child title
//    private HashMap<String, List<String>> _listDataChild;
//
//
//    //    FROM MAIN ACITIVTY
//    //For the expandable list
//    private ExpandableListAdapter listAdapter;
//    private ExpandableListView expandableListView;
//    private List<String> listDataHeader;
//    private HashMap<String, List<String>> listDataChild;
//
//    public ExpandableListAdapter(Context context, List<String> listDataHeader,
//                                 HashMap<String, List<String>> listChildData) {
//        this._context = context;
//        this._listDataHeader = listDataHeader;
//        this._listDataChild = listChildData;
//    }
//
//    @Override
//    public Object getChild(int groupPosition, int childPosititon) {
//        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
//                .get(childPosititon);
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, final int childPosition,
//                             boolean isLastChild, View convertView, ViewGroup parent) {
//
//        final String childText = (String) getChild(groupPosition, childPosition);
//
//        if (convertView == null) {
//            LayoutInflater infalInflater = (LayoutInflater) this._context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = infalInflater.inflate(R.layout.drawer_list_item, null);
//        }
//
//        TextView txtListChild = convertView
//                .findViewById(R.id.lblListItem);
//
//        txtListChild.setText(childText);
//        return convertView;
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
//                .size();
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return this._listDataHeader.get(groupPosition);
//    }
//
//    @Override
//    public int getGroupCount() {
//        return this._listDataHeader.size();
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded,
//                             View convertView, ViewGroup parent) {
//        String headerTitle = (String) getGroup(groupPosition);
//        if (convertView == null) {
//            LayoutInflater infalInflater = (LayoutInflater) this._context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = infalInflater.inflate(R.layout.drawer_list_group, null);
//        }
//
//        TextView lblListHeader = convertView
//                .findViewById(R.id.lblListHeader);
//        lblListHeader.setTypeface(null, Typeface.BOLD);
//        lblListHeader.setText(headerTitle);
//
//        return convertView;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }
//
//    //  From main activity
//    private void prepareListData() {
//        listDataHeader = new ArrayList<>();
//        listDataChild = new HashMap<String, List<String>>();
//
//        // Adding child data
//        listDataHeader.add("København");
//        listDataHeader.add("Odense");
//        listDataHeader.add("Aarhus");
//        listDataHeader.add("Testeri");
//
//        // Adding child data
//        List<String> København = new ArrayList<String>();
//        København.add("The Shawshank Redemption");
//        København.add("The Godfather");
//        København.add("The Godfather: Part II");
//        København.add("Pulp Fiction");
//        København.add("The Good, the Bad and the Ugly");
//        København.add("The Dark Knight");
//        København.add("12 Angry Men");
//
//        List<String> Odense = new ArrayList<String>();
//        Odense.add("The Conjuring");
//        Odense.add("Despicable Me 2");
//        Odense.add("Turbo");
//        Odense.add("Grown Ups 2");
//        Odense.add("Red 2");
//        Odense.add("The Wolverine");
//
//        List<String> Aarhus = new ArrayList<String>();
//        Aarhus.add("2 Guns");
//        Aarhus.add("The Smurfs 2");
//        Aarhus.add("The Spectacular Now");
//        Aarhus.add("The Canyons");
//        Aarhus.add("Europa Report");
//
//        List<String> Testeri = new ArrayList<String>();
//        Testeri.add("noget 1");
//        Testeri.add("noget 2");
//        Testeri.add("noget 3");
//        Testeri.add("noget 4");
//
//        // Header, Child data
//        listDataChild.put(listDataHeader.get(0), København);
//        listDataChild.put(listDataHeader.get(1), Odense);
//        listDataChild.put(listDataHeader.get(2), Aarhus);
//        listDataChild.put(listDataHeader.get(3), Testeri);
//
//
////        From MainActivity onCreate
//        // get the listview
////        expandableListView = (ExpandableListView) findViewById(R.id.lvExp);
//
//        // preparing list data
////        prepareListData();
//
////        Creating the list adapter from class
////        listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeader, listDataChild);
//
//        // setting list adapter
////        expandableListView.setAdapter(listAdapter);
//    }
//}