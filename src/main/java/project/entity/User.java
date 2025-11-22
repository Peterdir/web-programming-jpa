package project.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(columnDefinition = "NVARCHAR(50)")
	private String email;
	
	@Column(columnDefinition = "NVARCHAR(50)")
	private String fullname;
	
	@Column(columnDefinition = "NVARCHAR(50)")
	private String password;
	
	@Column(columnDefinition = "NVARCHAR(15)")
	private String phone;
	
	@Column(columnDefinition = "VARCHAR(20)")
	private String role;
	
	@Column(columnDefinition = "TINYINT(1)")
	private boolean active;
	
	@Column(columnDefinition = "NVARCHAR(255)")
	private String images;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Video> videos = new ArrayList<>();
}
