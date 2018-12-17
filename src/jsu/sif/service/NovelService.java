package jsu.sif.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.javafx.collections.ChangeHelper;

import jsu.sif.dao.ChapterDao;
import jsu.sif.dao.NovelDao;
import jsu.sif.domain.Novel;

public class NovelService {
	NovelDao novelDao = new NovelDao();
	//创建小说
	public int createNovel(Novel novel) {
		return novelDao.createNovel(novel);
	}
	//删除小说
	public void delete(int nid) throws SQLException {
		novelDao.delete(nid);
	}
	
	public Novel readOneNovel(int nid){
		return novelDao.readOneNovel(nid);
	}
	
	//读取某个作者写的小说
	public List<Novel> readNovel(String author) {

		List<Novel> novels = novelDao.readNovel(author);
		//创建ChapterDao类
		ChapterDao chapterDao = new ChapterDao();
		
		//迭代器循环遍历
		Iterator it = novels.iterator();
		while(it.hasNext()){
			Novel novel = (Novel) it.next();
			novel.setChapters(chapterDao.readLastChapter(novel.getNid()));
		}
		
		return novels;
	}
	
	//读取所有小说
	public List<Novel> readAllNovel(){
		return novelDao.readAllNovel();
	}

}
