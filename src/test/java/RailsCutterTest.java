import org.junit.Test;
import swingVersion.Cut;
import swingVersion.RailsCutter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class RailsCutterTest {

    private Cut cut;
    private RailsCutter railsCutter;

    @Test
    public void shouldReturnCorrectResponse() {
        cut = new Cut(300.00, 29.8, 10.0, 0.2);
        railsCutter = new RailsCutter(cut);assertThat(railsCutter.returnAnswer(), containsString("Zużycie szyn: 1.0szt., długość zużytych szyn 3.0m.,\n" +
                "Blachowkręty (6szt. na odcinek): 60.0szt., długość EPDM: 5.96m.\n" +
                "Ilość odcinków z jednej szyny: 10szt., odpad z jednej szyny 0.0cm.,\n" +
                "Odpad łącznie 0.0cm., straty z wiórów: 1.8cm.,\n" +
                " + odcinek nadający się do wykorzystania 0.0cm."));

    }

}
