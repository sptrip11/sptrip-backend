package com.twd.SpringSecurityJWT.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "group_code", uniqueConstraints = @UniqueConstraint(columnNames = "code_type"))
public class GroupCode {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "common_code_id")
    private int commonCodeId;

    @Column(name = "code_type", nullable = false, length = 20)
    private String codeType;

    @Column(name = "code_name", nullable = false, length = 20)
    private String codeName;
    
    /*@OneToMany => Code 엔티티와 일대다 관계를 설정
     * mappedBy => Code 엔티티에 groupCode 필드를 매핑했습니다.
     * cascade = CascadeType.ALL => GroupCode에 대한 모든 작업이 Code에도 적용되도록 설정 
     * orrphanRemoval = true => Code가 GroupCode와 연결이 끊어지면 자동으로 삭제되도록 설정.*/
    @JsonIgnore
    @OneToMany(mappedBy = "groupCode", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Code> codes = new ArrayList<>();
    
}
