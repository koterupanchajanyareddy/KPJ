import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.DirectedDFS;


public class WordNet {
    private Digraph vertexs;
    private SAP sap;
    private Hashtable<Integer, String> h1 = new Hashtable<Integer, String>();
    private Hashtable<String, SET<Integer>> h2 = new Hashtable<String, SET<Integer>>();

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException();
        }
        this.parseSynsets(synsets);
        vertexs = new Digraph(h1.size());
        this.parseHypernyms(hypernyms);

        sap = new SAP(vertexs);
        DirectedCycle finder = new DirectedCycle(vertexs);
        if (finder.hasCycle()) {
            throw new IllegalArgumentException();
        }

        // Find all roots
        SET<Integer> roots = new SET<Integer>();
        for (int i = 0; i < vertexs.V(); i++) {
            DirectedDFS dfs = new DirectedDFS( vertexs, i);
            if (dfs.count() == 1) {
                roots.add(i);
            }
        }
        assert roots.size() > 0;

        // StdOut.println("roots.size(): " + roots.size());

        if (roots.size() > 1) {
            throw new IllegalArgumentException();
        }
    }

    private void parseSynsets(String synsets) {
        try {
        BufferedReader csvReader = new BufferedReader(new FileReader(synsets));
        String row;
        while ((row = csvReader.readLine()) != null) {
           String[] data = row.split(",");
           int id = Integer.parseInt(data[0]);
           String[] tempStrings = data[1].split(" ");
           SET<String> set = new SET<String>();
            for (String noun : tempStrings) {
                set.add(noun);
            }
            h1.put(id, data[1]);
            for (String noun : tempStrings) {
                if (h2.containsKey(noun)) {
                    h2.get(noun).add(id);
                } else {
                    SET<Integer> s = new SET<Integer>();
                    s.add(id);
                    h2.put(noun, s);
                }
            }
           // do something with the data
           
           
        }
        csvReader.close();}
        catch(FileNotFoundException a) {}
        catch(IOException b) {}
    }
    private void parseHypernyms(String hypernyms) {
        try{
        BufferedReader csvReader = new BufferedReader(new FileReader(hypernyms));
       String row;
       while ((row = csvReader.readLine()) != null) {
           String[] data = row.split(",");
           // do something with the data
           for (int i = 1; i < data.length; i++) {
            vertexs.addEdge(Integer.parseInt(data[0]),Integer.parseInt(data[i]));
           }
        }
        csvReader.close();}
        catch(FileNotFoundException a){}
        catch(IOException b){}
    }
   // returns all WordNet nouns
   public Iterable<String> nouns() {
    return h2.keySet();
   }

   // is the word a WordNet noun?
   public boolean isNoun(String word) {
    if (word == null) {
        throw new IllegalArgumentException();
    }
       return h2.containsKey(word);
   }

   // distance between nounA and nounB (defined below)
   public int distance(String nounA, String nounB) {
       if (nounA == null || nounB == null) {
           throw new IllegalArgumentException();
       }
       if (this.isNoun(nounA) && this.isNoun(nounB)) {
       return sap.length(h2.get(nounA), h2.get(nounB));
    } else {
        throw new IllegalArgumentException();
    }
   }

   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounA, String nounB) {
    if (nounA == null || nounB == null) {
        throw new IllegalArgumentException();
    }
    //    if (this.isNoun(nounA) && this.isNoun(nounB)) {
    //    SAP graph = new SAP(this.vertexs);
    //    return h1.get(graph.ancestor(h2.get(nounA), h2.get(nounB)));
    // } else return null;
    if (!isNoun(nounA) || !isNoun(nounB)) {
        throw new IllegalArgumentException();
    }

    SET<Integer> idsA = h2.get(nounA);
    SET<Integer> idsB = h2.get(nounB);

    int ancestor = sap.ancestor(idsA, idsB);
    return h1.get(ancestor);
   }

   // do unit testing of this class
   public static void main(String[] args) {
   }
}