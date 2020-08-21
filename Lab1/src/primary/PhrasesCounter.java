package primary;

public class PhrasesCounter {
    private int phraseLength;
    private int phraseCount;
    private String fileName;
    private boolean isConsole;

    private void setOptions(String[] args) {
        CommandLineParser commandLineParser = new CommandLineParser(args);
        phraseLength = commandLineParser.getPhraseLength();
        phraseCount = commandLineParser.getPhraseCount();
        isConsole = commandLineParser.isConsole();
        fileName = commandLineParser.getFileName();
    }

    public void start(String[] args) {
        setOptions(args);

        if (isConsole) {
            System.out.format("Console activated: length:%d, minOfCount:%d\n", phraseLength, phraseCount);
            ConsoleToList consoleToList = new ConsoleToList();
            TextParser textParser = new TextParser();
            textParser.parse(consoleToList.transform(), phraseLength, phraseCount);
        } else if (fileName != null) {
            System.out.format("File <%s> opened: length:%d, minOfCount:%d\n", fileName, phraseLength, phraseCount);
            FileToList fileToList = new FileToList();
            TextParser textParser = new TextParser();
            textParser.parse(fileToList.transform(fileName), phraseLength, phraseCount);
        } else {
            System.out.println("Не указан поток ввода.");
            return;
        }
    }


}
