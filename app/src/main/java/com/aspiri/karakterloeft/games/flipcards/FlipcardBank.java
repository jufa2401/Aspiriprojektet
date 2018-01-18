package com.aspiri.karakterloeft.games.flipcards;

/**
 * Created by Justin on 10/01/2018.
 */

import android.content.Context;
import android.util.Log;

import com.aspiri.karakterloeft.games.ourDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class FlipcardBank {

    // declare list of Question objects
    List<Flipcard> list = new ArrayList<>();
    ourDatabaseHelper myDataBaseHelper;

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
        myDataBaseHelper = new ourDatabaseHelper(context);
        list = myDataBaseHelper.getAllFlipcardsList();//get questions/choices/answers from database
        String q1 = "$$ y=a \\cdot x+b $$";

        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            myDataBaseHelper.addInitialFlipcard(new Flipcard("Ret linje", "Hvad er formlen for en ret linje",
                    q1,"Ovenstående skal laves om til regulær tekst, ellers skal titlen på flipcardet ændres til mathview", "RET LINJE PHOTO"));
// CIRKEL
            myDataBaseHelper.addInitialFlipcard(new Flipcard("Cirkel - Areal", "Hvordan beregnes arealet for en cirkel?",
                    "$$A = r^2*\\Pi$$", "arealet af cirkel beregnes ved radius i anden, ganget med Pi", "Cirkel areal photo"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Cirkel - Radius", "Hvordan beregnes radius af en cirkel?", "$$r = \\sqrt(\\frac{A}{\\pi})$$", "Radius er lig med kvadratroden af arealet divideret med pi",  "Cirkel radius PHOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Cirkel - Omkreds", "Hvordan findes omkredsen af en cirkel?", "$$O = d \\cdot \\pi$$","Omkredsen findes ved at gange pi med diameteren" , "Cirkel omkreds PHOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Cirkel - Diameter", "Hvordan beregnes diameteren af en cirkel?", "$$d = 2\\cdot r = \\frac{O}{\\pi}i$$", "En cirkels diameter kan findes på to måder: to gange radius eller omkredsen divideret med pi", "Cirkel diameter PHOTO"));
//TREKANTER
            myDataBaseHelper.addInitialFlipcard(new Flipcard("Trekant - Pythagoras", "Hvad er Pythagoras formel, og hvordan anvendes denne?", "Formlen er $$ a^{2} + b^{2} = c^{2} $$", "Anden potensen af sidderne a og b i en trekant giver c i anden potens, denne kan dernæst kvadraeres for at finde længden på C", "PYTHAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Trekant - Areal", "Hvordan findes arealet af en vilkårlig trekant?", "$$A = h \\cdot G \\cdot \\frac{1}{2}$$", "Højde gange grundlinien halveret", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Trekant - Grundlinie", "Hvordan findes grundlinien for en vilkårlig trekant?", "$$g = \\frac{2\\cdot A}{h}$$", "Grundlinien findes ved to gange arealet divideret med højden", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Trekant - Højden", "Hvordan findes højden for en vilkårlig trekant?", "$$h=2 \\cdot\\frac{A}{g}$$", "Højden findes ved to gange areal divideret med grundlinien", "PHYTAGORAS FOTO"));
//PARALLELOGRAMMER
            myDataBaseHelper.addInitialFlipcard(new Flipcard("Parallelogram - Areal", "Hvordan findes arealet af et parallelogram", "$$A=h \\cdot g $$", "Arealet findes ved højden grange grundlinien", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Parallelogram - Højde", "Hvordan findes højden af et parallelogram", "$$h=\\frac{A}{g}$$", "Højden findes ved at dele Arealet med længden af grundlinien", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Parallelogram - Grundlinien", "Hvordan findes grundlinien af et parallelogram", "$$g=\\frac{A}{h}$$", "Grundlinien findes ved at dividere Arealet med højden", "PHYTAGORAS FOTO"));
//TRAPEZ
            myDataBaseHelper.addInitialFlipcard(new Flipcard("Trapez - Areal", "Hvordan findes Arealet af et trapez", "$$A=\\frac{1}{2}\\cdot h \\cdot ( a + b ) $$", "Arealet findes ved at ligge a og b siderne sammen og gange med den halve højde ", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Trapez - højden", "Hvordan findes højden af et trapez", "$$h=\\frac{A}{0,5 \\cdot ( a + b )}$$", " højden findes ved at dividere A med halvdelen af summen af a og b ", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Trapez - a", "Hvordan findes variablen a fra et trapez", "$$a=\\frac{A - 0,5 \\cdot h \\cdot b}{0,5 \\cdot h }$$", "a findes ved at trække produktet af halvdelen af højden gange b fra Arealet og dele med den halve højde", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Trapez - b", "Hvordan findes variablen b af et trapez", "$$b=\\frac{A - 0,5 \\cdot h \\cdot a}{0,5 \\cdot h }$$", "b findes ved at trække produktet af halvdelen af højden gange a fra Arealet og dele med den halve højde", "PHYTAGORAS FOTO"));
//KEGLE
            myDataBaseHelper.addInitialFlipcard(new Flipcard("Kegle - rumfang", "Hvordan findes rumfanget af en kegle", "$$ V = \\frac{1}{3}\\pi \\cdot r^{2} \\cdot h$$", "Rumfanget findes ved at gange pi med 1/3 gange r i anden gange h", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Kegle - Overfladeareal 1", "Hvordan findes overfladeareal 1 på en kegle", "$$ O =\\pi \\cdot r \\cdot \\sqrt( r^{2} + h^{2})$$", "Overfladerareal 1 findes ved at gange pi og r med kvadratet af summen af r i anden og h i anden ", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Kegle - Overfladeareal 2", "Hvordan findes overfladeareal 2 på en kegle", "$$ O = \\pi \\cdot r \\cdot s $$", "Overfladearealet 2  findes ved at  gange pi me r og s", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Kegle - sidelinien", "Hvordan findes sidelinien", "$$s = \\sqrt(r^{2} \\cdot h^{2})$$", "Sidelinien findes ved at kvadrere summen af r i anden og h i anden", "PHYTAGORAS FOTO"));
//KUGLE
            myDataBaseHelper.addInitialFlipcard(new Flipcard("Kugle - Overfladeareal", "Hvordan findes overfladearealet af en kugle", "$$V = \\frac{3}{4} \\cdot \\pi \\cdot r^{3} $$", "Volumen findes ved at tage tre fjerdedele pi og gange med r i tredie", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Kugle - Rumfang", "Hvordan findes rumfanget af en kugle", "$$O = 4 \\cdot \\pi \\cdot r^{2}$$", "Omfanget findes ved at gane 4 med pi r i anden", "PHYTAGORAS FOTO"));
//CYLINDER
            myDataBaseHelper.addInitialFlipcard(new Flipcard("Cylinder - Rumfang", "Hvordan findes rumfanget af en cylinder", "$$V = \\pi \\cdot r^{2} \\cdot h$$", "Volumen findes ved at gange pi med r i anden og h", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Cylinder - Overfladeareal", "Hvordan findes overfladearealet af en cylinder", "$$O = 2 \\cdot \\pi \\cdot r^{2}$$", "Omkredsen er produktet 2 gange pi  gange r gange  h", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Cylinder - Højde med O og r", "Hvordan findes højde med O og V", "$$h = \\frac{O}{2 \\cdot \\pi \\cdot r}$$", "højden findes ved at dele  O med 2 gange pi gange r", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Cylinder - Højde med V og  r", "Hvordan findes Højde med V og r", "$$h = \\frac{v}{\\pi \\cdot r^{2}}$$", "højden findes ved at dele  v med pi gange r i anden", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Cylinder - Radius med O og h", "Hvordan findes Radius med O og h", "$$r = \\frac{O}{2 \\cdot \\pi \\cdot h}$$", "radius findes ved at dividere O med 2gange pi gange h ", "PHYTAGORAS FOTO"));

            myDataBaseHelper.addInitialFlipcard(new Flipcard("Cylinder - Radius med V og h", "Hvordan findes Radius med V og h", "$$r = \\sqrt(\\frac{V}{\\pi \\cdot h})$$", "radius findes ved at kvadrere  Volumen delt med pi gange h", "PHYTAGORAS FOTO"));

            list = myDataBaseHelper.getAllFlipcardsList();//get list from database again
            Log.d("Init database", list.toString());

        }
    }


}