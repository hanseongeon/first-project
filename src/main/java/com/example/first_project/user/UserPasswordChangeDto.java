package com.example.first_project.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserPasswordChangeDto {

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String nowpassword;

    @NotEmpty(message = "비밀번호 필수항목입니다.")
    private String newpassword1;

    @NotEmpty(message = "비밀번호 확인은 필수입니다.")
    private String newpassword2;
}
