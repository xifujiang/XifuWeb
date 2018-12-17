package jsu.sif.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import jsu.sif.domain.Novel;
import jsu.sif.domain.NovelContent;
import jsu.sif.util.JdbcUtil;

public class ChapterDao {
	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	//插入章节
	public void insertChapter(NovelContent novelContent,String nid) throws SQLException {
		String sql = "insert into novelcontent"+nid+" (chapter,content,time)values(?,?,?)";
		System.out.println(sql);
		qr.update(sql,novelContent.getChapter(),novelContent.getContent(),new Date());
	}

	//读取章节
	public List<NovelContent> readAllChapter(String nid) {
		String sql = "select * from novelcontent"+nid;
		List<NovelContent> chapters = new ArrayList<>();
		try {
			chapters = (List<NovelContent>) qr.query(sql, new BeanListHandler(NovelContent.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chapters;
	}

	//读取最后更新的一章
//	public NovelContent readLastChapter(int nid){
//		String sql = "select * from novelcontent"+nid+" where time = (select max(time) from novelcontent"+nid+")";
//		System.out.println(sql);
//		NovelContent chapter = new NovelContent();
//		try {
//			chapter = (NovelContent) qr.query(sql, new BeanHandler(NovelContent.class));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return chapter;
//	}
	public List<NovelContent> readLastChapter(int nid){
		String sql = "select * from novelcontent"+nid+" where cid = (select max(cid) from novelcontent"+nid+")";
		System.out.println(sql);
		List<NovelContent> chapters = new ArrayList<>();
		try {
			chapters = (List<NovelContent>) qr.query(sql, new BeanListHandler(NovelContent.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chapters;
	}

	public NovelContent readOneChapter(int nid, int cid) {
		String sql = "select * from novelcontent"+nid+" where cid = ?";
		System.out.println(sql);
		NovelContent chapter = null;
		try {
			chapter = (NovelContent) qr.query(sql, new BeanHandler(NovelContent.class),cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chapter;
	}
}
