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
    private double railsUsagePerSegmentsInCentimeters;
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

        if (cut.getSegmentLength() < cut.getRailLength() && cut.getRailLength() > 0.00 && cut.getSegmentLength() > 0.00 && cut.getSawWidth() > 0.00 && cut.getSegmentCount() > 0.00) {
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

        StringBuilder builder = new StringBuilder();

        builder.append("Długość szyny ");
        builder.append(cut.getRailLength());
        builder.append("cm, ");
        builder.append(cut.getSegmentCount());
        builder.append(" szt. odcinków o długości ");
        builder.append(cut.getSegmentLength());
        builder.append(" cm, Szerokość ostrza piły ");
        builder.append(cut.getSawWidth());
        builder.append("cm,  odcinki na szyne ");
        builder.append(segmentsPerOneRail);
        builder.append("\nodpad z szyny ");
        builder.append(wastedSegmentOfRailRemain);
        builder.append(", odpad z ostatniej szyny ");
        builder.append(wastedSegmentOfLastRailRemain);
        builder.append(", wiór z ostatniej szyny ");
        builder.append(sawDustFromLastRail);
        builder.append("\n zużycie szyn w szt ");
        builder.append(railsUsage);
        builder.append(", zużycie szyn ");
        builder.append(railsUsagePerSegmentsInCentimeters);
        builder.append("cm, wiór ");
        builder.append(sawDustInCentimeters);
        builder.append("cm, odpad");
        builder.append(wasteInCentimeters);
        builder.append("cm,\nilość blachowkrętów: ");
        builder.append(screwsNrCount());
        builder.append(", długość EPDM w metrach: ");
        builder.append(epdmCount());

        String answer = builder.substring(0);
        return answer;

    }
}