package dataStructures;

import ArtAuctions.Artwork;

public class ComparatorByWorkName {

    private Artwork a;
    private Artwork o;

    public ComparatorByWorkName(Artwork a, Artwork o){}

    public int compare(){
        return a.getName().compareTo(o.getName());
    }

}
