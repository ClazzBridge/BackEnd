package com.example.academy.service;

import com.example.academy.domain.Classroom;
import com.example.academy.domain.Course;
import com.example.academy.domain.Vote;
import com.example.academy.dto.vote.AddVoteDTO;
import com.example.academy.dto.vote.GetVoteDTO;
import com.example.academy.repository.mysql.CourseRepository;
import com.example.academy.repository.mysql.VoteRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

  private final VoteRepository voteRepository;
  private final CourseRepository courseRepository;

  public VoteService(VoteRepository voteRepository, CourseRepository courseRepository) {
    this.voteRepository = voteRepository;
    this.courseRepository = courseRepository;
  }

  public void addVote(AddVoteDTO addVoteDTO) {
    Vote vote = new Vote();
    vote.setCourse(courseRepository.findByTitle(addVoteDTO.getCourseTitle()).get());
    vote.setTitle(addVoteDTO.getTitle());
    vote.setDescription(addVoteDTO.getDescription());
    vote.setStartDate(addVoteDTO.getStartDate());
    vote.setEndDate(addVoteDTO.getEndDate());
    voteRepository.save(vote);
  }

//  public Vote updateVote(UpdateVoteDTO updateVoteDTO) {
//
//    Long id = updateVoteDTO.getId();
//    String title = updateVoteDTO.getTitle();
//
//
//    Optional<Vote> vote = VoteRepository.findById(id);
//    if (vote.isEmpty()) {
//      throw new NoSuchElementException("해당 투표가 없습니다.");
//    }
//    Vote newvVote = vote.get();
//
//    newvVote.setId(id);
//    newvVote.setTitle(title);
//
//    return voteRepository.save(newvVote);
//  }

  public List<GetVoteDTO> getAllVote(){
    LocalDateTime now = LocalDateTime.now();
    List<Vote> votes = voteRepository.findAll();
    for (Vote vote1 : votes) {
      if (now.isAfter(vote1.getEndDate()) && now.isAfter(vote1.getStartDate())
          // 현재 > 종료날짜 , 현재 > 시작날짜
      || now.isBefore(vote1.getStartDate())) {
          // 현재 < 시작날짜
        vote1.setIsExpired(false);
      }
      voteRepository.save(vote1);
    }

    for (Vote vote2 : votes) {
      if (now.isBefore(vote2.getEndDate()) && now.isAfter(vote2.getStartDate()) ) {
        // 현재 < 종료,  현재 > 시작
        vote2.setIsExpired(true);
      }
      voteRepository.save(vote2);
    }
  //투표 시작날짜가 현재보다 빠르고 종료날짜가 느린 값들은 Ture 아니면 false;

    return votes.stream()
        .map(vote -> new GetVoteDTO(
            vote.getId(),
            vote.getCourse().getTitle(),
            vote.getTitle(),
            vote.getDescription(),
            vote.getStartDate(),
            vote.getEndDate(),
            vote.getIsExpired()))
        .collect(Collectors.toList());
  }

  public List<GetVoteDTO> getVote(Long id)  {
    Optional<Vote> votes = voteRepository.findById(id);
//    if (votes.isPresent()) {
//      throw new Exception("조회된 투표가 없습니다.");
//    }
    return votes.stream()
        .map(vote -> new GetVoteDTO(
            vote.getId(),
            vote.getCourse().getTitle(),
            vote.getTitle(),
            vote.getDescription(),
            vote.getStartDate(),
            vote.getEndDate(),
            vote.getIsExpired()))
        .collect(Collectors.toList());
  }


  public void deleteVote(Long id) throws Exception{
    try {
      voteRepository.deleteById(id);
    } catch (Exception e) {
      throw new Exception("잘못된 삭제입니다.");
    }
  }
}
