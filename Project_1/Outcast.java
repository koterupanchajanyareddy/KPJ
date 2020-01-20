public class Outcast {
    private WordNet wordnet;
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }
    public String outcast(String[] nouns) {
        String temp_String = null;
        int temp_int = Integer.MIN_VALUE;
        for (int i = 0; i < nouns.length; i++) {
            int temp = 0;
            for (int j = 0; j < nouns.length; j++) {
                temp = temp + this.wordnet.distance(nouns[i], nouns[j]);
            }
            if (temp > temp_int) {
                temp_int = temp;
                temp_String = nouns[i];
            }
        }
        return temp_String;
    }
    public static void main(String[] args) {
    }
}