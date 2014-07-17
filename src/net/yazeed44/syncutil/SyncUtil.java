/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.yazeed44.syncutil;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author yazeed44
 */
public final class SyncUtil {
    
    public static final String NUMBER_SAMPLE = "966566021226";
    public static final String exportFile = "CheckedNumbers.txt";
    public static String getResult(String command){
        String result = "";
        
        Process proc;
          try {
              proc = Runtime.getRuntime().exec(command); // send the command to the terminal
               BufferedReader reader =  new BufferedReader(new InputStreamReader(proc.getInputStream()));
String line;
        
        while((line = reader.readLine()) != null) {
            
            result += line +"\n";
        }

        proc.waitFor();  
          } catch (IOException | InterruptedException ex) {
           
          }

          System.out.println(Thread.currentThread().getName() +" got " + "result : " + "\n" + result );
       
            writeFile(exportFile,result);
        
        return result;
    }
    
    
    public static int getNumberCount(String numbers){
        int count = 0;
        
        for(int index = 0; index < numbers.length();index++){
            
            if(numbers.substring(index, index+1).equals(" ")){
                count++;
            }
        }
        return count;
        
    }
    
    
    public static int getNumberCount(File importFile){
        
        String numbers = SyncUtil.readFile(importFile);
        
        return getNumberCount(numbers);
        
    }
    
    /**
        
     * put string into Clipboard
     * @param writeMe , the text to copy 
     */
    public static void setSysClipboardText(String writeMe) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(writeMe);
        clip.setContents(tText, null);
    }
    
    /**
    * check if user use windows or gnu/Linux
    * @param compount to show the dialog
     * @return returns true if user clicked windows , false if clicked mac or gnu/linux
    */
    public static boolean initIsWindows(Component compount){
        int result =JOptionPane.showOptionDialog(compount, "What is your operating System ?","" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                , null, new String[]{"GNU/Linux or mac","Windows"}, null);
        
        if(result == 0){
            JOptionPane.showMessageDialog(compount, "you must install mono to use this program "+"\n" + "in Gnu/linux : open terminal and type " +"\n"
                    +"sudo apt-get install mono" +"\n" +"in mac : i don't know :D just search and you will find a way"
                    , "", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        else {
            JOptionPane.showMessageDialog(compount, "You are good to go !!", "", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }
    }
    
    /**
     * 
     * @param command - the command without numbers
     * @param threadsNumber - the threads number to be launched
     * @param allNumbers - all the numbers 
     * @return An array of commands with numbers , to be used in getResult(command) 
     */
    public static String[] getCommandWithNumbersSeperated(String command,int threadsNumber,String allNumbers){
        String[] commands = new String[threadsNumber+1];
        int start = 0;
        final int endConst = allNumbers.length() / (commands.length);
        int end = endConst;
        for(int i = 0 ; i < commands.length;i++){
            
            if (i != 0){
             end += endConst;
            }
             System.out.println("Initalizing end = " + end);
             while(end >= allNumbers.length()){
                 end--;
             }
             
             while(allNumbers.charAt(end) != ' '){
                  end++;
               
             }
           
             
            assert(end < allNumbers.length());
           
            System.out.println("Start = " + start + ", end  = " + end);
            
            String numbers = allNumbers.substring(start, end);
           
            commands[i] = command + " "+numbers;
            System.out.println("command " + i  + " =  " + commands[i]);
             start = end;
        }
        
        
        
        return commands;
    }
    
    public static boolean isStartWithNumber(String result){
        boolean isStartWithNumber = result.startsWith("0")||result.startsWith("1")||result.startsWith("2")||result.startsWith("3")||result.startsWith("4")
               ||result.startsWith("5")||result.startsWith("6")||result.startsWith("7")||result.startsWith("8")||result.startsWith("9");
        return isStartWithNumber;
    }
    
    public static boolean isResultTrue(Component compount,String result){
        
         if(result.contains("Exception")){
           JOptionPane.showMessageDialog(compount, "There's a exception : " +"\n"+result, "", JOptionPane.ERROR_MESSAGE);
           return false;
       }
       else if(result.contains("ERROR")){
           JOptionPane.showMessageDialog(compount, "There's an error !! " + "\n" + result, "", JOptionPane.ERROR_MESSAGE);
           return false;
       }
       
       else if (result.contains("failed")){
           JOptionPane.showMessageDialog(compount,"" + result,"",JOptionPane.ERROR_MESSAGE);
           return false;
       }
         
         return true;
    }
    
    public static String readFile(File importFile){
        
        String text = "";
        if (importFile != null){     
            try(BufferedReader buffer = new BufferedReader(new FileReader(importFile))) {
                //get the phone number from every line
                 
                 String line;
            while((line = buffer.readLine()) != null){
                text+=line +" ";
                
            }
            } catch (FileNotFoundException ex) {
               
            } catch (IOException ex) {
               
            }
            
        }
        
        return text;
    }
    
    public static void writeFile(String fileName,String text) {
        
        File file = new File(fileName);
        
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(SyncUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(file,true))) {
            writer.println(text);
            
            
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(SyncUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String createCommand(String username ,String password,String mode,String debug , String context,boolean isWindows){
        String command = "";
        if (!isWindows){
            command += "mono ";
        }
        
        command += "wasyncutil.exe" +" username=";
        command+= username +" ";
        command+="password="+password;
        command+=" mode="+mode;
        command+=" debug="+debug;
        command+=" context="+context;
        return command;
    }
    
    
    public static void showResult(String result,long start,Component component){
        
       SyncUtil.isResultTrue(component, result);
           System.out.println("Final result : " + result);       
        
        
        if (result.length() > 1&& SyncUtil.isStartWithNumber(result)){
           
            
        // Get elapsed time in milliseconds
long elapsedTimeMillis = System.currentTimeMillis()-start;
        

// Get elapsed time in seconds
         float elapsedTimeSec = elapsedTimeMillis/1000F;
         
         String message;
         
         if(result.length() > 100){
             message = "there's alot of numbers , can't display them" + "\n" + "done in : " + elapsedTimeSec +" seconds";
         }
         else {
             message = "numbers who got whatsapp : " + "\n" + result + "\n" + "done in : " + elapsedTimeSec +" seconds";
         }
         
         
           int copy = JOptionPane.showOptionDialog(component, message , "",JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE
                   , null, new String[]{"Copy the numbers"}, null);
           
           if (copy == 0){
               SyncUtil.setSysClipboardText(result);
               
           }
           
           
       }
       
       else if (result.length() == 0){
             // Get elapsed time in milliseconds
long elapsedTimeMillis = System.currentTimeMillis()-start;
        

// Get elapsed time in seconds
         float elapsedTimeSec = elapsedTimeMillis/1000F;
         
         
           JOptionPane.showMessageDialog(component, "Nobody got whatsapp!!"+ "\n"+ "Ohhhh!! I know that feeling bro !!"
                   + "\n" + "done in : " + elapsedTimeSec +" seconds", "", JOptionPane.INFORMATION_MESSAGE);
         
       }
    }
}
