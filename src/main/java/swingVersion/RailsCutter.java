package swingVersion;


public class RailsCutter {

    private final Cut cut;

    public RailsCutter(Cut cut) {
        this.cut = cut;
    }


    private double odcinki_na_szyne;
    private double odpad_z_szyny;

    private double odpad_z_ostatniej_szyny;
    private double wior_z_ostatniej_szyny;

    private double zuzycie_szyn_w_szt;
    private double zuzycie_szyn_w_cm_na_odcinki;
    private double wior_w_cm;
    private double odpad_w_cm;

    public double round(double rounded) {
        return Math.round((rounded) * 100) / 100.0;
    }

    public void oblicz(Cut cut) {
        double cieta_szyna = cut.getRailLength();
        double odcinki_na_ostatnia_szyne;
        double wior_z_szyny = 0.0;
        odcinki_na_szyne = 0.0;
        odpad_z_szyny = 0.00;
        zuzycie_szyn_w_cm_na_odcinki = 0.0;
        zuzycie_szyn_w_szt = 0.00;
        odpad_w_cm = 0.0;
        wior_w_cm = 0.0;
        odpad_z_ostatniej_szyny = 0.0;
        wior_z_ostatniej_szyny = 0.0;


        do {
            cieta_szyna = round(cieta_szyna - cut.getSegmentLength() - cut.getSawWidth());

            odcinki_na_szyne++;

            if (cieta_szyna != 0.0) {
                wior_z_szyny = wior_z_szyny + cut.getSawWidth();
            }

        } while (cieta_szyna >= cut.getSegmentLength());

        odpad_z_szyny = cieta_szyna;
        odcinki_na_ostatnia_szyne = cut.getSegmentCount() % odcinki_na_szyne;

        if (odcinki_na_szyne != 0.0)
            zuzycie_szyn_w_cm_na_odcinki = 300 * cut.getSegmentCount() / odcinki_na_szyne;

        if (odcinki_na_ostatnia_szyne != 0.0) {
            wior_z_ostatniej_szyny = round(odcinki_na_ostatnia_szyne * cut.getSawWidth());
        }
        if (odcinki_na_ostatnia_szyne != 0.0) {

            odpad_z_ostatniej_szyny =  cut.getRailLength() - (odcinki_na_ostatnia_szyne * cut.getSegmentLength()) - wior_z_ostatniej_szyny;
        }


        odpad_w_cm = odpad_z_szyny * Math.floor(cut.getSegmentCount() / odcinki_na_szyne);
        wior_w_cm = wior_z_szyny * Math.floor(cut.getSegmentCount() / odcinki_na_szyne) + wior_z_ostatniej_szyny;
        zuzycie_szyn_w_cm_na_odcinki = round(cut.getSegmentCount() * cut.getSegmentLength() + odpad_w_cm + wior_w_cm);
        zuzycie_szyn_w_szt = round(zuzycie_szyn_w_cm_na_odcinki / 300.0);
    }


    public String getAnswer() {

        String answer = cut.getSegmentCount() + " szt. odcinków o długości " + cut.getSegmentLength() + " cm,\nSzerokość ostrza piły " + cut.getSawWidth() + " cm," + "\nodcinki na szyne " + odcinki_na_szyne + "\nodpad z szyny " + odpad_z_szyny + "\nodpad z ostatniej szyny " + odpad_z_ostatniej_szyny + "\nwiór z ostatniej szyny " + wior_z_ostatniej_szyny + "\nzuzycie szyn w szt " + zuzycie_szyn_w_szt + "\nzużycie szyn w cm " + zuzycie_szyn_w_cm_na_odcinki + "\nwiór w cm " + wior_w_cm + "\nodpad w cm " + odpad_w_cm;
        System.out.println(answer);
        return answer;
    }
}


