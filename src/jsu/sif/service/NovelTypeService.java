package jsu.sif.service;

import java.util.List;

import jsu.sif.dao.NovelTypeDao;
import jsu.sif.domain.NovelType;

public class NovelTypeService {
	NovelTypeDao novelTypeDao = new NovelTypeDao();
	public List<NovelType> readType(){
		return novelTypeDao.readType();
	}
}
