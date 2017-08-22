package javamusic.models; 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import com.wrapper.spotify.models.Album;
import com.wrapper.spotify.models.SimpleAlbum;

public class Cart implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Album> albums;	
    
    public Cart() {
    	albums = new ArrayList<Album>();
    }
    
	public ArrayList<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}    
	public void addToCart(Album album) {
		this.albums.add(album);		
	}
	public void removeFromCart(Album album) {
		this.albums.remove(album);	
	}	
	public void removeFromCartById(String idToRemove) {
		Iterator<Album> iter = this.albums.iterator();
		while (iter.hasNext()) {
			String id = iter.next().getId();
			if (id.equals(idToRemove))
				iter.remove();
		}
	}		
	public boolean isEmpty() {
		return this.albums.isEmpty();
	}		
}