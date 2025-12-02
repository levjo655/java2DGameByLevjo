package org.example.object;

import org.example.GamePanel;
import org.example.entity.Entity;

public class OBJ_Boots extends Entity {


    public OBJ_Boots(GamePanel gp) {
    super (gp);
        name = "Boots";
        down1= setup ("/objects/boots");


    }
}
