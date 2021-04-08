/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHero {

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        synthesizer.GuitarString[] CONCERTS = new synthesizer.GuitarString[37];
        for (int i = 0; i < 37; i = i + 1) {
            CONCERTS[i] = new synthesizer.GuitarString(440.0 * Math.pow(2,
                    ((double) i - 24.0) / 12.0));
        }
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                int index = keyboard.indexOf(StdDraw.nextKeyTyped());
                if (index >= 0) {
                    CONCERTS[index].pluck();
                }
            }
            /* compute the superposition of samples */
            double sample = 0;
            for (synthesizer.GuitarString c : CONCERTS) {
                sample = sample + c.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (synthesizer.GuitarString c : CONCERTS) {
                c.tic();
            }
        }
    }
}
