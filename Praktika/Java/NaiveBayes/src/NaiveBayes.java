import java.util.*;
import java.util.function.BiConsumer;

/**
 * @author Kay Förster
 */
public class NaiveBayes {
    private HashMap<String, Integer> countClassItems = new HashMap<>();
    private HashMap<String, HashMap<String, Integer>> countItems = new HashMap<>();
    private HashMap<String, Integer> countClasses = new HashMap<>();

    /**
     * Trainiert / erfasst Beispiele für eine jeweilige Sprache
     *
     * @param sample Lerntext
     * @param klasse Sprache des Lerntextes
     */
    private void train(String sample, String klasse) {
        // Anzahl Klassen zählen
        Integer countClass = countClasses.getOrDefault(klasse, 0) + 1;
        countClasses.put(klasse, countClass);

        // Wörter für die jeweilige Klasse erfassen
        HashMap<String, Integer> countWords = countItems.getOrDefault(klasse, new HashMap<>());

        // Einzelne Wörter durchgehen
        String[] woerter = prepareString(sample);
        for (String wort : woerter) {
            // Häufigkeit der Wörter
            Integer countWord = countWords.getOrDefault(wort, 0) + 1;
            countWords.put(wort, countWord);

            // Anzahl Elemente in der Klasse erhöhen
            Integer count = countClassItems.getOrDefault(klasse, 0) + 1;
            countClassItems.put(klasse, count);
        }

        countItems.put(klasse, countWords);
    }

    private void classify(String sample) {
        HashMap<String, Double> result = new HashMap<>(countClasses.size());

        for (String klasse : countClasses.keySet())
            result.put(klasse, probs(klasse, prepareString(sample)));

        // Alle Ergebnisse ausgenen
        System.out.println(result);
    }

    /**
     * Berechnet P(x|c)
     *
     * @param klasse in welcher das Wort enthalten sein soll
     * @param word   Wort nach welchem gesucht wird
     * @return Wahrscheinlichkeit für ein Wort in der Klasse
     */
    private double prob(String klasse, String word) {
        HashMap<String, Integer> countWords = countItems.getOrDefault(klasse, new HashMap<>());

        return Math.log(countWords.getOrDefault(word, 1).doubleValue() / countClassItems.get(klasse).doubleValue());
    }

    /**
     * Berechnet P(c)
     *
     * @param klasse für welche die Wahrscheinlichkeit berechnet werden soll
     * @return Wahrscheinlichkeit mit welcher die Klasse auftritt
     */
    private double probC(String klasse) {
        Integer sum = 0;
        for (Integer integer : countClasses.values())
            sum += integer;

        return Math.log(countClasses.get(klasse).doubleValue() / sum.doubleValue());
    }

    private double probs(String klasse, String[] sample) {
        double sum = 0;

        for (String wort : sample)
            sum += prob(klasse, wort);

        return probC(klasse) + sum;
    }

    private static String[] prepareString(String s) {
        s = s.replaceAll("\\.", "");
        s = s.replaceAll(",", "");
        s = s.replaceAll("-", " ");

        return s.toLowerCase().trim().split(" ");
    }

    public static void main(String[] args) {
        // Beispiel Texte
        HashMap<String, String> examples = new HashMap<>();
        examples.put("de", "Weit hinten, hinter den Wortbergen, fern der Länder Vokalien und Konsonantien leben die Blindtexte. Abgeschieden wohnen Sie in Buchstabhausen an der Küste des Semantik, eines großen Sprachozeans. Ein kleines Bächlein namens Duden fließt durch ihren Ort und versorgt sie mit den nötigen Regelialien. Es ist ein paradiesmatisches Land, in dem einem gebratene Satzteile in den Mund fliegen. Nicht einmal von der allmächtigen Interpunktion werden die Blindtexte beherrscht – ein geradezu unorthographisches Leben. Eines Tages aber beschloß eine kleine Zeile Blindtext, ihr Name war Lorem Ipsum, hinaus zu gehen in die weite Grammatik. Der große Oxmox riet ihr davon ab, da es dort wimmele von bösen Kommata, wilden Fragezeichen und hinterhältigen Semikoli, doch das Blindtextchen ließ sich nicht beirren. Es packte seine sieben Versalien, schob sich sein Initial in den Gürtel und machte sich auf den Weg. Als es die ersten Hügel des Kursivgebirges erklommen hatte, warf es einen letzten Blick zurück auf die Skyline seiner Heimatstadt Buchstabhausen, die Headline von Alphabetdorf und die Subline seiner eigenen Straße, der Zeilengasse. Wehmütig lief ihm eine rethorische Frage über die Wange, dann setzte es seinen Weg fort. Unterwegs traf es eine Copy. Die Copy warnte das Blindtextchen, da, wo sie herkäme wäre sie zigmal umgeschrieben worden und alles, was von ihrem Ursprung noch übrig wäre, sei das Wort “und” und das Blindtextchen solle umkehren und wieder in sein eigenes, sicheres Land zurückkehren. Doch alles Gutzureden konnte es nicht überzeugen und so dauerte es nicht lange, bis ihm ein paar heimtückische Werbetexter auflauerten, es mit Longe und Parole betrunken machten und es dann in ihre Agentur schleppten, wo sie es für ihre Projekte wieder und wieder missbrauchten. Und wenn es nicht umgeschrieben wurde, dann benutzen Sie es immer noch.");
        examples.put("eng", "Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. Even the all-powerful Pointing has no control about the blind texts – it is an almost unorthographic life. One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar. The Big Oxmox advised her not to do so, because there were thousands of bad Commas, wild Question Marks and devious Semikoli, but the Little Blind Text didn’t listen. She packed her seven versalia, put her initial into the belt and made herself on the way. When she reached the first hills of the Italic Mountains, she had a last view back on the skyline of her hometown Bookmarksgrove, the headline of Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way. On her way she met a copy. The copy warned the Little Blind Text, that where it came from it would have been rewritten a thousand times and everything that was left from its origin would be the word „and“ and the Little Blind Text should turn around and return to its own, safe country. But nothing the copy said could convince her and so it didn’t take long until a few insidious Copy Writers ambushed her, made her drunk with Longe and Parole and dragged her into their agency, where they abused her for their projects again and again. And if she hasn’t been rewritten, then they are still using her.");

        NaiveBayes naiveBayes = new NaiveBayes();

        for (String klasse : examples.keySet()) {
            naiveBayes.train(examples.get(klasse), klasse);
        }

        naiveBayes.classify("Das ist ein einfacher deutscher Beispieltext ob er erkannt wird, ist aber trotzdem fraglich. Aber anscheinend findet er genug Wörter aber je länger er wird und umso mehr ich schreibe desto größer wird die ungewissheit");
    }
}
