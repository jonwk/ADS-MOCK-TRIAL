// package javaAC;

/**
 * 
 * @author irshad alam This represents the Ternary tree nodes
 *
 */
public class TestMain {

    public static void main(String[] args) {
        TernarySearchTree tst = new TernarySearchTree();
        tst.insert("cat");
        tst.insert("car");
        tst.insert("mat");
        tst.insert("caravan");
        tst.insert("cartoon");
        String searchKey = "c";
        String result = tst.search(searchKey.toLowerCase());
        System.out.println("Suggestions for keyword " + searchKey + "\n=======================");
        System.out.println(result);

        tst.insert("hello".toLowerCase());
        tst.insert("hell".toLowerCase());
        tst.insert("hel".toLowerCase());
        tst.insert("hellsing".toLowerCase());
        tst.insert("kitty".toLowerCase());

        // System.out.println("Testing to string\n" + tst.toString());
        System.out.println("Testing to search hell\n" +tst.search("hell".toLowerCase()));
        System.out.println("Testing to search hellsing\n" +tst.search("hellsing".toLowerCase()));
        System.out.println("Testing to search he\n" +tst.search("he".toLowerCase()));
        System.out.println("Testing to search h\n" +tst.search("h".toLowerCase()));
        System.out.println("Testing to search z\n" +tst.search("z".toLowerCase()));
        // System.out.println("Testing to search with an empty space\n"tst.search("
        // "));
        System.out.println("Testing to search with null\n" +tst.search(""));
    }
}