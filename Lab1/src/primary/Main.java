package primary;

public class Main {

    public static void main(String[] args) {
        try {
            PhrasesCounter phrasesCounter = new PhrasesCounter();
            phrasesCounter.start(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
