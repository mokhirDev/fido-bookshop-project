package org.acme.dto.user;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    public Long id;
    public String name;
    public String fullName;
    public String username;
    public String password;
    public String email;
    public String phone;

}
