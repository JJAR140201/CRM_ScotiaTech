package crm.scotiatech.wrapper;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWrapper {

    private Integer Id;

    private String name;

    private String email;

    private String contactNumber;

    private String status;

    public UserWrapper(Integer id, String name, String email, String contactNumber, String status) {
        Id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.status = status;
    }
}
