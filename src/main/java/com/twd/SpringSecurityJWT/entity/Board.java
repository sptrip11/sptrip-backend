package com.twd.SpringSecurityJWT.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "board")
public class Board {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private int boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", referencedColumnName = "user_seq")
    private User user;

    @Column(name = "board_title", nullable = false, length = 200)
    private String boardTitle;

    @Column(name = "board_content", nullable = false, length = 6000)
    private String boardContent;

    @Column(name = "board_image_url", nullable = false, length = 400)
    private String boardImageUrl = "noThumbnail.png";
    
    @CreationTimestamp
    @Column(name = "board_date")
    private LocalDateTime boardDate;

    @Column(name = "board_cnt_wtd", columnDefinition = "int default 0")
    private int boardCntWtd = 0;

    @Column(name = "board_cnt_lkd", columnDefinition = "int default 0")
    private int boardCntLkd = 0;

    @Column(name = "board_cnt_cmt", columnDefinition = "int default 0")
    private int boardCntCmt = 0;

    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardLog> boardLogs = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardLike> boardLikes = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();
    
    public boolean isLikedByUser(User user) {
    	return boardLikes.stream()
    			.anyMatch(like -> like.getUser().equals(user));
    }
    public int getBoardLikesCount() {
        return boardLikes.size();
    }
    public int getCommentsCount() {
        return comments.size();
    }
    
    public void increaseBoardCountWtd() {
    	this.boardCntWtd++;
    }
    
    
   
    
    
}