package ArtAuctions;

import dataStructures.*;
public class ArtworkIterator implements Iterator<Artwork> {

    Iterator<Entry<String,Artwork>> itID;
    public ArtworkIterator(Iterator<Entry<String,Artwork>> it){
        itID = it;
        rewind();
    }

    @Override
    public boolean hasNext() {
        return itID.hasNext();
    }

    @Override
    public Artwork next() throws NoSuchElementException {
        Entry<String, Artwork> entry = itID.next();
        return (Artwork) entry.getValue();

    }

    @Override
    public void rewind() {
        itID.rewind();
    }
}
