package jsu.sif.domain;


import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Novel {
	private int nid;
	private String nname;//dui
	private String nauthor;
	private int nstatic;//dui
	private int nclass;//dui
	private String nlable;//标签
	private String nlogo;//图片
	private int nhot;
	private String nintro;//介绍
	private List<NovelContent> chapters = new ArrayList<>();
	public Novel(){}
	@Override
	public String toString() {
		return "Novel [nid=" + nid + ", nname=" + nname + ", nauthor=" + nauthor + ", nstatic=" + nstatic + ", nclass="
				+ nclass + ", nlable=" + nlable + ", nlogo=" + nlogo + ", nhot=" + nhot + ", nintro=" + nintro
				+ ", chapters=" + chapters + "]";
	}
	

	
}
