package abaranov.parse;

import abaranov.parse.Parse;

public class RunParse {
    private final Parse parse;

    public RunParse(final Parse parse) {
        this.parse = parse;
    }
    public void parse(String input, String output) {
        this.parse.parse(input, output);
    }
}
