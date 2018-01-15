package com.aspiri.karakterloeft.games;

/**
 * Created by Justin on 10/01/2018.
 */

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FlipcardBank {

    // declare list of Question objects
    List<Flipcard> list = new ArrayList<>();
    MultipleChoiceDataBaseHelper myDataBaseHelper;

    // method returns number of questions in list
    public int getLength() {
        return list.size();
    }

    public String getCategory(int a) {
        return list.get(a).getCategory();
    }
    // method returns question from list based on list index
    public String getFront(int a) { return list.get(a).getFront(); }

    public String getBack(int a){return list.get(a).getBack();}

    public String getBackExplanation (int a){return list.get(a).getBackExplanation();}
    // method return a single multiple choice item for question based on list index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4
    // as an argument
    public String getPhoto(int a){return list.get(a).getPhoto();}


    public void initFlipcards(Context context) {
        myDataBaseHelper = new MultipleChoiceDataBaseHelper(context);
        list = myDataBaseHelper.getAllFlipcardsList();//get questions/choices/answers from database
        String q1 = "$$ y=a \\cdot x+b $$";

        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            myDataBaseHelper.addInitialFlipcard(new Flipcard("Ret linje", "Hvad er formlen for en ret linje",
                    q1,"Ovenstående skal laves om til regulær tekst, ellers skal titlen på flipcardet ændres til mathview", "RET LINJE PHOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Cirkel - Areal", "Hvordan beregnes arealet for en cirkel?",
                    "A = R^2*Pi", "arealet af cirkel beregnes ved radius i anden, ganget med Pi", "Cirkel areal photo"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Cirkel - Radius", "Hvordan beregnes radius af en cirkel?", "r = sqrt(A/Pi)", "Radius er lig med kvadratroden af arealet divideret med pi",  "Cirkel radius PHOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Cirkel - Omkreds", "Hvordan findes omkredsen af en cirkel?", "O = d*Pi","Omkredsen findes ved at gange pi med diameteren" , "Cirkel omkreds PHOTO"));

            myDataBaseHelper.addDataFlipCard(new Flipcard("Cirkel - Diameter", "Hvordan beregnes diameteren af en cirkel?", "d = 2r = O/Pi", "En cirkels diameter kan findes på to måder: to gange radius eller omkredsen divideret med pi", "Cirkel diameter PHOTO"));

            myDataBaseHelper.addDataFlipCard(new Flipcard("Trekant - Pythagoras", "Hvad er Pythagoras formel, og hvordan anvendes denne?","Formlen er a^2 + b^2 = c^2", "Anden potensen af sidderne a og b i en trekant giver c i anden potens, denne kan dernæst kvadraeres for at finde længden på C","PYTHAGORAS FOTO"));

            myDataBaseHelper.addDataFlipCard(new Flipcard("Trekant - Areal", "Hvordan findes arealet af en vilkårlig trekant?","Formlen er H*G*1/2","Højde gange grundlinien halveret", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addDataFlipCard(new Flipcard("Trekant - Grundlinie", "Hvordan findes grundlinien for en vilkårlig trekant?","2*A/H", "Grundlinien findes ved to gange arealet divideret med højden","PHYTAGORAS FOTO"));

            myDataBaseHelper.addDataFlipCard(new Flipcard("Trekant - Højden", "Hvordan findes højden for en vilkårlig trekant?","2*A/G","Højden findes ved to gange areal divideret med grundlinien","PHYTAGORAS FOTO"));

            myDataBaseHelper.addDataFlipCard(new Flipcard("Parallelogram - Areal", "Hvordan findes arealet af et parallelogram","H*G", "Arealet findes ved højden grange grundlinien","PHYTAGORAS FOTO"));

            list = myDataBaseHelper.getAllFlipcardsList();//get list from database again
            Log.d("Init database", list.toString());

        }
    }


}