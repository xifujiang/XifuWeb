
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jsu.sif.dao.ChapterDao;
import jsu.sif.dao.NovelDao;
import jsu.sif.domain.NovelContent;
import jsu.sif.domain.NovelType;
import jsu.sif.service.NovelTypeService;

public class test {
	public static void main(String args[]) throws SQLException{
//		NovelContent novelContent = new NovelContent();
//		novelContent.setChapter("第一章");
//		novelContent.setContent("chapter1.txt");
//		new ChapterDao().insertChapter(novelContent, "11");
		
		
		List<NovelType> novelTypes = new ArrayList<>();
		novelTypes = new NovelTypeService().readType();
		Iterator iterator = novelTypes.iterator();
		while(iterator.hasNext()){
			NovelType novelType = (NovelType) iterator.next();
			System.out.println(novelType);
		}
	}
}
