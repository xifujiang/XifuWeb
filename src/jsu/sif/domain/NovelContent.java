package jsu.sif.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class NovelContent {
	private int cid;
	private String chapter;
	private String content;
	private Date time;
	public NovelContent(){}
	public NovelContent(int cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "NovelContent [cid=" + cid + ", chapter=" + chapter + ", content=" + content + ", time=" + time + "]";
	}
}
