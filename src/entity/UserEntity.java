package entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder

public class UserEntity {
    private Integer id;
    private String name;
    private String login;
    private String password;
    private Integer roleId;

}
