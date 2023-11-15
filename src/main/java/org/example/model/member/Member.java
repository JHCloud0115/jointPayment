package org.example.model.member;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


@Getter
@Setter
public class Member {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int memberUid;

    private String memberName;

    @Column(unique = true)
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String password;

    private String cellphone;

    private LocalDateTime createdAt;

}
