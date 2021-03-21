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

        if (cut.getSegmentLength() < cut.getRailLength() && cut.getRailLength() > 0.00 && cut.getSegmentLength() > 0.00 && cut.getSawWidth() >= 0.00 && cut.getSegmentCount() > 0.00) {
            return true;
        } else
            return false;

    }

    private double wastedSegmentOfRailRemainCount() {
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

    private double totalRailsUsageInMetersCount() {
        totalRailsUsageInMeters = round((cut.getSegmentCount() * cut.getSegmentLength() + wasteInCentimeters + sawDustInCentimeters)/100.00);
        return totalRailsUsageInMeters;
    }

    private double railsUsageCount() {
        railsUsage = round((totalRailsUsageInMeters*100.00) / cut.getRailLength());

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
        totalRailsUsageInMetersCount();
        railsUsageCount();

        StringBuilder builder = new StringBuilder();


        builder.append("Zużycie szyn: ");
        builder.append(railsUsage);
        builder.append("szt., długość zużytych szyn ");
        builder.append(totalRailsUsageInMeters);
        builder.append("m.,\nBlachowkręty (6szt. na odcinek): ");
        builder.append(screwsNrCount());
        builder.append("szt., długość EPDM: ");
        builder.append(epdmCount());
        builder.append("m.");
        builder.append("\nIlość odcinków z jednej szyny: ");
        builder.append(segmentsPerOneRail);
        builder.append("szt., odpad z jednej szyny ");
        builder.append(wastedSegmentOfRailRemain);
        builder.append("cm.,\nOdpad łącznie ");
        builder.append(wasteInCentimeters);
        builder.append("cm., straty z wiórów: ");
        builder.append(sawDustInCentimeters);
        builder.append("cm.,\n + odcinek nadający się do wykorzystania ");
        builder.append(wastedSegmentOfLastRailRemain);
        builder.append("cm.");
        String answer = builder.substring(0);
        return answer;

    }
}