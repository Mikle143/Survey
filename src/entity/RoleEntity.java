package entity;

public class RoleEntity {
    private Integer id;
    private String role;

    public RoleEntity(Integer id, String role) {
        this.id = id;
        this.role = role;
    }
    public RoleEntity(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
