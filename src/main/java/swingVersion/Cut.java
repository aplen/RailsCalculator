package swingVersion;

public class Cut {

    private double railLength, segmentLength, segmentCount, sawWidth;

    public double getRailLength() {
	return railLength;
    }

    public void setRailLength(double railLength) throws IllegalArgumentException {
	if (railLength <= 0) {
	    throw new IllegalArgumentException();
	} else {
	    this.railLength = railLength;
	}

    }
    public void setSegmentLength(double segmentLength) throws IllegalArgumentException {
	if (segmentLength <= 0) {
	    throw new IllegalArgumentException();
	} else {
	    this.segmentLength = segmentLength;
	}
    }

    public double getSegmentCount() {
	return segmentCount;
    }

    public void setSegmentCount(double segmentCount) throws IllegalArgumentException {
	if (segmentCount <= 0) {
	    throw new IllegalArgumentException();
	} else {
	    
		this.segmentCount = segmentCount;
	}
    }

    public double getSawWidth() {
	return (sawWidth);
    }

    public void setSawWidth(double sawWidth) throws IllegalArgumentException {
	if (sawWidth <= 0) {
	    throw new IllegalArgumentException();
	} else {
	    this.sawWidth = sawWidth;
	}
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

	public double getSegmentLength() {
		return segmentLength;
	}
}
