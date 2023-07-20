package bitedu.bipa.tiles.service;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.tiles.dao.BookDao;
import bitedu.bipa.tiles.vo.BookCopy;

@Service("bookService")
public class BookService implements IBookService {

	@Autowired
	private BookDao dao;
	
	@Override
	public boolean registBook(BookCopy copy) {
		boolean flag = false;
		flag = dao.insertBook(copy);
		return flag;
	}
	
	@Override
	public ArrayList<BookCopy> searchBookAll(){
		ArrayList<BookCopy> list = null;
		list = dao.selectBookAll();
		return list;
	}
	
	@Override
	public boolean removeBook(String bookSeq) {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = dao.deleteBook(Integer.parseInt(bookSeq));
		return flag;
	}
	
	@Override
	public BookCopy findBook(String bookSeq) {
		BookCopy copy = null;
		copy = dao.selectBook(Integer.parseInt(bookSeq));
		System.out.println(copy);
		return copy;
	}
	
	@Override
	public boolean modifyBook(BookCopy copy) {
		// TODO Auto-generated method stub
		boolean flag = false;
		flag = dao.updateBook(copy);
		return flag;
	}
	
	@Override
	public BookCopy upload(List<FileItem> items) throws Exception {
		// TODO Auto-generated method stub
		BookCopy copy = null;
		copy = new BookCopy();
		for(FileItem item : items) {
			if(item!=null&item.isFormField()) {
				//일반적인 Form값 추출
				String fieldName = item.getFieldName();
				if(fieldName.equals("book_seq")){
					copy.setBookSeq(Integer.parseInt(item.getString("UTF-8")));
				}else if(fieldName.equals("isbn")) {
					copy.setIsbn(item.getString("UTF-8"));
				} else if(fieldName.equals("book_title")) {
					copy.setTitle(item.getString("UTF-8"));
				} else if(fieldName.equals("author")) {
					copy.setAuthor(item.getString("UTF-8"));
				} else if(fieldName.equals("publish_date")) {
					String date = item.getString("UTF-8");
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date now = df.parse(date);
						copy.setPublishDate(new Timestamp(now.getTime()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				}

				
			} else {
				// uplode할 데이터에 대한 정보 추출
				String fieldName = item.getFieldName();
				if(fieldName.equals("book_image")) {
					String temp = item.getName();
					System.out.println("book_image "+temp);
					int index = temp.lastIndexOf("\\");
					String fileName = temp.substring(index+1);
					copy.setBookImage(fileName);
					File uploadFile = new File("d:\\dev\\upload_files\\images\\"+fileName);
					item.write(uploadFile);
				}

			}
		}
		return copy;
	}

	
}







