
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class kbc extends JFrame
{
     BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     private int yques_no,fques_no;
     private int[] q_asked=new int[57];
     private String name="0",ques,choices,y_ans,c_ans,filename;
     private byte fifty=1,flip=1;
     private long score;

        
     JFrame frame=new JFrame("Kaun Banega Crorepati??");
         JScrollPane pan;
         JPanel pan1;
         JPanel pan2;
         JPanel pan3;
         JButton ch1;
         JButton ch2;
         JButton ch3;
         JButton ch4;
         JLabel lable1;
         JLabel lable3;
         JButton fif;
         JButton sv;
         JButton qt;
         JButton flp;
    
     public kbc()
     {
         frame=new JFrame("Kaun Banega Crorepati??");
         pan=new JScrollPane();
         pan1=new JPanel();
         pan2=new JPanel();
         pan3=new JPanel();
         ch1=new JButton();
         ch2=new JButton();
         ch3=new JButton();
         ch4=new JButton();
         lable1=new JLabel();
         lable3=new JLabel();
         fif=new JButton("  Fifty-fifty  ");
         sv=new JButton("  Save  ");
         qt=new JButton("  Quit  ");
         flp=new JButton("  Flip  ");
        
         try{
         addEv();
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
     }
    
    public static void main(String[] args)throws IOException
    {
        final kbc k=new kbc();
        final JFrame main =new JFrame("Kaun Banega Crorepati");
        JButton newg=new JButton(" New Game ");
        JButton loadg=new JButton(" Load Game ");
        main.getContentPane().setBackground(Color.orange);
        newg.setCursor(new Cursor(HAND_CURSOR));
        loadg.setCursor(new Cursor(HAND_CURSOR));
        newg.setBackground(Color.yellow);
        loadg.setBackground(Color.yellow);
        newg.setForeground(Color.black);
        loadg.setForeground(Color.black);
        try{
        newg.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt)
            {
                try{k.newf();}
                catch(Exception e){};
                main.dispose();
               
            }
        });
        }
        catch(Exception e)
        {
            System.out.println("ERROR:"+e);
        }
        loadg.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt)
            {
                try{k.load();}
                catch(Exception e)
                {
                    System.out.println(e);
                }
                main.dispose();
            }
        });
       
       
        main.setSize(80,90);
        main.getContentPane().add(newg,"North");
        main.getContentPane().add(loadg,"South");
        main.setVisible(true);
        main.show();
       
    }
   
    public void newf()throws IOException
        {
            final JTextField field=new JTextField("Enter your name");
            final JFrame frame2=new JFrame("New game");
            final JButton sub=new JButton("Submit");
            sub.setForeground(Color.black);
            sub.setBackground(Color.pink);
            sub.setCursor(new Cursor(HAND_CURSOR));
            sub.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                    name=field.getText();
                    frame2.dispose();
                    try{comment();}
                    catch(Exception e)
                    {
                         System.out.println(e);
                    }
                }
            });
            frame2.getContentPane().setBackground(Color.black);
            frame2.setSize(150,100);
            field.selectAll();
            frame2.getContentPane().add(field,"North");
            frame2.getContentPane().add(sub,"South");
            frame2.setVisible(true);
            frame2.show();
            yques_no=1;
            score=0;
            flip=1;
            fifty=1;
        }
    
    
     public void execute()throws IOException
     {
               comment();
               yques_no++;
     }
    
     public void comment()throws IOException
     {
         int tmp=yques_no;

         switch(tmp)
         {
             case 1:lable3.setText("Here is your very first question for Rs.25000");
                    break;
             case 7:
                    lable3.setText("7th question for Rs.200000, this is your 1st breakpoint");
                    break;
             case 29:lable3.setText("Excellent playing!!"+name);
                     break;
             case 56:lable3.setText(name+",here is your final question for 1 crore");
                     break;
             case 20:lable3.setText("Here is the question for your next breakpoint of Rs.2600000");
                     break;
             case 15:
             case 19:
             case 32:
             case 39:
             case 52:
             case 44:lable3.setText("This is really good stuff caming from you, "+name);
                     break;
             case 53:
             case 50:lable3.setText("Here's ques no. "+tmp+" that will take you one step closer to victory!");
                     break;
             case 55:lable3.setText("Your almost there. Dont give up now!");
                     break;
             default:lable3.setText("Question number "+tmp+" up next");
                     break;
         }
         ques_ans();
        
     }
    
     public void load()throws IOException
     {
            final JTextField field=new JTextField("Enter load-file name");
            final JFrame frame2=new JFrame("Load game");
            final JButton sub=new JButton("Submit");
            sub.setForeground(Color.black);
            sub.setBackground(Color.pink);
            sub.setCursor(new Cursor(HAND_CURSOR));
            sub.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                    filename=field.getText();
                    frame2.dispose();
                    try{load2();
                        comment();}
                    catch(Exception e)
                    {
                         System.out.println(e);
                    }
                }
            });
            frame2.getContentPane().setBackground(Color.black);
            frame2.setSize(150,100);
            field.selectAll();
            frame2.getContentPane().add(field,"North");
            frame2.getContentPane().add(sub,"South");
            frame2.setVisible(true);
            frame2.show();
        }
      public void load2()throws IOException
      {
             BufferedReader load;
             load=new BufferedReader(new FileReader(filename+".kbc"));
             String s="";
             while((s=load.readLine())!=null)
             {
                 StringTokenizer st=new StringTokenizer(s,"*",false);
                 while(st.hasMoreTokens()!=false)
                 {
                      name=st.nextToken();
                      yques_no=Integer.parseInt(st.nextToken());
                      fifty=Byte.parseByte(st.nextToken());
                      flip=Byte.parseByte(st.nextToken());
                      String temp=st.nextToken();
                      StringTokenizer stt=new StringTokenizer(temp," ",false);
                      for(int i=0;i<=stt.countTokens();i++)
                      {
                          q_asked[i]=Byte.parseByte(stt.nextToken());
                      }
                      score=Long.parseLong(st.nextToken());
                 }
             }
             load.close();
             display("Load complete!");
     
     }
    
     public void save()throws IOException
     {
            final JTextField field=new JTextField("Enter save-file name");//change for save method
            final JFrame frame2=new JFrame("Save game");
            final JButton sub=new JButton("Submit");
            sub.setForeground(Color.black);
            sub.setBackground(Color.pink);
            sub.setCursor(new Cursor(HAND_CURSOR));
            sub.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                    filename=field.getText();
                    frame2.dispose();
                    try{save2(filename);}
                    catch(Exception e)
                    {
                         System.out.println(e);
                    }
                }
            });
            frame2.getContentPane().setBackground(Color.black);
            frame2.setSize(150,100);
            field.selectAll();
            frame2.getContentPane().add(field,"North");
            frame2.getContentPane().add(sub,"South");
            frame2.setVisible(true);
            frame2.show();
        }
        public void save2(String filename)throws IOException
        {
             PrintWriter save=new PrintWriter(new FileWriter(filename+".kbc"));
             save.print(name+"*"+yques_no+"*"+fifty+"*"+flip+"*");
             int noe=0;//no of elements
             for(int i=0;i<q_asked.length;i++)
             {
                 if(q_asked[i]!=0)
                    noe++;
                 if(q_asked[i]==0)
                    break;
             }
             for(int i=0;i<=noe;i++)
             {
                 save.print(q_asked[i]+" ");
             }
             save.print("*"+score);
             save.close();
             display("Save complete!!");
            
     }
    
     public void ques_ans()throws IOException
     {
         int noe;
         int chk=1;
         for(noe=0;noe<q_asked.length;noe++)
         {
            
             if(q_asked[noe]!=0)
             {
                 continue;
             }
             else
                 break;
         }
         while(chk==1)
         {
         fques_no=(int)(Math.random()*100);
         for(int i=0;i<=noe;i++)
         {
             if(fques_no==q_asked[i])
             {
                   chk=1;
                   break;
             }
             if(fques_no>60)
             {
                 chk=1;
                 break;
             }
             else
                  chk=0;
         }
         }
         q_asked[noe]=fques_no;
         BufferedReader rd=new BufferedReader(new FileReader("Question bank.kbc"));
         String s="",t="";
         StringTokenizer temp;
        
         while((s=rd.readLine())!=null)
         {
             temp=new StringTokenizer(s,"*",false);
             t=temp.nextToken();
             if((Integer.parseInt(t))==(fques_no))
             {
                 ques=temp.nextToken();
                 choices=temp.nextToken();
                 c_ans=temp.nextToken();
                 break;
             }
             else
                  continue;
         }
         StringTokenizer st=new StringTokenizer(choices,"_",false);
         pan3.setBackground(new Color(-85647));
         pan3.setBorder(new javax.swing.border.LineBorder(new Color(23654),4,false));
         pan2.setBackground(Color.pink);
         pan2.setBorder(new javax.swing.border.LineBorder(new Color(23654),4,false));
         pan2.setSize(300,200);
         lable3.setFont(new Font("Berlin Sans FB",0,18));
         pan3.add(lable3);
        
         setChoices(ch1,"  "+st.nextToken()+"  ");
         setChoices(ch2,"  "+st.nextToken()+"  ");
         setChoices(ch3,"  "+st.nextToken()+"  ");
         setChoices(ch4,"  "+st.nextToken()+"  ");
        
         ch1.setEnabled(true);
         ch2.setEnabled(true);
         ch3.setEnabled(true);
         ch4.setEnabled(true);
        
         lable1.setText("  "+ques+"  ");
         lable1.setFont(new Font("Courier New",1,20));
         lable1.setBorder(new javax.swing.border.LineBorder(new Color(3425),2,true));
         pan1.setBackground(new Color(-7575));
         pan1.setBorder(new javax.swing.border.LineBorder(new Color(0),4,false));
         pan1.add(lable1);
         pan1.add(ch1);
         pan1.add(ch2);
         pan1.add(ch3);
         pan1.add(ch4);
         pan.setViewportView(pan1);
        
        
         fif.setBorder(new javax.swing.border.LineBorder(Color.black,2,true));
         fif.setFont(new Font("Westminster",1,18));
         if(fifty==1)
         {
               fif.setBackground(Color.cyan);
               fif.setEnabled(true);
         }
         else{
               fif.setEnabled(false);
               fif.setBackground(Color.magenta);
         }
         fif.setCursor(new Cursor(HAND_CURSOR));
        

        
         flp.setBorder(new javax.swing.border.LineBorder(Color.black,2,true));
         flp.setFont(new Font("Westminster",1,18));
         if(flip==1)
         {
               flp.setBackground(Color.cyan);
               flp.setEnabled(true);
         }
         else
         {
               flp.setBackground(Color.magenta);
               flp.setEnabled(false);
         }
         flp.setCursor(new Cursor(HAND_CURSOR));
        
         setOpts(sv);
         setOpts(qt);
        
         pan2.add(fif);
         pan2.add(flp);
         pan2.add(sv);
         pan2.add(qt);
        
         frame.getContentPane().add(pan3,"North");
         frame.getContentPane().add(pan);
         frame.getContentPane().add(pan2,"South");
         frame.setVisible(true);
         frame.setSize(500,180);
         frame.setResizable(false);
         frame.show();
         }
     /**
      * function to add event listeners for the buttons
      */
     public void addEv()throws IOException
     {
         qt.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                    y_ans="quit";
                    try{check_ans();}
                    catch(Exception e)
                    {
                         System.out.println(e);
                    }
                }
            });
         sv.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                    y_ans="save";
                    try{check_ans();}
                    catch(Exception e)
                    {
                         System.out.println(e);
                    }
                }
            });
         flp.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                    y_ans="flip";
                    try{check_ans();}
                    catch(Exception e)
                    {
                         System.out.println(e);
                    }
                }
            });
         fif.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                   
                   y_ans="fiftyfifty";
                   try{check_ans();}
                    catch(Exception e)
                    {
                         System.out.println(e);
                    }
                }
            });
         ch4.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                   
                   y_ans="D";
                   try{check_ans();}
                    catch(Exception e)
                    {
                         System.out.println(e);
                    }
                }
            });
         ch3.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                   
                   y_ans="C";
                   try{check_ans();}
                    catch(Exception e)
                    {
                         System.out.println(e);
                    }
                }
            });
         ch2.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                   
                   y_ans="B";
                   try{check_ans();}
                    catch(Exception e)
                    {
                         System.out.println(e);
                    }
                }
            });
         ch1.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                   
                   y_ans="A";
                   try{check_ans();}
                    catch(Exception e)
                    {
                         System.out.println(e);
                    }
                }
            });
     }
     public void check_ans()throws IOException
     {
         if(y_ans.charAt(0)==c_ans.charAt(0))
         {
              lable3.setText(c_ans+" is absolutely correct!");
              if(yques_no<=7)
              {
                  score+=25000;
              }
              else if(yques_no>7)
              {
                  score=(yques_no-7)*200000;
              }
              lable3.setText("You've earned Rs."+score+"\n");
              if(score==10200000)
              {
                  frame.dispose();
                  JFrame win=new JFrame("Winner");
                  JLabel end=new JLabel("The winner is "+name);
                  JLabel end2=new JLabel("Rs.1000000");
                  end.setFont(new Font("Copperplate Gothic Bold",1,30));
                  end2.setFont(new Font("Copperplate Gothic Bold",1,30));
                  win.getContentPane().setBackground(Color.orange);
                  win.getContentPane().add(end,"North");
                  win.getContentPane().add(end2,"South");
                  win.setSize(400,200);
                  win.show();
                  win.setVisible(true);
              }
         }
         else if(y_ans.equals("fiftyfifty"))
         {
            
             if(fifty==0)
             {
                 lable3.setText("Sorry "+name+", you already used you fifty-fity");
             }
             else
             {
                   fifty();
                   fifty--;
             }
         }
         else if(y_ans.equals("flip"))
         {
             if(flip==1)
             {
             lable3.setText("Here's your new question:");
             ques_ans();
             flip--;
             }
             else
             {
                  lable3.setText(name+", you have used your flip!");
             }
         }
         else if(y_ans.equals("save"))
         {
             frame.dispose();
             save();
         }
         else if(y_ans.equals("quit"))
         {
             if(score>=200000&&score<2600000)
              {
                  display("You've won 2 lakhs since you crossed your first breakpoint!!");
              }
              else if(score>=2600000)
              {
                  display("You've won 26 lakhs since you crossed your second breakpoint!!");
              }
              else
                  display("You looser, you'll go away with absoelutely nothing!");
              frame.dispose();
         }
         else
         {
              if(score>=200000&&score<2600000)
              {
                  display("Sorry, wrong ans.But you've won 2 lakhs.Correct ans was "+c_ans);
              }
              else if(score>=2600000)
              {
                  display("Sorry, wrong ans.But you've won 26 lakhs.Correct ans was "+c_ans);
              }
              else
              {
                  display("Sorry, wrong ans.Correct ans was "+c_ans);
              }
              frame.dispose();
         }
        
         if(y_ans.equals("fiftyfifty")==false&&y_ans.equals("flip")==false)
         {
         pan1.removeAll();
         final JButton next=new JButton();
         setChoices(next," Next... ");
         pan1.add(next,"NorthWest");
         next.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                   pan1.remove(next);
                   try{yques_no++;
                       comment();}
                    catch(Exception e)
                    {
                         System.out.println(e);
                    }
                }
            });
        }
     }
    
     public void fifty()
     {
             String opt1=ch1.getText();
             opt1=opt1.substring(2,opt1.length()-2);

             String opt2=ch2.getText();
             opt2=opt2.substring(2,opt2.length()-2);
 
             String opt3=ch3.getText();
             opt3=opt3.substring(2,opt3.length()-2);
 
             String opt4=ch4.getText();
             opt4=opt4.substring(2,opt4.length()-2);

             if(opt1.equals(c_ans))
             {
                 ch3.setBackground(Color.magenta);
                 ch3.setEnabled(false);
                 ch4.setBackground(Color.magenta);
                 ch4.setEnabled(false);
             }
             else if(opt2.equals(c_ans))
             {
                 ch1.setBackground(Color.magenta);
                 ch1.setEnabled(false);
                 ch4.setBackground(Color.magenta);
                 ch4.setEnabled(false);
             }
             else if(opt3.equals(c_ans))
             {
                 ch2.setBackground(Color.magenta);
                 ch2.setEnabled(false);
                 ch4.setBackground(Color.magenta);
                 ch4.setEnabled(false);
             }
             else if(opt4.equals(c_ans))
             {
                 ch3.setBackground(Color.magenta);
                 ch3.setEnabled(false);
                 ch1.setBackground(Color.magenta);
                 ch1.setEnabled(false);
            }
            }
            
             public void display(String text)
             {
                 GridBagLayout lay=new GridBagLayout();
                 GridBagConstraints gbc=new GridBagConstraints();
                
                 JLabel print=new JLabel(text);
                 final JFrame frm=new JFrame("Message");
                 JButton ok=new JButton("OK");
                
                 frm.getContentPane().setLayout(lay);
                
                 print.setFont(new Font("Fastrac Fashion",1,30));
                 print.setBorder(new javax.swing.border.LineBorder(new Color(2121),2,true));
                 frm.getContentPane().setBackground(new Color(-3575));
                
                 gbc.gridx=0;gbc.gridy=0;
                 gbc.ipadx=15;gbc.ipady=25;
                 gbc.anchor = GridBagConstraints.CENTER;
                 lay.setConstraints(print,gbc);
                 frm.getContentPane().add(print);
                
                 gbc.gridx=0;gbc.gridy=1;
                 gbc.ipadx=1;gbc.ipady=1;
                 gbc.anchor = GridBagConstraints.CENTER;
                 lay.setConstraints(ok,gbc);
                 frm.getContentPane().add(ok);
                
                 ok.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent evt){
                    frm.dispose();
                }
            });
                 ok.setBackground(new Color(-7575));
                
                 frm.pack();
                 frm.show();
             }
            
             public void setChoices(JButton but,String text)
             {
                 but.setText(text);
                 but.setFont(new Font("Fastrac Fashion",1,25));
                 but.setBorder(new javax.swing.border.LineBorder(new Color(2121),2,true));
                 but.setBackground(new Color(-3575));
                 but.setCursor(new Cursor(HAND_CURSOR));
             }
            
              public void setOpts(JButton but)
             {
                 but.setBorder(new javax.swing.border.LineBorder(Color.black,2,true));
                 but.setFont(new Font("Westminster",1,18));
                 but.setBackground(Color.cyan);
                 but.setCursor(new Cursor(HAND_CURSOR));
             }
                
}
