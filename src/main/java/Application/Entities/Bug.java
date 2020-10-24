package Application.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bug {
    private int bugId;
    private String testedSystem;
    private Date date;
    private LocalTime time;
    private User user; // Full info admin only
    private String bugName;
    private String betaVersion;
    private String OSModel;
    private String description;
    private byte[] screenshot; //Admin only
    private int status;
}
