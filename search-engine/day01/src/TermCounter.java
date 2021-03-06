import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class TermCounter {

	private Map<String, Integer> map;
	private String label;

	public TermCounter(String label) {
		this.label = label; //url
		this.map = new HashMap<String, Integer>();
	}

	public String getLabel() {
		return label;
	}

	public int size() {
       int wordCount = 0;
       for(String word : map.keySet()){
           wordCount += map.get(word);
       }
       return wordCount;
	}

	public void processElements(Elements paragraphs) {
		for (Node node: paragraphs) {
			processTree(node);
		}
	}

	public void processTree(Node root) {
		// NOTE: we could use select to find the TextNodes, but since
		// we already have a tree iterator, let's use it.
		for (Node node: new WikiNodeIterable(root)) {
			if (node instanceof TextNode) {
				processText(((TextNode) node).text());
			}
		}
	}

	public void processText(String text) {
		// replace punctuation with spaces, convert to lower case, and split on whitespace
		String[] array = text.replaceAll("\\pP", " ").toLowerCase().split("\\s+");

		for(int i = 0; i < array.length; i++){
		    incrementTermCount(array[i]);
        }
	}

	public void incrementTermCount(String term) {
	    put(term, get(term)+1);
	}

	public void put(String term, int count) {
	    map.put(term, count);
	}

	public Integer get(String term) {
		if(map.get(term) == null){
		    return 0;
        }
	    return map.get(term);
	}

	public Set<String> keySet() {
	    return map.keySet();
	}

	public void printCounts() {
		for (String key: keySet()) {
			Integer count = get(key);
			System.out.println(key + ", " + count);
		}
		System.out.println("Total of all counts = " + size());
	}

	public static void main(String[] args) throws IOException {
		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";

		WikiFetcher wf = new WikiFetcher();
		Elements paragraphs = wf.fetchWikipedia(url);

		TermCounter counter = new TermCounter(url.toString());
		counter.processElements(paragraphs);
		counter.printCounts();
	}
}
