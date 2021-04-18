import java.util.ArrayList;

class TSTNode {
    char data;
    boolean isEnd;
    TSTNode left, middle, right;

    /** Constructor **/
    public TSTNode(char data) {
        this.data = data;
        this.isEnd = false;
        this.left = null;
        this.middle = null;
        this.right = null;
    }
}

class TernarySearchTree {
    private TSTNode root;
    private ArrayList<String> al;

    /** Constructor **/
    public TernarySearchTree() {
        root = null;
    }

    /** function to check if empty **/
    public boolean isEmpty() {
        return root == null;
    }

    /** function to clear **/
    public void makeEmpty() {
        root = null;
    }

    /** function to insert for a word **/
    public void insert(String word) {
        root = insert(root, word.toCharArray(), 0);
    }

    /** function to insert for a word **/
    public TSTNode insert(TSTNode r, char[] word, int ptr) {
        if (r == null)
            r = new TSTNode(word[ptr]);

        if (word[ptr] < r.data)
            r.left = insert(r.left, word, ptr);
        else if (word[ptr] > r.data)
            r.right = insert(r.right, word, ptr);
        else {
            if (ptr + 1 < word.length)
                r.middle = insert(r.middle, word, ptr + 1);
            else
                r.isEnd = true;
        }
        return r;
    }

    /** function to delete a word **/
    public void delete(String word) {
        delete(root, word.toCharArray(), 0);
    }

    /** function to delete a word **/
    private void delete(TSTNode r, char[] word, int ptr) {
        if (r == null)
            return;

        if (word[ptr] < r.data)
            delete(r.left, word, ptr);
        else if (word[ptr] > r.data)
            delete(r.right, word, ptr);
        else {
            /** to delete a word just make isEnd false **/
            if (r.isEnd && ptr == word.length - 1)
                r.isEnd = false;

            else if (ptr + 1 < word.length)
                delete(r.middle, word, ptr + 1);
        }
    }

    /** function to search for a word **/
    // public boolean search(String word) {
    // return search(root, word.toCharArray(), 0);
    // }

    // /** function to search for a word **/
    // private boolean search(TSTNode r, char[] word, int ptr) {
    // if (r == null)
    // return false;

    // if (word[ptr] < r.data)
    // return search(r.left, word, ptr);
    // else if (word[ptr] > r.data)
    // return search(r.right, word, ptr);
    // else {
    // if (r.isEnd && ptr == word.length - 1)
    // return true;
    // else if (ptr == word.length - 1)
    // return false;
    // else
    // return search(r.middle, word, ptr + 1);
    // }
    // }

    public String search(String word) {
        StringBuilder sb = new StringBuilder();

        TSTNode prefixRoot = crawlToPrefixLaNode(root, word.toCharArray(), 0);
        findAllSuggestions(prefixRoot, "", sb, word);
        if (sb.length() < 1) {
            return "No Matching String Found";
        }
        return sb.toString();
    }

    public String[] getSearchResults(String word){
        String search_SB = search(word);
        String[] searchArr = search_SB.split("\n");
        return searchArr;
    }
    /** function to print tree **/
    public String toString() {
        al = new ArrayList<String>();
        traverse(root, "");
        return "\nTernary Search Tree : " + al;
    }

    /** function to traverse tree **/
    private void traverse(TSTNode r, String str) {
        if (r != null) {
            traverse(r.left, str);

            str = str + r.data;
            if (r.isEnd)
                al.add(str);

            traverse(r.middle, str);
            str = str.substring(0, str.length() - 1);

            traverse(r.right, str);
        }
    }

    private TSTNode crawlToPrefixLaNode(TSTNode r, char[] word, int ptr) {
        if (r == null)
            return null;
        if (word[ptr] < r.data)
            return crawlToPrefixLaNode(r.left, word, ptr);
        else if (word[ptr] > r.data)
            return crawlToPrefixLaNode(r.right, word, ptr);
        else {
            if (ptr == word.length - 1)
                return r;
            else
                return crawlToPrefixLaNode(r.middle, word, ptr + 1);
        }
    }

