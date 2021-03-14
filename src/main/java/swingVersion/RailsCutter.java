package swingVersion;


public class RailsCutter {

    private final Cut cut;

    public RailsCutter(Cut cut) {
        this.cut = cut;
    }

    private double segmentsPerOneRail;
    private double wastedSegmentOfRailRemain;
    private double wastedSegmentOfLastRailRemain;
    private double sawDustFromLastRail;
    private double railsUsage;
    private double railsUsagePerSegmentsInCentimeters;
    private double sawDustInCentimeters;
    private double wasteInCentimeters;
    private double sawDustFromOneRail;
    private double segmentsPerLastRail;
    private double counter;

    private double round(double rounded) {
        return Math.round((rounded) * 100) / 100.0;
    }

    private double screwsNrCount() {
        return cut.getSegmentCount() * 6;
    }

    private double epdmCount() {
        return round(cut.getSegmentLength() * 2.0 * cut.getSegmentCount() / 100.00);
    }

    private boolean isSegmentShorterThanRailAndNoZeroValues() {

        if (cut.getSegmentLength() < cut.getRailLength()&&cut.getRailLength()>0.00&&cut.getSegmentLength()>0.00&&cut.getSawWidth()>0.00&&cut.getSegmentCount()>0.00) {
            return true;
        } else
            return false;

    }

    private double wastedSegmentOfRailRemainCount() {
        counter = 0.0;
        sawDustFromOneRail=0.0;
        wastedSegmentOfRailRemain = cut.getRailLength();

        if (isSegmentShorterThanRailAndNoZeroValues())

            do {

                wastedSegmentOfRailRemain = round(wastedSegmentOfRailRemain - cut.getSegmentLength() - cut.getSawWidth());
                counter++;

                if (wastedSegmentOfRailRemain != 0.0
                ) {
                    sawDustFromOneRail = sawDustFromOneRail + cut.getSawWidth();
                }

            } while (wastedSegmentOfRailRemain >= cut.getSegmentLength());

        return wastedSegmentOfRailRemain;
    }

    private double segmentsPerOneRailCount() {
        segmentsPerOneRail = counter;
        return segmentsPerOneRail;
    }

    private double segmentsPerLastRailCount() {
        segmentsPerLastRail = cut.getSegmentCount() % segmentsPerOneRail;
        return segmentsPerLastRail;
    }

    private double sawDustFromLastRailCount() {
        sawDustFromLastRail = 0.00;
        if (segmentsPerLastRail != 0.0) {
            sawDustFromLastRail = round(segmentsPerLastRail * cut.getSawWidth());
        }
        return sawDustFromLastRail;
    }

    private double wastedSegmentsOfLastRailRemainCount() {
        wastedSegmentOfLastRailRemain = 0.0;
        if (segmentsPerLastRail != 0.0) {

            wastedSegmentOfLastRailRemain = round(cut.getRailLength() - (segmentsPerLastRail * cut.getSegmentLength()) - sawDustFromLastRail);
        }

        return wastedSegmentOfLastRailRemain;
    }

    private double wasteInCentimetersCount() {
        wasteInCentimeters = round(wastedSegmentOfRailRemain * Math.floor(cut.getSegmentCount() / segmentsPerOneRail));
        return wasteInCentimeters;
    }

    private double sawDustInCentimetersCount() {
        sawDustInCentimeters = round(sawDustFromOneRail * Math.floor(cut.getSegmentCount() / segmentsPerOneRail) + sawDustFromLastRail);
        return sawDustInCentimeters;
    }

    private double railsUsagePerSegmentsInCentimetersCount() {
        railsUsagePerSegmentsInCentimeters = round(cut.getSegmentCount() * cut.getSegmentLength() + wasteInCentimeters + sawDustInCentimeters);
        return railsUsagePerSegmentsInCentimeters;
    }

    private double railsUsageCount() {
        railsUsage = round(railsUsagePerSegmentsInCentimeters / cut.getRailLength());

        return railsUsage;
    }


    public String returnAnswer() {
wastedSegmentOfRailRemainCount();
segmentsPerOneRailCount();
segmentsPerLastRailCount();
sawDustFromLastRailCount();
wastedSegmentsOfLastRailRemainCount();
wasteInCentimetersCount();
sawDustInCentimetersCount();
railsUsagePerSegmentsInCentimetersCount();
railsUsageCount();

        String answer = cut.getSegmentCount() + " szt. odcinków o długości " + cut.getSegmentLength() + " cm, Szerokość ostrza piły " + cut.getSawWidth() + " cm," + " odcinki na szyne " + segmentsPerOneRail + "\nodpad z szyny " + wastedSegmentOfRailRemain + "\nodpad z ostatniej szyny " + wastedSegmentOfLastRailRemain + "\nwiór z ostatniej szyny " + sawDustFromLastRail + "\nzuzycie szyn w szt " + railsUsage + "\nzużycie szyn w cm " + railsUsagePerSegmentsInCentimeters + "\nwiór w cm " + sawDustInCentimeters + "\nodpad w cm " + wasteInCentimeters + "\nilość blachowkrętów: " + screwsNrCount() + "\ndługość EPDM w m: " + epdmCount();
        System.out.println(answer);
        return answer;

    }
}