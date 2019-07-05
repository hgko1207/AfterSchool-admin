package com.ysc.after.school.admin.domain.db;

import lombok.Data;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.ysc.after.school.admin.domain.Domain;

@MappedSuperclass
@Data
public abstract class AbstractDomain implements Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	private LocalDateTime updateDate;
}
