import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import redis.clients.jedis.Jedis;

public class WikiSearch {

    // map from URLs that contain the term(s) to relevance score
    private Map<String, Integer> map;

    private Index index;

    public WikiSearch(Map<String, Integer> map) {
            this.map = map;
    }

    public Integer getRelevance(String url) {
        if(map.get(url)==null){
            return 0;
        }
        return map.get(url);
    }


    // Prints the contents in order of term frequency.
    private  void print() {
        List<Entry<String, Integer>> entries = sort();
        for (Entry<String, Integer> entry: entries) {
            System.out.println(entry);
        }
    }

    // Computes the union of two search results.
    public WikiSearch or(WikiSearch that) {
        for(String url : that.map.keySet()){
                map.put(url, that.getRelevance(url)+this.getRelevance(url));
        }
        return this;
    }

    // Computes the intersection of two search results.
    public WikiSearch and(WikiSearch that) {
        Map<String, Integer> andResult = new HashMap<>();
        for(String url : that.map.keySet()){
            if((map.containsKey(url))){
                andResult.put(url, that.getRelevance(url)+this.getRelevance(url));
            }
        }
        return new WikiSearch(andResult);
    }

    // Computes the intersection of two search results.
    public WikiSearch minus(WikiSearch that) {
        for(String url : that.map.keySet()){
            if((map.containsKey(url))){
                map.remove(url);
            }
        }
        return this;
    }



    // Computes the relevance of a search with multiple terms.
    protected int totalRelevance(Integer rel1, Integer rel2){
        return rel1+rel2;
    }


    // Sort the results by relevance.
    public List<Entry<String, Integer>> sort() {
        List<Entry<String, Integer>> sorted = new LinkedList(map.entrySet());
        Collections.sort(sorted, new Comparator() {
            public int compare(Object entry1, Object entry2) {
                return ((Comparable) ((Map.Entry) (entry1)).getValue())
                        .compareTo(((Map.Entry) (entry2)).getValue());
            }
        });
        return sorted;
    }


    // Performs a search and makes a WikiSearch object.
    public static WikiSearch search(String term, Index index) {
        // TODO: Use the index to get a map from URL to count

        // Fix this
        Map<String, Integer> map = index.get(term);

        // Store the map locally in the WikiSearch
        return new WikiSearch(map);
    }

    public static WikiSearch TfIdf(String[] terms){
        Index index = new Index();
        WikiSearch total = search(terms[0],index);
        double[] termPages = new double[(terms.length)];
        termPages[0] = total.map.size();
        for(int i = 1; i < terms.length;i++){
            WikiSearch cPage = search(terms[i],index);
            total = total.or(cPage);
            termPages[i] = (double)(cPage.map.size());
        }

        double tot = (double)(total.map.size());

        WikiSearch finalMap = new WikiSearch(new  HashMap<String, Integer>());
        for(int i = 0; i < terms.length;i++){
            WikiSearch cPage = search(terms[i],index);
            for(String key : cPage.map.keySet()){
                cPage.map.put(key, (int)(cPage.getRelevance(key) * Math.log(tot/(1+termPages[i]))));
            }
            finalMap = finalMap.or(cPage);
        }

        return finalMap;

    }


    // TODO: Choose an extension and add your methods here

    public static void main(String[] args) throws IOException {

        // make a Index
        Jedis jedis = JedisMaker.make();

        Index index = new Index(); // You might need to change this, depending on how your constructor works.

        // search for the first term
//        String term1 = "java";
//        System.out.println("Query: " + term1);
//        WikiSearch search1 = search(term1, index);
//        search1.print();
//
//        // search for the second term
//        String term2 = "programming";
//        System.out.println("Query: " + term2);
//        WikiSearch search2 = search(term2, index);
//        search2.print();
//
//        // compute the intersection of the searches
//        System.out.println("Query: " + term2 + " MINUS " + term1);
//        WikiSearch intersection = search2.minus(search1);
//        intersection.print();
//
//        System.out.println("Query: " + term2 + " OR " + term1);
//        intersection = search2.or(search1);
//        intersection.print();
//
//        System.out.println("Break");
//        TfIdf(new String[]{term1, term2}).print();


        System.out.println(jedis.hgetAll("the"));
        System.out.println("Break:-----------------");
        System.out.println(jedis.hgetAll("java"));

    }
}