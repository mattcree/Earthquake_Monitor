/**
 * Created by Cree on 03/11/2016.
 */
public class Barry {
    public static void main(String[] args) {
        String barry = "Print this out one character at a time please.";
        String barry2 = "Print this out one character at a time please.";
        String barry3 = "Print this out one character at a time please.";

        marquee(barry);
        marquee(barry2);
        marquee(barry3);

    }

    private static void marquee (String string) {
        int length = string.length();
        for(int i = 0; i < length; i++) {
            System.out.print(string.charAt(i));
            try {
                Thread.sleep(10);
            } catch (Exception ex) {}
        }

    }

}
