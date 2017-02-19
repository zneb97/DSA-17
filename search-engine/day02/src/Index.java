import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Index {

    // Index: map of words to URL and their counts
    //private Map<String, Set<String>> index = new HashMap<String, Set<String>>();
    //private static Set<String> urls;
    private static Set<String> stopWords;
    private static Jedis jedis;

    public Index(){
        try {
            stopWords = new StopWords().getStopWords();
            jedis = new JedisMaker().make();
        }catch(IOException e){
            System.out.print("IOException, did not get stopwords, or jedis failed");
        }


    }


    public void add(String term, TermCounter tc,Transaction t) {
        // TODO
        // if we're seeing a term for the first time, make a new Set
        // otherwise we can add the term to an existing Set
        if(!stopWords.contains(term)) {

            t.hset(term, tc.getLabel(), Integer.toString(tc.get(term)));

        }
    }

    public Map<String,Integer> get(String term) {
        Map<String, Integer> counts = new HashMap();
        Set<String> urls = jedis.smembers(term);
        for (String url: urls) {
            String count = jedis.hget(url, term);
            counts.put(url, Integer.valueOf(count));
        }
        return counts;
    }

    public void indexPage(String url, Elements paragraphs) {
        // make a TermCounter and count the terms in the paragraphs
        // TODO

        //if(!jedis.hexists("urlstest", url)) {
            Transaction t = jedis.multi();
            TermCounter newCount = new TermCounter(url);
            newCount.processElements(paragraphs);
            Set<String> keySet = newCount.keySet();
            for (String key : keySet) {
                add(key, newCount,t);
            }
            t.hset("urlstest", url, "bleh");
        //}
        t.exec();

        // for each term in the TermCounter, add the TermCounter to the index
        // TODO
    }

    public static void deleteAllKeys() {

        Set<String> keys = jedis.keys("*");

        Transaction t = jedis.multi();

        for (String key : keys) {

            t.del(key);

        }

        t.exec();
    }

    public void printIndex() {
        // loop through the search terms
        for (String term: keySet()) {
            System.out.println(term);

            // for each term, print the pages where it appears
            Map<String, Integer> tcs = get(term);
            for (String tc: tcs.keySet()) {
                String count = jedis.hget(term, tc);
                System.out.println("    " + term + " " + tc + "  " +  count);
            }
        }
    }

    public Set<String> keySet() {
        return jedis.keys("*");
    }


    public static void main(String[] args) throws IOException {

        WikiFetcher wf = new WikiFetcher();
        Index indexer = new Index();

        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        Elements paragraphs = wf.fetchWikipedia(url);
        indexer.indexPage(url, paragraphs);

        url = "https://en.wikipedia.org/wiki/Programming_language";
        paragraphs = wf.fetchWikipedia(url);
        indexer.indexPage(url, paragraphs);

        indexer.printIndex();
    }
}
