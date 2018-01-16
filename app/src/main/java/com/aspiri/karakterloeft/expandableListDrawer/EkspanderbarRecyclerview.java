package com.aspiri.karakterloeft.expandableListDrawer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspiri.karakterloeft.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


class LandeOgByerData {
    List<String> lande = Arrays.asList("Danmark", "Norge", "Sverige", "Island", "Færøerne", "Finland",
            "Frankrig", "Spanien", "Portugal", "Nepal", "Indien", "Kina", "Japan", "Thailand");

    List<List<String>> byer = Arrays.asList(
            Arrays.asList("København", "Århus", "Odense", "Aalborg", "Ballerup"),
            Arrays.asList("Oslo", "Trondheim"),
            Arrays.asList("Stockholm", "Malmø", "Lund"),
            Arrays.asList("Reykjavík", "Kópavogur", "Hafnarfjörður", "Dalvík"),
            Arrays.asList("Tórshavn", "Klaksvík", "Fuglafjørður"),
            Arrays.asList("Helsinki", "Espoo", "Tampere", "Vantaa"),
            Arrays.asList("Paris", "Lyon"),
            Arrays.asList("Madrid", "Barcelona", "Sevilla"),
            Arrays.asList("Lissabon", "Porto"),
            Arrays.asList("Kathmandu", "Bhaktapur"),
            Arrays.asList("Mumbai", "Delhi", "Bangalore"),
            Arrays.asList("Shanghai", "Zhengzhou"),
            Arrays.asList("Tokyo", "Osaka", "Hiroshima", "Kawasaki", "Yokohama"),
            Arrays.asList("Bankok", "Sura Thani", "Phuket"));
}

public class EkspanderbarRecyclerview extends AppCompatActivity {

    LandeOgByerData data = new LandeOgByerData();

    HashSet<Integer> åbneLande = new HashSet<>(); // hvilke lande der lige nu er åbne

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter = new RecyclerView.Adapter<EkspanderbartListeelemViewholder>() {

        @Override
        public int getItemCount() {
            return data.lande.size();
        }

        @Override
        public EkspanderbartListeelemViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            LinearLayout rodLayout = new LinearLayout(parent.getContext());
            rodLayout.setOrientation(LinearLayout.VERTICAL);
            EkspanderbartListeelemViewholder vh = new EkspanderbartListeelemViewholder(rodLayout);
            vh.rodLayout = rodLayout;
            vh.landeview = getLayoutInflater().inflate(R.layout.drawer_list_item, parent, false);
            vh.overskrift = vh.landeview.findViewById(R.id.lblListItem);
            vh.åbnLukBillede = vh.landeview.findViewById(R.id.imageView2);
            vh.landeview.setOnClickListener(vh);
//      vh.landeview.setBackgroundResource(?android:attr/listSelector); // giv visuelt feedback når der trykkes på baggrunden
            vh.åbnLukBillede.setOnClickListener(vh);
//      vh.åbnLukBillede.setBackgroundResource(android.R.drawable.btn_default);
            vh.rodLayout.addView(vh.landeview);
            return vh;
        }

        @Override
        public void onBindViewHolder(EkspanderbartListeelemViewholder vh, int position) {
            boolean åben = åbneLande.contains(position);
            vh.overskrift.setText(data.lande.get(position) + " åben=" + åben);
//      vh.beskrivelse.setText("Land nummer " + position + " på vh@"+Integer.toHexString(vh.hashCode()));

            if (!åben) {
                vh.åbnLukBillede.setImageResource(R.drawable.button_up_grey_256); // vis 'åbn' ikon
                for (View underview : vh.underviews)
                    underview.setVisibility(View.GONE); // skjul underelementer
            } else {
                vh.åbnLukBillede.setImageResource(R.drawable.button_down_grey_256); // vis 'luk' ikon

                List<String> byerILandet = data.byer.get(position);

                while (vh.underviews.size() < byerILandet.size()) { // sørg for at der er nok underviews
                    TextView underView = new TextView(vh.rodLayout.getContext());
                    underView.setPadding(0, 20, 0, 20);
                    underView.setBackgroundResource(android.R.drawable.list_selector_background);
                    underView.setOnClickListener(vh);      // lad viewholderen håndtere evt klik
                    underView.setId(vh.underviews.size()); // unik ID så vi senere kan se hvilket af underviewne der klikkes på
                    vh.rodLayout.addView(underView);
                    vh.underviews.add(underView);
                }

                for (int i = 0; i < vh.underviews.size(); i++) { // sæt underviews til at vise det rigtige indhold
                    TextView underView = vh.underviews.get(i);
                    if (i < byerILandet.size()) {
                        underView.setText(byerILandet.get(i));
                        underView.setVisibility(View.VISIBLE);
                    } else {
                        underView.setVisibility(View.GONE);      // for underviewet skal ikke bruges
                    }
                }
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        setContentView(recyclerView);

        // Understøttelse for skærmvending - kan evt udelades
        if (savedInstanceState != null) {
            åbneLande = (HashSet<Integer>) savedInstanceState.getSerializable("åbneLande");
            recyclerView.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable("liste"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) { // Understøttelse for skærmvending - kan evt udelades
        super.onSaveInstanceState(outState);
        outState.putSerializable("åbneLande", åbneLande);
        outState.putParcelable("liste", recyclerView.getLayoutManager().onSaveInstanceState());
    }

    /**
     * En Viewholder husker forskellige views i et listeelement, sådan at søgninger i viewhierakiet
     * med findViewById() kun behøver at ske EN gang.
     * Se https://developer.android.com/training/material/lists-cards.html
     */
    class EkspanderbartListeelemViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout rodLayout;
        TextView overskrift;
        //    TextView beskrivelse;
        ImageView åbnLukBillede;
        View landeview;
        ArrayList<TextView> underviews = new ArrayList<>();

        public EkspanderbartListeelemViewholder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            final int position = getAdapterPosition();

            if (v == åbnLukBillede || v == landeview) { // Klik på billede åbner/lukker for listen af byer i dette land
                boolean åben = åbneLande.contains(position);
                if (åben) åbneLande.remove(position); // luk
                else åbneLande.add(position); // åbn
                adapter.notifyItemChanged(position);
            } else {
                int id = v.getId();
                Toast.makeText(v.getContext(), "Klik på by nummer " + id + " i " + data.lande.get(position), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
