/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Verify;

import java.util.Random;

/**
 *
 * @author Anh Tuan
 */
public class RandomCode { // tao ma verify
    public String activateCode(){
        Random rand=new Random();
        int number=rand.nextInt(999999);
        return String.format("%06d",number); // them 0 neu so bat dau voi 0
    }
}
