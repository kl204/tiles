package bitedu.bipa.tiles.dao;

import java.util.ArrayList;

import bitedu.bipa.tiles.vo.BookCopy;

public interface IBookDao {
	
	public boolean insertBook(BookCopy copy);	
	public boolean deleteBook(int parseInt);
	public BookCopy selectBook(int parseInt);
	public boolean updateBook(BookCopy copy);
	ArrayList<BookCopy> selectBookAll();
}
