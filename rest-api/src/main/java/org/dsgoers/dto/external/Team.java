package org.dsgoers.dto.external;

import lombok.Data;

@Data
public class Team {

    private int id;
    private String abbrev;
    private String location;
    private String nickname;

    public String getUserFirstName() {
        switch (id) {
            case 1:
                return "Daniel";
            case 2:
                return "Michael";
            case 3:
                return "Hans";
            case 4:
                return "Lizzie";
            case 5:
                return "Shally";
            case 6:
                return "Kiya";
            case 7:
                return "Drew";
            case 8:
                return "Stephen";
            case 9:
                return "Kailea";
            case 10:
                return "Wally";
            default:
                return "Unknown";
        }

    }

}
