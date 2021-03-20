package swingVersion;

public class Cut {

    private double railLength, segmentLength, segmentCount, sawWidth;

    public double getRailLength() {
        return railLength;
    }

    public double getSegmentCount() {
        return segmentCount;
    }

    public double getSawWidth() {
        return (sawWidth);
    }

    public double getSegmentLength() {
        return segmentLength;
    }

    @Override
    public String toString() {

        StringBuilder b = new StringBuilder();

        b.append(segmentCount);
        b.append(" Ilość odcinków\n");
        b.append(railLength);
        b.append(" długość szyny\n");
        b.append(sawWidth);
        b.append(" szerokość ostrza piły\n");
        b.append(segmentLength);
        b.append(" długość ciętego odcinka\n");
        return b.substring(0);
    }

    void setRailLength(double railLength) {
        this.railLength = railLength;
    }

    void setSegmentLength(double segmentLength) {
        this.segmentLength = segmentLength;
    }

    void setSegmentCount(double segmentCount) {
        this.segmentCount = segmentCount;
    }

    void setSawWidth(double sawWidth) {
        this.sawWidth = sawWidth;
    }


}
