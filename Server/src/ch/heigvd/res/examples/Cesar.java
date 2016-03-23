package ch.heigvd.res.examples;

/**
 * Created by michael on 21.03.16.
 */
public class Cesar {

    private int key;

    public Cesar(int key){
        this.key = key;
    }

    public String decrypt(String str){
        String decrypted = "";
        str = str.toUpperCase();
        for(int i = 0; i < str.length(); ++i){
            if(Character.isAlphabetic(str.charAt(i))){
                if((int)str.charAt(i) - key - (int)'A' > 0)
                    decrypted += (char)(((int)str.charAt(i) - key - (int)'A') % 26 + (int) 'A');
                else
                    decrypted += (char)(((int)str.charAt(i) - key - (int)'A') + 26 + (int) 'A');
            }
        }
        return decrypted;
    }

    public String encrypt(String str){
        String encrypted = "";
        str = str.toUpperCase();
        for(int i = 0; i < str.length(); ++i){
            char c = Character.toUpperCase(str.charAt(i));
            if(Character.isLetter(c)){
                encrypted += (char)(((c + key) - (int) 'A') % 26 + (int)'A');
            }
            else if(Character.isSpaceChar(c)){
                encrypted += (char)c;
            }
        }

        return encrypted;
    }
}
