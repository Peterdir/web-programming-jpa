package project.service.impl;

import java.util.List;

import project.entity.Video;
import project.repository.VideoRepository;
import project.repository.impl.VideoRepositoryImpl;
import project.service.VideoService;

public class VideoServiceImpl implements VideoService{
	private VideoRepository videoRepo = new VideoRepositoryImpl();

    @Override
    public List<Video> findAll() {
        return videoRepo.findAll();
    }

    @Override
    public Video findById(int id) {
        return videoRepo.findById(id);
    }

    @Override
    public void insert(Video video) {
        videoRepo.insert(video);
    }

    @Override
    public void update(Video video) {
        videoRepo.update(video);
    }

    @Override
    public void delete(int id) {
        videoRepo.delete(id);
    }

    @Override
    public List<Video> search(String keyword) {
        return videoRepo.search(keyword);
    }
}
