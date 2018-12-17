package jsu.sif.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import jsu.sif.domain.Novel;
import jsu.sif.util.JdbcUtil;

public class NovelDao {
	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());

	//读取某本小说
	public Novel readOneNovel(int nid){
		String sql = "select * from novel where nid=?";
		Novel novel = null;
		try {
			novel = (Novel) qr.query(sql, new BeanHandler(Novel.class),nid);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return novel;
	}
	//读取某个作者写的小说小说
	public List<Novel> readNovel(String author) {
		
		String sql = "select * from novel where nauthor=?";
		List<Novel> novels = new ArrayList();
//		Novel[] novels2 =null;
//		novels = qRunner.query(sql, new BeanListHandler(),Novel.class,author);
		try {
			novels = (List<Novel>) qr.query(sql, new BeanListHandler(Novel.class), author);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return novels;
	}
	//读取全部小说
	public List<Novel> readAllNovel(){
		String sql = "select * from novel order by nhot desc";
		List<Novel> novels = new ArrayList();
		try {
			novels = (List<Novel>) qr.query(sql, new BeanListHandler(Novel.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return novels;
	}
	
	//删除小说
	public void delete(int nid) throws SQLException {
		String sql = "delete from novel where nid=?";
		qr.update(sql,nid);
		String sql2 = "drop table if exists novelcontent"+nid;
		qr.update(sql2);
	}
	
	//创建小说
	public int createNovel(Novel novel) {
		String sql = "insert into novel (nname,nauthor,nstatic,nclass,nlable,nlogo,nintro,createtime) values(?,?,?,?,?,?,?,?)";
		try {
			qr.update(sql,novel.getNname(),novel.getNauthor(),novel.getNstatic(),novel.getNclass(),novel.getNlable(),novel.getNlogo(),novel.getNintro(),new Date());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql2 ="select nid from novel where nname=?";
		Novel novel2 = null;
		try {
			novel2 = qr.query(sql2, new BeanHandler<Novel>(Novel.class),novel.getNname());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql3 = "create table novelContent"+novel2.getNid()+ " (cid int primary key auto_increment, chapter varchar(18),content varchar(200),time datetime)";
		System.out.println(sql3);
		try {
			qr.update(sql3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return novel2.getNid();
	}
	
}
