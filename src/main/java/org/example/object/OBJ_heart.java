package org.example.object;

import org.example.GamePanel;
import org.example.entity.Entity;

public class OBJ_heart extends Entity {

    public OBJ_heart(GamePanel gp) {
        super(gp);
        name = "Heart";
        image= setup ("/objects/heart_full");
        image2 = setup ("/objects/heart_half");
        image3 = setup ("/objects/heart_blank");

    }
}
