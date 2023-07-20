package bitedu.bipa.tiles.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import bitedu.bipa.tiles.vo.BookCopy;

public interface IBookService {
	
	public boolean registBook(BookCopy copy);
	
	public ArrayList<BookCopy> searchBookAll();
	public boolean removeBook(String bookSeq); 
	public BookCopy findBook(String bookSeq); 
	public boolean modifyBook(BookCopy copy);

	BookCopy upload(List<FileItem> items) throws Exception;
	
}
