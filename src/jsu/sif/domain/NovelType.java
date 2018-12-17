package jsu.sif.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class NovelType {
	private int tid;
	private String tname;
	@Override
	public String toString() {
		return "NovelType [tid=" + tid + ", tname=" + tname + "]";
	}
	
}
