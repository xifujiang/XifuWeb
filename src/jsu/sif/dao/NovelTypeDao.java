package jsu.sif.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import jsu.sif.domain.Novel;
import jsu.sif.domain.NovelType;
import jsu.sif.util.JdbcUtil;

public class NovelTypeDao {
	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	public List<NovelType> readType() {
		String sql = "select * from noveltype";
		List<NovelType> novelType = new ArrayList();
		try {
			novelType = (List<NovelType>) qr.query(sql, new BeanListHandler(NovelType.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return novelType;
	}

}
