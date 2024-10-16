package dataStructures;

public class ComparatorIDS implements Comparator<String>{


    @Override
    public int compare(String thisid, String otherid) {
        return thisid.toLowerCase().compareTo(otherid.toLowerCase());
    }
}
