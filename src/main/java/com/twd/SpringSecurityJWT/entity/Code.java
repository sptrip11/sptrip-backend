package com.twd.SpringSecurityJWT.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "code")
public class Code {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_id")
    private int codeId;

	/*
	 * @ManyToOne => GroupCode 엔티티와 다대일 관계를 설정.
	 * @JoinColumn => 외래 키 컬럼을 지정.
	 * referencedColumnName => GroupCode 테이블의 code_type 컬럼을 참조하도록 설정.
	 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_type", referencedColumnName = "code_type")
    private GroupCode groupCode;

    @Column(name = "code_value", nullable = false, length = 5)
    private String codeValue;

    @Column(name = "code_name", nullable = false, length = 20)
    private String codeName;
}
