package com.voco_task.utility;
import java.util.UUID;

public class CodeGenerator {
    public static String generateCode(){
        String uuid = UUID.randomUUID().toString();
        //546654-546465-46132-87-9
        String[] data = uuid.split("-");
        String newCode = "";
        int i=0;

        while(i < data.length){
            newCode += data[i].charAt(0);
            i++;
        }
        return newCode;
    }
}
