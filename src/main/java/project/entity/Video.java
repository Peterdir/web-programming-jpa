package project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="videos")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int videoId;
	
	@Column(columnDefinition = "NVARCHAR(250)")
	private String title;
	
	@Column(columnDefinition = "NVARCHAR(500)")
	private String poster;
	
	@Column(columnDefinition = "BIGINT")
	private long views;
	
	@Column(columnDefinition = "NVARCHAR(500)")
	private String description;
	
	@Column(columnDefinition = "TINYINT(1)")
	private boolean active;
	
	@ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
	
	@ManyToOne
    @JoinColumn(name = "userId")
    private User user;
	
}
