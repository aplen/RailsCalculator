package swingVersion;


public class RailsCutter {

    private final Cut cut;

    public RailsCutter(Cut cut) {
        this.cut = cut;
    }

    private int segmentsPerOneRail;
    private double wastedSegmentOfRailRemain;
    private double wastedSegmentOfLastRailRemain;
    private double sawDustFromLastRail;
    private double railsUsage;
    private double totalRailsUsageInMeters;
    private double sawDustInCentimeters;
    private double wasteInCentimeters;
    private double sawDustFromOneRail;
    private double segmentsPerLastRail;
    private int counter;

    private double round(double rounded) {
        return Math.round((rounded) * 100) / 100.0;
    }

    private double screwsNrCount() {
        return cut.getSegmentCount() * 6;
    }

    private double epdmCount() {
        return round(cut.getSegmentLength() * 2.0 * cut.getSegmentCount() / 100.00);
    }

    public boolean isSegmentShorterThanRailAndNoZeroValues() {

        return cut.getSegmentLength() < cut.getRailLength() && cut.getRailLength() > 0.00 && cut.getSegmentLength() > 0.00 && cut.getSawWidth() >= 0.00 && cut.getSegmentCount() > 0.00;

    }

    private void wastedSegmentOfRailRemainCount() {
        counter = 0;
        sawDustFromOneRail = 0.0;
        wastedSegmentOfRailRemain = cut.getRailLength();

        do {

            wastedSegmentOfRailRemain = round(wastedSegmentOfRailRemain - cut.getSegmentLength() - cut.getSawWidth());
            counter++;

            if (wastedSegmentOfRailRemain != 0.0
            ) {
                sawDustFromOneRail = round(sawDustFromOneRail + cut.getSawWidth());
            }

        } while (wastedSegmentOfRailRemain-cut.getSawWidth() >= cut.getSegmentLength());

    }

    private void segmentsPerOneRailCount() {
        segmentsPerOneRail = counter;
    }

    private void segmentsPerLastRailCount() {
        segmentsPerLastRail = cut.getSegmentCount() % segmentsPerOneRail;
    }

    private void sawDustFromLastRailCount() {
        sawDustFromLastRail = 0.00;
        if (segmentsPerLastRail != 0.0) {
            sawDustFromLastRail = round(segmentsPerLastRail * cut.getSawWidth());
        }
    }

    private void wastedSegmentsOfLastRailRemainCount() {
        wastedSegmentOfLastRailRemain = 0.0;
        if (segmentsPerLastRail != 0.0) {

            wastedSegmentOfLastRailRemain = round(cut.getRailLength() - (segmentsPerLastRail * cut.getSegmentLength()) - sawDustFromLastRail);
        }

    }

    private void wasteInCentimetersCount() {
        wasteInCentimeters = round(wastedSegmentOfRailRemain * Math.floor(cut.getSegmentCount() / segmentsPerOneRail));
    }

    private void sawDustInCentimetersCount() {
        sawDustInCentimeters = round(sawDustFromOneRail * Math.floor(cut.getSegmentCount() / segmentsPerOneRail) + sawDustFromLastRail);
    }

    private void totalRailsUsageInMetersCount() {
        totalRailsUsageInMeters = round((cut.getSegmentCount() * cut.getSegmentLength() + wasteInCentimeters + sawDustInCentimeters)/100.00);
    }

    private void railsUsageCount() {
        railsUsage = round((totalRailsUsageInMeters*100.00) / cut.getRailLength());

    }


    public String returnAnswer() {
        wastedSegmentOfRailRemainCount();
        segmentsPerOneRailCount();
        segmentsPerLastRailCount();
        sawDustFromLastRailCount();
        wastedSegmentsOfLastRailRemainCount();
        wasteInCentimetersCount();
        sawDustInCentimetersCount();
        totalRailsUsageInMetersCount();
        railsUsageCount();

        StringBuilder builder = new StringBuilder();


        builder.append("Zu??ycie szyn: ");
        builder.append(railsUsage);
        builder.append("szt., d??ugo???? zu??ytych szyn ");
        builder.append(totalRailsUsageInMeters);
        builder.append("m.,\nBlachowkr??ty (6szt. na odcinek): ");
        builder.append(screwsNrCount());
        builder.append("szt., d??ugo???? EPDM: ");
        builder.append(epdmCount());
        builder.append("m.");
        builder.append("\nIlo???? odcink??w z jednej szyny: ");
        builder.append(segmentsPerOneRail);
        builder.append("szt., odpad z jednej szyny ");
        builder.append(wastedSegmentOfRailRemain);
        builder.append("cm.,\nOdpad ????cznie ");
        builder.append(wasteInCentimeters);
        builder.append("cm., straty z wi??r??w: ");
        builder.append(sawDustInCentimeters);
        builder.append("cm.,\n + odcinek nadaj??cy si?? do wykorzystania ");
        builder.append(wastedSegmentOfLastRailRemain);
        builder.append("cm.");
        return builder.substring(0);

    }
}