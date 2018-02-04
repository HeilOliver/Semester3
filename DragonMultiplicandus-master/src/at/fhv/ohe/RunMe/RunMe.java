package at.fhv.ohe.RunMe;

import at.fhv.ohe.multiplicandus.Multiplicandus;
import at.fhv.ohe.multiplicandus.map.TheDeadValley;
import at.fhv.ohe.multiplicandus.princess.Klothilde;
import at.fhv.ohe.multiplicandus.princess.Klotmilde;
import at.fhv.ohe.multiplicandus.princess.Klotwilde;

public class RunMe {

    public static void main(String[] args) {
        Multiplicandus multiplicandus = new Multiplicandus(new TheDeadValley());

        System.out.println(multiplicandus.findTheWayOfGlory(new Klothilde()));
        System.out.println(multiplicandus.findTheWayOfGlory(new Klotmilde()));
        System.out.println(multiplicandus.findTheWayOfGlory(new Klotwilde()));
    }
}
