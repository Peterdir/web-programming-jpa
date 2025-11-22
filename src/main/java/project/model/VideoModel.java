package project.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class VideoModel implements Serializable{
	private static final long serialVersionUID = 1L;

	private int idVideo;
	
	private String title;
	
	private String poster;
	
	private long views;

	private String description;
	
	private boolean active;
}
