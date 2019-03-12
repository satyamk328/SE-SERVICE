package com.erp.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Satyam Kumar
 *
 */
@Setter
@Getter
@Entity
@Table(name = "user_module")
public class UserModule extends BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RoleId", nullable = false, unique = true)
	private String roleId;
	@Column(name = "PrivilegeName", nullable = false)
	private String privilegeName;
	@Column(name = "IsRecharge", nullable = false)
	private boolean isRecharge = false;
	@Column(name = "IsRecharge", nullable = false)
	private boolean isBillPayment = false;
	@Column(name = "IsRecharge", nullable = false)
	private boolean isMoneyTransfer = false;
	@Column(name = "IsRecharge", nullable = false)
	private boolean isAddMonry = false;
	@Column(name = "IsRecharge", nullable = false)
	private boolean isExportReport = false;
	@Column(name = "IsRecharge", nullable = false)
	private boolean isBusBook = false;
	@Column(name = "UserId", nullable = false, unique = true)
	private String userId;

}
