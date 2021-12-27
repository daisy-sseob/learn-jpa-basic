package me.sseob;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "Member") // jpa에서 관리하는 객체.
public class Member {
	@Id
	private Long id;
	
	@Column(name = "name", nullable = false) // db column 명을 name으로 설정, nullable -> database not null제약조건이 추가된다.
	private String username;
	
	private Integer age;

	@Enumerated(EnumType.STRING) // default는 ORINAL인데 Enum의 order를 저장하는 컬럼으로 생성된다.
	private RoleType roleType;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;

	@Lob // java type에 따라 blob, clob컬럼으로 설정된다.
	private String description;

	@Transient // db컬럼에 추가하고 싶지 않은 field
	private int temp;

	public Member() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
