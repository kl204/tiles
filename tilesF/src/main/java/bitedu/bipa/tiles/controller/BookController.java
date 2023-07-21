package bitedu.bipa.tiles.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.tiles.service.BookService;
import bitedu.bipa.tiles.vo.BookCopy;

@Controller("bookController")
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService blmService;

	@RequestMapping("/list.do")
	public ModelAndView list() {
		ModelAndView mav = null;
		mav = new ModelAndView();
		
		ArrayList<BookCopy> Book = blmService.searchBookAll();
		
		mav.addObject("data",Book);
		mav.setViewName("/book/list");

		return mav;
	}
	
	@RequestMapping("/listAgain.do")
	public ModelAndView listAgain() {
		ModelAndView mav = null;
		mav = new ModelAndView();
		
		ArrayList<BookCopy> Book = blmService.searchBookAll();
		
		mav.addObject("data",Book);
		mav.setViewName("./book/bookList.jsp");

		return mav;
	}

	
	@RequestMapping(value="/view_regist.do", method=RequestMethod.GET)
	public ModelAndView viewRegist() {
		ModelAndView mav = new ModelAndView();
		
		
		mav.setViewName("/book/registView");
		return mav;
	}
	
	@RequestMapping(value="/view_detail.do", method=RequestMethod.GET)
	public ModelAndView viewDetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String bookSeq = request.getParameter("bookSeq");
		BookCopy copy = blmService.findBook(bookSeq);
		mav.addObject("copy",copy);
		mav.setViewName("/book/detailView");
		return mav;
	}
	
	@RequestMapping(value="/view_update.do", method=RequestMethod.POST)
	public ModelAndView viewUpdate(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String bookSeq = request.getParameter("bookSeq");
		BookCopy copy = blmService.findBook(bookSeq);
		mav.addObject("copy",copy);
		mav.setViewName("/book/updateView");
		return mav;
	}
	
	@RequestMapping(value="/remove.do", method=RequestMethod.GET)
	public ModelAndView remove(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String bookSeq = request.getParameter("bookSeq");
		boolean flag = blmService.removeBook(bookSeq);
		
		mav.setViewName("redirect:list.do");
		return mav;
	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		System.out.println("update"); 
		String path = "d:\\dev\\upload_files";

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024*1024*10);
		ServletFileUpload update = new ServletFileUpload(factory);
		List<FileItem> items = null;
		try {
			items = update.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookCopy copy = null;
		try {
			copy = blmService.upload(items);
			System.out.println("(controller)change data : "+copy);
			blmService.modifyBook(copy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("redirect:list.do");
		
		return mav;
	}
	
	@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	public ModelAndView regist(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BookCopy copy = new BookCopy();
		copy.setIsbn(request.getParameter("isbn"));
		copy.setTitle(request.getParameter("book_title"));
		copy.setAuthor(request.getParameter("author"));
		copy.setPublisher(request.getParameter("publisher"));
		String date = request.getParameter("publish_date");
		System.out.println("date "+date);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date now = df.parse(date);
			copy.setPublishDate(new Timestamp(now.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		blmService.registBook(copy);
		System.out.println("regist");
		mav.setViewName("redirect:list.do");
		return mav;
	}
	
	@RequestMapping(value="/upload.do", method=RequestMethod.POST)
	public ModelAndView upload(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		System.out.println("upload");
		String path = "d:\\dev\\upload_files";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024*1024*10);
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BookCopy copy = null;
		try {
			copy = blmService.upload(items);
			System.out.println(copy);
			blmService.registBook(copy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("redirect:list.do");
		
		return mav;
	}
	
	@RequestMapping(value="/download.do",method = RequestMethod.GET)
	public void download(@RequestParam("fileName") String fileName,HttpServletResponse resp) {
		
		File downloadFile = new File("d:\\dev\\upload_files\\images\\"+fileName);
		
		try {
			fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		resp.setContentType("text/html; charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.addHeader("Content-Disposition", "attatchment;filename="+fileName);
		
		try {
			FileInputStream fis = new FileInputStream(downloadFile);
			OutputStream os = resp.getOutputStream();
			byte[] buffer = new byte[256];
			int length = 0;

			while((length=fis.read(buffer))!=-1) {
				os.write(buffer, 0, length);
			}
			os.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
