package jsu.sif.service;

import java.sql.SQLException;
import java.util.List;

import jsu.sif.dao.ChapterDao;
import jsu.sif.domain.NovelContent;

public class ChapterService {
	ChapterDao chapterDao = new ChapterDao();
	//插入章节
	public void insertChapter(NovelContent novelContent,String nid) throws SQLException {
		chapterDao.insertChapter(novelContent,nid);
	}

	public List<NovelContent> readAllChapter(String nid) {
		return chapterDao.readAllChapter(nid);
	}

	public NovelContent readOneChapter(int nid, int cid) {
		return chapterDao.readOneChapter(nid,cid);
	}
	
	
	
}