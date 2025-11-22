package project.service;

import java.util.List;

import project.entity.Video;

public interface VideoService {
	List<Video> findAll();

	Video findById(int id);

	void insert(Video video);

	void update(Video video);

	void delete(int id);

	List<Video> search(String keyword);
}