    private void findAllSuggestions(TSTNode r, String s, StringBuilder sb, String word) {
        if (r != null) {
            findAllSuggestions(r.left, s, sb, word);
            s = s + r.data;
            if (r.isEnd) {
                if (word.length() == 1) {
                    if (word.equals(s.substring(0, 1)))
                        sb.append(word + s.substring(1) + "\n");
                }
                else
                    sb.append(word + s.substring(1) + "\n");

            }
            findAllSuggestions(r.middle, s, sb, word);
            s = s.substring(0, s.length() - 1);
            findAllSuggestions(r.right, s, sb, word);
        }
    }
}

public class autocomp {
    public static void main(String[] args) {
        System.out.println("starting");
        TernarySearchTree TST = new TernarySearchTree();

        TST.insert("Hello");
        TST.insert("Hell");
        TST.insert("Hel");
        TST.insert("Hellsing");
        TST.insert("Kitty");

        System.out.println("Testing to string\n"+TST.toString());
        System.out.println("Testing to search Hell\n"+TST.search("Hell"));
        System.out.println("Testing to search Hellsing\n"+TST.search("Hellsing"));
        System.out.println("Testing to search He\n"+TST.search("He"));
        System.out.println("Testing to search H\n"+TST.search("H"));
        // System.out.println("Testing to search with an empty space\n"+TST.search(" "));

        String[] s_arr = TST.getSearchResults("H");

        for(String x : s_arr){
            System.out.println(x);
        }
        System.out.println(s_arr.length);
        // System.out.println("Testing to search with null\n"+TST.search(""));
        // System.out.println(TT.Contains("Hello"));
        // System.out.println(TT.Contains("Hell"));
        // System.out.println(TT.Contains("Hel"));
    }
}

// 1 HASTINGS FS CLIFF AVE WB
// 2 HASTINGS ST FS ALPHA AVE WB
// 3 HASTINGS ST FS BETA AVE EB
// 4 HASTINGS ST FS BOUNDARY RD EB
// 5 HASTINGS ST FS DUTHIE AVE EB
// 6 HASTINGS ST FS DUTHIE AVE WB
// 7 HASTINGS ST FS FELL AVE EB
// 8 HASTINGS ST FS FELL AVE WB
// 9  HASTINGS ST FS GAMMA AVE WB
// 10 HASTINGS ST FS GILMORE AVE EB
// 11HASTINGS ST FS GILMORE AVE WB
// 12HASTINGS ST FS HOLDOM AVE EB
// 13HASTINGS ST FS HOLDOM AVE- WB
// 14HASTINGS ST FS HOLDOM AVE-- WB
// 15HASTINGS ST FS HOWARD AVE EB
// 16HASTINGS ST FS HOWARD AVE WB
// 17HASTINGS ST FS HYTHE AVE EB
// 18HASTINGS ST FS HYTHE AVE WB
// 19HASTINGS ST FS INGLETON AVE EB
// H20ASTINGS ST FS INGLETON AVE WB
// 21HASTINGS ST FS INLET DR EB
// 22HASTINGS ST FS KENSINGTON AVE EB
// 23HASTINGS ST FS KENSINGTON AVE WB
// H24ASTINGS ST FS MACDONALD AVE EB
// 25HASTINGS ST FS MADISON AVE EB
// 26HASTINGS ST FS MADISON AVE WB
// 27HASTINGS ST FS SPERLING AVE EB
// 28HASTINGS ST FS SPERLING AVE WB
// 29HASTINGS ST FS SPRINGER AVE EB
// 30HASTINGS ST FS SPRINGER AVE WB
// 31HASTINGS ST FS WILLINGDON AVE WB
// 32HASTINGS ST NS ALPHA AVE EB
// 33HASTINGS ST NS WILLINGDON AVE EB
