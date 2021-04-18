// package javaAC;

/**
 * 
 * @author Irshad Alam
 *
 */
public class TernarySearchTree {

    private TernaryNode root;

    /**
     * 
     * @param word
     * @return void
     * 
     *         This method will insert the nodes to TernarySearchTree
     */
    public void insert(String word) {
        root = insert(root, word.toCharArray(), 0);
    }

    /**
     * 
     * @param root
     * @param word
     * @param i
     * @return {@link TernaryNode} Util to insert the nodes into
     *         {@link TernarySearchTree} by reading characters from the string
     */
    private TernaryNode insert(TernaryNode root, char[] word, int i) {
        if (root == null) {
            root = new TernaryNode(word[i]);
        }
        if (word[i] < root.data)
            root.left = insert(root.left, word, i);
        else if (word[i] > root.data)
            root.right = insert(root.right, word, i);
        else

        {
            if (i + 1 < word.length)
                root.middle = insert(root.middle, word, i + 1);
            else
                root.isEnd = true;
        }
        return root;
    }

    /**
     * 
     * @param word
     * @return {@link String} This method does search from a prefix <i>example if
     *         prefix is ca it will return cat, cam, car etc.</i>
     */
    public String search(String word) {
        StringBuilder sb = new StringBuilder();
        // Gets the node which has data equal to last character in given string
        TernaryNode prefixRoot = crawlToPrefixLastNode(root, word.toCharArray(), 0);
        findAllSuggestions(prefixRoot, "", sb, word);
        if (sb.length() < 1) {
            return "No Matching String Found";
        }
        return sb.toString();
    }

    /**
     * 
     * @param r
     * @param word
     * @param ptr
     * @return {@link TernaryNode} This method crawls down to node in
     *         {@link TernarySearchTree} whose data is equal to the last char in the
     *         word char array and returns that node.
     */
    private TernaryNode crawlToPrefixLastNode(TernaryNode tNode, char[] word, int ptr)

    {
        if (tNode == null)
            return null;
        if (word[ptr] < tNode.data)
            return crawlToPrefixLastNode(tNode.left, word, ptr);
        else if (word[ptr] > tNode.data)
            return crawlToPrefixLastNode(tNode.right, word, ptr);
        else {
            if (ptr == word.length - 1)
                return tNode;
            else
                return crawlToPrefixLastNode(tNode.middle, word, ptr + 1);

        }

    }

    /**
     * 
     * @param r
     * @param str
     * @param sb
     * @param word A util to find all the suggestion from the given prefix.
     */
    private void findAllSuggestions(TernaryNode tNode, String str, StringBuilder sb, String word)

    {
        if (tNode != null) {
            findAllSuggestions(tNode.left, str, sb, word);
            // findAllSuggestions(tNode.right, str, sb, word);
            str = str + tNode.data;
            if (tNode.isEnd) {
                if (word.length() == 1) {
                    if (word.equals(str.substring(0, 1)))
                        sb.append(word + str.substring(1) + "\n");
                }
                else
                    sb.append(word + str.substring(1) + "\n");

            }
            findAllSuggestions(tNode.middle, str, sb, word);
            str = str.substring(0, str.length() - 1);

            findAllSuggestions(tNode.right, str, sb, word);
            // findAllSuggestions(tNode.left, str, sb, word);
        }

    }

}